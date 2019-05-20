package yjj.nanasreunion.Scenes;

import yjj.nanasreunion.Objects.Components.Camera;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.*;
import yjj.nanasreunion.Vector2d;

public class GameplayScene implements Scene
{
    private Camera      m_Camera;
    private Actor[]     m_Actors;
    private Widget[]    m_Widgets;
    private Pawn        m_PlayerPawn;

    @Override
    public void Init()
    {
        m_Actors        = new Actor[50];
        m_Widgets       = new Widget[10];

        m_Camera        = new Camera();
        m_PlayerPawn    = new Pawn();
        m_Actors[0]      = m_PlayerPawn;

        m_Camera.SetTarget(m_PlayerPawn.position);
        m_Camera.SetCameraOffset(new Vector2d(0.f, 0.f));
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
        m_Camera.GenerateView(); // pre compute view once per frame
        for(Actor a : m_Actors)
            a.Draw(canvas, m_Camera, interp);
        for(Widget w: m_Widgets)
            w.Draw(canvas);
    }

    @Override
    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return m_PlayerPawn.OnKeyDown(keyCode, event);
    }

    @Override
    public boolean OnTouchEvent(MotionEvent event)
    {
        //process Widget input first. If processed, do NOT process player input
        for(Widget w : m_Widgets)
        {
            if(w.OnTouchEvent(event))
                return true;
        }
        return m_PlayerPawn.OnTouchEvent(event);
    }
}
