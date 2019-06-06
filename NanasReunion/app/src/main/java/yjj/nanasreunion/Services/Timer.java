package yjj.nanasreunion.Services;

public class Timer
{
    private final static float DELTA_TIME  = 0.01f;

    private static long m_PrevTime         = 0;
    private static long m_TimeFrame        = 0;

    private static float m_TimeAccumulator = 0.f;
    private static float m_TimeDilation    = 1.f;

    private static boolean m_IsPaused;

    public static void Reset()
    {
        m_IsPaused          = false;
        m_PrevTime          = System.currentTimeMillis();
        m_TimeAccumulator   = 0.f;
        m_TimeFrame         = 0;
        m_TimeDilation      = 1.f;
    }

    public static void SetTimeDilation(float newTimeDilation)
    {
        m_TimeDilation = newTimeDilation;
    }

    public static float GetTimeDilation()
    {
        return m_TimeDilation;
    }

    public static void Tick()
    {
        long  m_CurrentTime = System.currentTimeMillis();
        m_TimeFrame = m_CurrentTime - m_PrevTime;
        m_PrevTime = System.currentTimeMillis();
        m_TimeAccumulator += (float)(m_TimeFrame) / 1000.f;
    }

    public static boolean TogglePause()
    {
        m_IsPaused = !m_IsPaused;
        return m_IsPaused;
    }

    public static float DeltaTime()
    {
        if(m_IsPaused) return 0.f;
        return DELTA_TIME * m_TimeDilation;
    }

    public static boolean FlushTime()
    {
        boolean Result = m_TimeAccumulator >= DELTA_TIME;
        if(Result)
            m_TimeAccumulator -= DELTA_TIME;
        return Result;
    }

    public static float Interpolation()
    {
        return m_TimeAccumulator * DELTA_TIME;
    }

}
