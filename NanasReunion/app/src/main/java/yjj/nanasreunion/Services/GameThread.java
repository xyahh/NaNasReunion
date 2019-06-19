package yjj.nanasreunion.Services;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private SurfaceHolder   m_SurfaceHolder;
    private GameView        m_GameView;
    private boolean         m_Run = false;

    public GameThread(SurfaceHolder _SurfaceHolder, GameView _GameView)
    {
        m_SurfaceHolder = _SurfaceHolder;
        m_GameView = _GameView;
    }

    public void SetRunning(boolean run) { m_Run = run;}

    @Override
    public void run()
    {
        Canvas c;
        Timer.Reset();
        SetRunning(true);
        while(m_Run)
        {
            Timer.Tick();

            while(Timer.FlushTime())
                m_GameView.Update();

            c = null;
            try
            {
                c = m_SurfaceHolder.lockCanvas(null);
                synchronized (m_SurfaceHolder)
                {
                    m_GameView.Draw(c);
                }
            } finally {
                if (c != null)
                {
                    m_SurfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
