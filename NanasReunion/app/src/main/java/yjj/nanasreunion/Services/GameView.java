package yjj.nanasreunion.Services;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

import yjj.nanasreunion.MyStack;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Scenes.*;


public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private MyStack<Scene> m_Scenes;

    public GameView(Context context)
    {
        super(context);
        Init(context);
        getHolder().addCallback(this);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
        getHolder().addCallback(this);
    }

    public void Init(Context context)
    {
        Log.d("View init", "View init");
        m_Scenes = new MyStack<>();
        ServiceHub.Inst().InitServices(context, this);

        GameThread _GameThread = ServiceHub.Inst().GetGameThread();
        _GameThread.SetRunning(true);
        _GameThread.start();

        PushScene(new GameplayScene());
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
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
       // R.drawable.ic_launcher_foreground;
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
        if(canvas == null) return;
        m_Scenes.top().Render(canvas, Timer.Interpolation());
    }

    public void Update()
    {
        m_Scenes.top().Update(Timer.DeltaTime());
    }
}
