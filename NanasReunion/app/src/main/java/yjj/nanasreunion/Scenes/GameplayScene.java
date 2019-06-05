package yjj.nanasreunion.Scenes;

import yjj.nanasreunion.Objects.Components.Camera;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayDeque;

import yjj.nanasreunion.Objects.*;
import yjj.nanasreunion.Objects.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2d;

public class GameplayScene implements Scene
{
    private Camera      m_Camera;

    private ArrayDeque<Actor> m_Actors;
    private ArrayDeque<Widget> m_Widgets;

    private Pawn        m_PlayerPawn;

    @Override
    public void Init()
    {
        m_Actors = new ArrayDeque<>();
        m_Widgets = new ArrayDeque<>();

        m_Camera        = new Camera();
        m_PlayerPawn    = new Pawn();
        //android.R.drawable._normal_banana;
        //m_PlayerPawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(android.R.drawable.n))


        m_Actors.addFirst(m_PlayerPawn);
        m_Camera.SetCameraOffset(new Vector2d(0.1f, 0.9f));
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

        for(Actor a : m_Actors)
            for(Actor b: m_Actors)
                a.ProcessCollision(b);

        for(Actor a : m_Actors)
            if(a.IsDestroyed())
                m_Actors.remove(a);
    }

    @Override
    public void Render(Canvas canvas, float interp)
    {
        m_Camera.UpdateCameraView(m_PlayerPawn.position); // pre compute view once per frame

        for(Actor a : m_Actors)
            a.Draw(canvas, m_Camera, interp);

        for(Widget w: m_Widgets)
            w.Draw(canvas, m_Camera, interp);
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
