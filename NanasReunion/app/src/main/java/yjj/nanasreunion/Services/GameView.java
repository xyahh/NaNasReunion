package yjj.nanasreunion.Services;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import yjj.nanasreunion.MyStack;
import yjj.nanasreunion.Scenes.*;


public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private MyStack<Scene> m_Scenes;

    public GameView(Context context)
    {
        super(context);
        setFocusable(true);
        m_Scenes = new MyStack<>();
        m_Scenes.push(new NullScene());
    }

    public void ChangeScene(Scene scene)
    {
        if(scene == null) return; // nothing happens if input scene is null

        PopScene();
        PushScene(scene);
    }

    public void PopScene()
    {
        m_Scenes.pop().Destroy();
    }

    public void PushScene(Scene scene)
    {
        if(scene == null) return;

        m_Scenes.push(scene);
        m_Scenes.top().Init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        GameThread _GameThread = ServiceHub.Inst().GetGameThread();

        _GameThread.SetRunning(true);
        _GameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        GameThread _GameThread = ServiceHub.Inst().GetGameThread();
        boolean retry = true;
        _GameThread.SetRunning(false);
        while(retry)
        {
            try
            {
                _GameThread.join();
                retry = false;

            } catch (InterruptedException e)
            {
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Draw(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        super.onKeyDown(keyCode, event);
        return m_Scenes.top().OnKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        super.onTouchEvent(event);
        return m_Scenes.top().OnTouchEvent(event);
    }

    public void Draw(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        m_Scenes.top().Render(canvas, Timer.Interpolation());
    }

    public void Update()
    {
        m_Scenes.top().Update(Timer.DELTA_TIME);
    }
}
