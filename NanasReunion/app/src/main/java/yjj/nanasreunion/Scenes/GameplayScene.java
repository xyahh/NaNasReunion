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
    private ArrayDeque<Widget> m_Widgets;

    private Pawn                m_PlayerPawn;

    private void InitActors()
    {
        m_PlayerPawn    = new Pawn();
        m_PlayerPawn.position = new Vector2f(0.f, 0.f);
        m_PlayerPawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.moving_banana),
                10, 6, 6);
        m_PlayerPawn.physics = new Physics();
        m_PlayerPawn.physics.SetMass(1.f);
        m_Actors.addLast(m_PlayerPawn);
    }

    private void InitCamera()
    {
        m_Camera = new Camera();
        m_Camera.SetCameraOffset(new Vector2f(-0.3f, -0.6f));
    }

    private void InitBackground()
    {
        m_Background = new ScrollingBackground();
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.ground), -0.4f, 1.f, ServiceHub.Inst().GetScreenSize());
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.sun), 2.f, 0.f, new Vector2i(100, 100));
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.clouds), 1.25f, 0.1f, ServiceHub.Inst().GetScreenSize());
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.grass), 0.15f, 0.07f, ServiceHub.Inst().GetScreenSize());
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.clouds3), 1.75f, 0.15f, ServiceHub.Inst().GetScreenSize());
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.tree), 0.6f, 1.f, ServiceHub.Inst().GetScreenSize());
    }

    private void InitWidgets()
    {
        Widget w;
        Vector2i v = new Vector2i(50, 50);
        w = new Widget(new Vector2i(0, 0), v);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        w.graphics = new RectGraphics(v, paint);

        w.owner = m_PlayerPawn;
        w.AddCommand(new TogglePauseCommand(),  true);
        m_Widgets.add(w);
    }

    @Override
    public void Init()
    {
        m_Actors = new ArrayDeque<>();
        m_Widgets = new ArrayDeque<>();

        InitActors();
        InitCamera();
        InitBackground();
        InitWidgets();
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
        m_Background.Update(m_PlayerPawn, m_Camera, deltaTime);

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
        canvas.drawColor(Color.argb(255, 135, 206, 235));
        m_Camera.UpdateCameraView(m_PlayerPawn); // pre compute view once per frame

        m_Background.DrawBackground(canvas, null, m_Camera);

       for(Actor a : m_Actors)
           a.Draw(canvas, m_Camera, interp);

       for(Widget w: m_Widgets)
           w.Draw(canvas, interp);

       m_Background.DrawForeground(canvas, null, m_Camera);
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
