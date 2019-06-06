package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import yjj.nanasreunion.Objects.Components.Camera;
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

    public SpriteGraphics(Bitmap bitmap, int FPS, int TotalFrames, int SpritesPerRow, float scale)
    {
        super(bitmap);
        m_FrameCount = TotalFrames;
        m_SpritesPerRow = SpritesPerRow;
        Scale(scale);
        SetFPS(FPS);
    }

    @Override
    public void Scale(float scale)
    {
        Log.d ("Hello", "Z");

        ScaleBitmap(scale);
        Log.d ("Hello", "A");

        SetSprite();
        Log.d ("Hello", "B");

        UpdateOutRects();
        Log.d ("Hello", "C");
    }

    @Override
    public void UpdateOutRects()
    {
        m_OutHalfSize       = new Vector2d(m_SpriteWidth  * 0.5f, m_SpriteHeight * 0.5f);
        m_Boundaries        = new Collision(new Vector2d(), m_OutHalfSize);
    }

    private void SetSprite()
    {
        m_FrameTimer = 0.f;
        m_CurrentFrame = 0;

        int WIDTH = m_Bitmap.getWidth();
        int HEIGHT = m_Bitmap.getHeight();

        int ExtraRow = 0;
        if(m_FrameCount % m_SpritesPerRow != 0)  ExtraRow = 1;

        m_SpriteWidth = WIDTH / m_SpritesPerRow;
        m_SpriteHeight = HEIGHT / ((m_FrameCount / m_SpritesPerRow) + ExtraRow);
        m_SourceRect        = new Rect(0, 0, WIDTH, HEIGHT);
    }

    public void SetFPS(int FPS)
    {
        m_FrameFrequency = 1.f / (float)(FPS);
    }

    @Override
    public void Update(float DeltaTime)
    {
        m_FrameTimer += DeltaTime;
        if (m_FrameTimer > m_FrameFrequency)
        {
            m_FrameTimer-= m_FrameFrequency;
            m_CurrentFrame = (m_CurrentFrame + 1) % m_FrameCount;

            m_SourceRect.left = (m_CurrentFrame % m_SpritesPerRow) * m_SpriteWidth;
            m_SourceRect.top = (m_CurrentFrame / m_SpritesPerRow) * m_SpriteHeight;
            m_SourceRect.right = m_SourceRect.left + m_SpriteWidth;
            m_SourceRect.bottom = m_SourceRect.top + m_SpriteHeight;
        }
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2d WorldPosition, float interp) {
        super.Draw(canvas, camera, WorldPosition, interp);
    }
}
