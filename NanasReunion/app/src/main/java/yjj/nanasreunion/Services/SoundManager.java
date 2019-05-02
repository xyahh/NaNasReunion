package yjj.nanasreunion.Services;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager
{
    private static  SoundManager    m_Instance = new SoundManager();

    private         SoundPool       m_SoundPool;
    private         HashMap         m_SoundPoolMap;
    private         AudioManager    m_AudioManager;

    private SoundManager()
    {
    }

    public static SoundManager Get(ServiceHub.Assert a)
    {
        a.hashCode(); //verify that the caller is ServiceHub only.
        return m_Instance;
    }

    public void Init(Context context)
    {
        SoundPool.Builder   m_SoundPoolBuilder;
        m_SoundPoolBuilder = new SoundPool.Builder();
        m_SoundPoolBuilder.setMaxStreams(5);
        m_SoundPool = m_SoundPoolBuilder.build();

        //m_SoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);

        m_SoundPoolMap = new HashMap();
        m_AudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void AddSound(int index, int soundId, Context context)
    {
        int id = m_SoundPool.load(context, soundId, 1);
        m_SoundPoolMap.put(index, id);
    }

    public void PlaySound(int index, int loop)
    {
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume /
                m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer) m_SoundPoolMap.get(index),
                streamVolume, streamVolume,
                1, loop, 1f);
    }
}
