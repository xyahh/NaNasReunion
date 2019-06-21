package yjj.nanasreunion.Services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import yjj.nanasreunion.Scenes.GameplayScene;
import yjj.nanasreunion.Scenes.Scene;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class ServiceHub
{

    public static final class Assert { private Assert() {} }

    private static final Assert         m_Assert = new Assert();

    public static  boolean              RightwardGameplay = true;
    public static  float                EnemySpeedMultiplier = 1.f;
    public static  int                  ClearColor = Color.argb(255, 135, 206, 235);
    public static String                ItemName;
    public static boolean LosingCondition = false;
    public static boolean AlreadyLost = false;
    private static  ServiceHub          m_Instance = new ServiceHub();

    private         GameView            m_GameView;
    private         Resources           m_Resources;
    private         GameThread          m_Thread;
    private ServiceHub() {}
    private DisplayMetrics              m_DisplayMetrics;
    private         int                 m_Score;

    private Vector2i m_ScreenSize;

    public Scene GetCurrentScene()
    {
        return m_GameView.GetCurrentScene();
    }

    public void InitServices(Context context, GameView gameview)
    {
        m_GameView = gameview;
        m_Resources     = m_GameView.getResources();
        m_DisplayMetrics = m_GameView.getResources().getDisplayMetrics();
    }

    public void HandleThreadCreation(SurfaceHolder holder, GameView gameView)
    {
       HandleThreadDestroy(); //destroy current thread
        m_Thread = new GameThread(holder, gameView);
        m_Thread.SetRunning(true);
        m_Thread.start();
    }

    public void HandleThreadDestroy()
    {
        if(m_Thread != null)
        {
            boolean retry = true;
            m_Thread.SetRunning(false);
            while(retry)
            {
                try
                {
                    m_Thread.join();
                    retry = false;

                } catch (InterruptedException e)
                {
                }
            }
        }
        m_Thread = null;
    }

    public static ServiceHub Inst()          { return m_Instance; }

    public synchronized void SetScore(int Score)
    {
        m_Score = Score;
    }
    public int GetScore() { return m_Score;}



    void SetScreenSize(Vector2i v)
    {
        m_ScreenSize = v;
    }

    public Vector2i GetScreenSize()
    {
        return new Vector2i(m_ScreenSize);
    }

    public float GetDPI()
    {
        return m_DisplayMetrics.densityDpi / 420.f;
    }

    public GameThread   GetGameThread()     { return m_Thread;      }
    public GameView     GetGameView()       { return m_GameView;    }
    public Resources    GetResources()      { return m_Resources;   }
    public Bitmap       GetBitmap(int r)    { return BitmapFactory.decodeResource(m_Resources, r); }
}
