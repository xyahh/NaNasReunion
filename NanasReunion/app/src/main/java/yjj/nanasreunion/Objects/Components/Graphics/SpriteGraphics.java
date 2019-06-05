package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;

import yjj.nanasreunion.Objects.Components.Collision.Collision;
import yjj.nanasreunion.Vector2d;

public class SpriteGraphics extends Graphics
{
    private float   m_FrameFrequency; // 1.f / FPS
    private int     m_FrameCount;

    private int     m_CurrentFrame;

    private float   m_FrameTimer;

    private int     m_SpritesPerRow;

    private int     m_SpriteHeight;
    private int     m_SpriteWidth;

    public SpriteGraphics(Bitmap bitmap, int FrameHeight, int FrameWidth,
                          int FPS, int TotalFrames, int SpritesPerRow, float Scale)
    {
        SetSprite(bitmap, FrameHeight, FrameWidth, TotalFrames, SpritesPerRow, Scale);
        SetFPS(FPS);
    }

    void SetSprite(Bitmap bitmap, int FrameHeight, int FrameWidth, int TotalFrames, int SpritesPerRow, float Scale)
    {
        m_FrameCount = TotalFrames;

        m_FrameTimer = 0.f;

        m_SpritesPerRow = SpritesPerRow;

        m_CurrentFrame = 0;
        m_SpriteHeight = FrameHeight;
        m_SpriteWidth = FrameWidth;

        m_Bitmap = bitmap;
        m_SourceRect        = new Rect(0, 0, m_Bitmap.getWidth(), m_Bitmap.getHeight());
        m_BitmapHalfSize    = new Vector2d(FrameHeight  * 0.5f * Scale, FrameWidth * 0.5f * Scale);
        m_BitmapBox         = new Collision(new Vector2d(), m_BitmapHalfSize);
    }

    void SetFPS(int FPS)
    {
        m_FrameFrequency = 1.f / (float)(FPS);
    }

    void Update(float DeltaTime)
    {
        m_FrameTimer += DeltaTime;
        if (m_FrameTimer > m_FrameFrequency)
        {
            m_FrameTimer-= m_FrameFrequency;
            m_CurrentFrame = (m_CurrentFrame + 1) % m_FrameCount;
            m_SourceRect.left = (m_CurrentFrame % m_SpritesPerRow) * m_SpriteWidth;
            m_SourceRect.top = (m_CurrentFrame / m_SpritesPerRow) * m_SpriteHeight;
        }
    }
}
