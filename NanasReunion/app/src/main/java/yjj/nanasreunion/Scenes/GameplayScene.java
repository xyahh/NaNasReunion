package yjj.nanasreunion.Scenes;

import yjj.nanasreunion.Components.Camera;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayDeque;
import java.util.Random;

import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Item.ItemBox;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Components.State.RunningState;
import yjj.nanasreunion.Objects.*;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.OtherClasses.Scrolling.ScrollingBackground;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class GameplayScene implements Scene
{
    private float       m_TotalTime;

    private Camera      m_Camera;

    private ScrollingBackground m_Background;

    private ArrayDeque<Actor>  m_Actors;
    private Random              m_Randomizer = new Random();
    private Pawn                m_PlayerPawn;

    private float               m_CountdownTimer;
    private float               m_EnemySpawnTimer;

    private Enemy               m_EnemySpawner[] =
            {
                    new Monkey(),
                    new Bird()
            };

    private void InitActors()
    {
        m_PlayerPawn    = new Pawn();
        m_PlayerPawn.position = new Vector2f(0.f, 0.f);
        m_PlayerPawn.pivot = new Vector2f(0.5f, 1.f);

        m_PlayerPawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.moving_banana),
                10, 6, 6);
        m_PlayerPawn.graphics.SetScale(0.75f, 0.75f);
        m_PlayerPawn.physics = new Physics();
        m_PlayerPawn.physics.SetMass(1.f);
        m_PlayerPawn.physics.SetMaxVelocity(new Vector2f(1.f, 900.f));
        m_PlayerPawn.SetCamera(m_Camera);
        m_PlayerPawn.ChangeState(new RunningState());

        m_PlayerPawn.collision.SetCollisionType(COLLISION_TYPES.PLAYER);
        m_PlayerPawn.collision.SetCollisionEnabled(true);
        m_PlayerPawn.collision.SetDimensions(0.15f, 0.4f);

        m_Actors.addLast(m_PlayerPawn);
    }

    private void SpawnEnemy()
    {
            int Index = m_Randomizer.nextInt(m_EnemySpawner.length);
            m_Actors.add(m_EnemySpawner[Index].Spawn(m_PlayerPawn));
    }

    private void InitCamera()
    {
        m_Camera = new Camera();
        m_Camera.SetCameraOffset(new Vector2f(-0.15f, -0.5f));
        m_Camera.SetMovingFactor(true, false);
    }

    private void InitBackground()
    {
        m_Background = new ScrollingBackground();
        Vector2i ScreenSize = ServiceHub.Inst().GetScreenSize();
        Vector2f AbsoluteSpeedZero = new Vector2f();
        Vector2f CloudsAbsoluteSpeed = new Vector2f(-0.1f, 0.f);

        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.sun), 1.15f, AbsoluteSpeedZero,0.f, new Vector2i(ScreenSize.x / 4, ScreenSize.x / 4));
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.clouds), 0.75f, CloudsAbsoluteSpeed,0.025f, ScreenSize);
        m_Background.AddScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.grass), 0.f, AbsoluteSpeedZero,0.07f, ScreenSize);
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
        SpawnEnemy();

        m_TotalTime = 0.f;
        ServiceHub.Inst().SetScore((int)m_TotalTime);
        m_EnemySpawnTimer = 0.f;
        m_CountdownTimer = 5.f;
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
        m_TotalTime += deltaTime;
        ServiceHub.Inst().SetScore((int)m_TotalTime);

        m_EnemySpawnTimer+=deltaTime;
        if(m_EnemySpawnTimer >= 4.f)
        {
            m_EnemySpawnTimer-=4.f;
            SpawnEnemy();
        }

        m_CountdownTimer-= deltaTime;
        if(m_CountdownTimer <= 0.f)
        {
            Random r = new Random();
            m_CountdownTimer = 5.f;
            float pos = 0.f;
            if(r.nextBoolean())
                pos = 0.6f;
            ItemBox item_box = new ItemBox();
            item_box.position = new Vector2f(m_PlayerPawn.position.x + 3.f, pos);
            item_box.pivot = new Vector2f(0.5f, 0.5f);
            m_Actors.addLast(item_box);
        }

        m_Background.Update(m_Camera, deltaTime);

        for(Actor a : m_Actors)
            a.Update(deltaTime);

        for(Actor a : m_Actors)
            for(Actor b: m_Actors)
                a.ProcessCollision(b);


        for(Actor a : m_Actors)
            if(a.IsDestroyed())
                m_Actors.remove(a);

        m_Camera.UpdateCameraView(m_PlayerPawn); // pre compute view once per update
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
