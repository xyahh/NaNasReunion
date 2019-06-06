package yjj.nanasreunion.Services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.util.DisplayMetrics;

import yjj.nanasreunion.Scenes.GameplayScene;

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


    public void InitServices(Context context)
    {
        m_GameView      = new GameView(context);
        m_GameView.getHolder().addCallback(m_GameView);
        m_Thread        = new GameThread(m_GameView.getHolder(), m_GameView);
        m_Resources     = m_GameView.getResources();
        m_SoundManager  = SoundManager.Get(m_Assert);

        m_GameView.PushScene(new GameplayScene());
    }

    public static ServiceHub Inst()          { return m_Instance; }

    public SoundManager GetSoundManager()   { return m_SoundManager;}
    public GameThread   GetGameThread()     { return m_Thread;      }
    public GameView     GetGameView()       { return m_GameView;    }
    public Resources    GetResources()      { return m_Resources;   }
    public Bitmap       GetBitmap(int r)    { return BitmapFactory.decodeResource(m_Resources, r); }
}
