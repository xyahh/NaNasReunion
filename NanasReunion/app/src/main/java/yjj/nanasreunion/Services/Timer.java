package yjj.nanasreunion.Services;

public class Timer
{
    public final static float DELTA_TIME  = 0.01f;

    private static long m_PrevTime         = 0;
    private static long m_TimeFrame        = 0;

    private static float m_TimeAccumulator = 0.f;
    private static float m_TimeDilation    = 1.f;

    public static void Reset()
    {
        m_PrevTime          = System.currentTimeMillis();
        m_TimeAccumulator   = 0.f;
        m_TimeFrame         = 0;
        m_TimeDilation      = 1.f;
    }

    public static void Tick()
    {
        long  m_CurrentTime = System.currentTimeMillis();
        m_TimeFrame = m_CurrentTime - m_PrevTime;
        m_PrevTime = System.currentTimeMillis();
        m_TimeAccumulator += (float)(m_TimeFrame) * m_TimeDilation / 1000.f;
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
