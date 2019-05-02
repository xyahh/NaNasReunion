package yjj.nanasreunion.Services;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import yjj.nanasreunion.Scenes.*;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Scene m_Scene;

    public GameView(Context context)
    {
        super(context);
        setFocusable(true);
        m_Scene = new NullScene();
    }

    public void ChangeScene(Scene scene)
    {
        if(scene == null) return; // nothing happens if input scene is null
        m_Scene.Destroy();
        m_Scene = scene;
        m_Scene.Init();
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
    protected void onDraw(Canvas canvas) {
        Draw(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        m_Scene.OnKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_Scene.OnTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void Draw(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        m_Scene.Render(canvas, Timer.Interpolation());
    }

    public void Update()
    {
        m_Scene.Update(Timer.DELTA_TIME);
    }
}
