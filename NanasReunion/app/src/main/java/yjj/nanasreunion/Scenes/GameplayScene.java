package yjj.nanasreunion.Scenes;

import yjj.nanasreunion.Command.TogglePauseCommand;
import yjj.nanasreunion.Components.Camera;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayDeque;

import yjj.nanasreunion.Components.Graphics.RectGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Objects.*;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.OtherClasses.Scrolling.ScrollingBackground;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class GameplayScene implements Scene
{
    private Camera      m_Camera;

    private ScrollingBackground m_Background;

    private ArrayDeque<Actor>  m_Actors;

    private Pawn                m_PlayerPawn;

    private void InitActors()
    {
        m_PlayerPawn    = new Pawn();
        m_PlayerPawn.position = new Vector2f(0.f, 0.f);
        m_PlayerPawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.moving_banana),
                10, 6, 6);
        m_PlayerPawn.physics = new Physics();
        m_PlayerPawn.physics.SetMass(1.f);
        m_PlayerPawn.SetCamera(m_Camera);
        m_Actors.addLast(m_PlayerPawn);
    }

    private void InitCamera()
    {
        m_Camera = new Camera();
        m_Camera.SetCameraOffset(new Vector2f(-0.3f, -0.6f));
        m_Camera.SetMovingFactor(1.f, 0.f);
    }

    private void InitBackground()
    {
        m_Background = new ScrollingBackground();
        Vector2i ScreenSize = ServiceHub.Inst().GetScreenSize();
        Vector2f AbsoluteSpeedZero = new Vector2f();
        Vector2f CloudsAbsoluteSpeed = new Vector2f(-10.f, 0.f);

        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.sun), 1.15f, AbsoluteSpeedZero,0.f, new Vector2i(ScreenSize.x / 4, ScreenSize.x / 4));
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.clouds), 0.75f, CloudsAbsoluteSpeed,0.025f, ScreenSize);
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.grass), 0.25f, AbsoluteSpeedZero,0.07f, ScreenSize);
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.clouds3), 1.f, CloudsAbsoluteSpeed, 0.05f, ScreenSize);
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.tree), 0.75f, AbsoluteSpeedZero, 1.f, ScreenSize);
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.ground), -0.3f, AbsoluteSpeedZero,1.f, ScreenSize);
    }

    @Override
    public void Init()
    {
        m_Actors = new ArrayDeque<>();

        InitCamera();
        InitActors();
        InitBackground();
    }

    @Override
    public void Destroy()
    {

    }

    @Override
    public void SurfaceChange(int width, int height)
    {
    }

    @Override
    public void Update(float deltaTime)
    {
        m_Background.Update(m_Camera, deltaTime);

        for(Actor a : m_Actors)
            a.Update(deltaTime);

        for(Actor a : m_Actors)
            for(Actor b: m_Actors)
                a.ProcessCollision(b);

        for(Actor a : m_Actors)
            if(a.IsDestroyed())
                m_Actors.remove(a);

        m_Camera.UpdateCameraView(m_PlayerPawn, deltaTime); // pre compute view once per update
    }

    @Override
    public void Render(Canvas canvas, float interp)
    {
        canvas.drawColor(Color.argb(255, 135, 206, 235));


        m_Background.DrawObjects(canvas, null, m_Camera);

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
