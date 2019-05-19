package yjj.nanasreunion.Scenes;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Scenes.Scene;

public class NullScene implements Scene
{
    @Override
    public void Init()
    {

    }

    @Override
    public void Destroy()
    {

    }

    @Override
    public void Update(float deltaTime)
    {

    }

    @Override
    public void Render(Canvas canvas, float interp)
    {

    }

    @Override
    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return false;
    }

    @Override
    public boolean OnTouchEvent(MotionEvent event)
    {
        return false;
    }

    @Override
    public void SurfaceChange(int width, int height)
    {

    }
}
