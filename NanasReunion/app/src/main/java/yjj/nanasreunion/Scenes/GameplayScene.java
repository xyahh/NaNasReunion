package yjj.nanasreunion.Scenes;

import yjj.nanasreunion.Actors.Components.Camera;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Actors.*;

public class GameplayScene implements Scene
{
    private Camera      m_Camera;
    private Actor[]     m_Actors;
    private Pawn        m_PlayerPawn;

    @Override
    public void Init()
    {
        m_Actors        = new Actor[100];
        m_Camera        = new Camera();
        m_PlayerPawn    = new Pawn();
        m_Camera.SetFocusedActor(m_PlayerPawn);
    }

    @Override
    public void Destroy()
    {

    }

    @Override
    public void SurfaceChange(int width, int height)
    {
        m_Camera.UpdateViewport(width, height);
    }

    @Override
    public void Update(float deltaTime)
    {
        for(Actor a : m_Actors)
            a.Update(deltaTime);
    }

    @Override
    public void Render(Canvas canvas, float interp)
    {
        for(Actor a : m_Actors)
            a.Draw(canvas, m_Camera, interp);
    }

    @Override
    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return m_PlayerPawn.OnKeyDown(keyCode, event);
    }

    @Override
    public boolean OnTouchEvent(MotionEvent event)
    {
        return m_PlayerPawn.OnTouchEvent(event);
    }
}
