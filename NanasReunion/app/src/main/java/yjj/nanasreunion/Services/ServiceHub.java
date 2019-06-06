package yjj.nanasreunion.Services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.util.DisplayMetrics;

import yjj.nanasreunion.Scenes.GameplayScene;
import yjj.nanasreunion.Vector2i;

public class ServiceHub
{

    public static final class Assert { private Assert() {} }

    private static final Assert         m_Assert = new Assert();

    private static  ServiceHub          m_Instance = new ServiceHub();
    private         SoundManager        m_SoundManager;
    private         GameView            m_GameView;
    private         Resources           m_Resources;
    private         GameThread          m_Thread;
    private ServiceHub() {}
    private DisplayMetrics              m_DisplayMetrics;

    public void InitServices(Context context, GameView gameview)
    {
        m_GameView = gameview;
        m_Thread        = new GameThread(m_GameView.getHolder(), m_GameView);
        m_Resources     = m_GameView.getResources();
        m_SoundManager  = SoundManager.Get(m_Assert);
        m_DisplayMetrics = m_GameView.getResources().getDisplayMetrics();
        m_GameView.PushScene(new GameplayScene());
    }

    public static ServiceHub Inst()          { return m_Instance; }

    public Vector2i GetScreenSize()
    {
        return new Vector2i(m_DisplayMetrics.widthPixels, m_DisplayMetrics.heightPixels);
    }

    public float GetDPI()
    {
        return m_DisplayMetrics.densityDpi / 420.f;
    }

    public SoundManager GetSoundManager()   { return m_SoundManager;}
    public GameThread   GetGameThread()     { return m_Thread;      }
    public GameView     GetGameView()       { return m_GameView;    }
    public Resources    GetResources()      { return m_Resources;   }
    public Bitmap       GetBitmap(int r)    { return BitmapFactory.decodeResource(m_Resources, r); }
}
