package yjj.nanasreunion.Scenes;

import yjj.nanasreunion.Objects.Components.Camera;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayDeque;

import yjj.nanasreunion.Objects.*;
import yjj.nanasreunion.Objects.Components.Graphics.Graphics;
import yjj.nanasreunion.Objects.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Components.Graphics.StaticGraphics;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2d;

public class GameplayScene implements Scene
{
    private Camera      m_Camera;

    private ArrayDeque<Actor>  m_Actors;
    private ArrayDeque<Widget> m_Widgets;

    private Pawn                m_PlayerPawn;

    private void InitActors()
    {
        Actor a;
        a = new Actor();
        a.graphics = new StaticGraphics(ServiceHub.Inst().GetBitmap(R.drawable.sun));
        a.position = new Vector2d(100.f, 100.f);
        m_Actors.addFirst(a);

        a = new Actor();
        a.position = new Vector2d(100.f, 10.f);
        a.graphics = new StaticGraphics(ServiceHub.Inst().GetBitmap(R.drawable.normal_banana));
        m_Actors.addLast(a);

        m_PlayerPawn    = new Pawn();
        m_PlayerPawn.position = new Vector2d(0.f, 0.f);
        m_PlayerPawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.normal_banana),
                10, 6, 6);
        m_PlayerPawn.graphics.SetScale(1.f);
        m_Actors.addLast(m_PlayerPawn);
    }

    private void InitCamera()
    {
        DisplayMetrics displayMetrics = ServiceHub.Inst().GetResources().getDisplayMetrics();
        m_Camera = new Camera();
        m_Camera.UpdateViewport(displayMetrics.widthPixels, displayMetrics.heightPixels);
        m_Camera.SetCameraOffset(new Vector2d(-100.f, -100.f));
    }

    @Override
    public void Init()
    {
        m_Actors = new ArrayDeque<>();
        m_Widgets = new ArrayDeque<>();

        InitActors();
        InitCamera();
    }

    @Override
    public void Destroy()
    {

    }

    @Override
    public void SurfaceChange(int width, int height)
    {
    }

    float time =0.f;
    boolean scaled = false;
    @Override
    public void Update(float deltaTime)
    {
        time += deltaTime;
        if(time > 3.f)
        {
            time = 0.f;
            if(scaled)
            {
                m_PlayerPawn.graphics.SetScale(2.f);
            } else
            {
                m_PlayerPawn.graphics.SetScale(0.5f);
            }

            scaled = !scaled;
        }

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
