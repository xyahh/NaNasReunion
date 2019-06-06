package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Vector2d;

public class SpriteGraphics extends Graphics
{
    private ArrayList<Bitmap> m_Sprites;

    private float   m_FrameFrequency; // 1.f / FPS
    private int     m_FrameCount;

    private int     m_CurrentFrame;
    private float   m_FrameTimer;


    public SpriteGraphics(Bitmap bitmap, int FPS, int TotalFrames, int SpritesPerRow)
    {
        super();
        m_Sprites = new ArrayList<>();

        m_FrameCount = TotalFrames;
        int ExtraRow = 0;
        if(m_FrameCount % SpritesPerRow != 0) ExtraRow = 1;

        int SpriteWidth = bitmap.getWidth() / SpritesPerRow;
        int SpriteHeight = bitmap.getHeight() / ((m_FrameCount / SpritesPerRow) + ExtraRow);

        SetDrawSize(SpriteWidth, SpriteHeight);

        for(int i = 0; i < m_FrameCount; ++i)
        {
            int x = (i % SpritesPerRow) * SpriteWidth;
            int y = (i / SpritesPerRow) * SpriteHeight;
            m_Sprites.add(Bitmap.createBitmap(bitmap, x, y, SpriteWidth, SpriteHeight));
        }
        SetFPS(FPS);
    }


    public void SetFPS(int FPS)
    {
        m_FrameFrequency = 1.f / (float)(FPS);
    }

    @Override
    public void Update(float DeltaTime)
    {
        angle += 5.f;
        if(angle > 360.f)
            angle -=360.f;
        m_DrawMatrix.setTranslate(-m_DrawHalfSize.x, -m_DrawHalfSize.y);
        m_DrawMatrix.postRotate(angle);

        m_FrameTimer += DeltaTime;
        if (m_FrameTimer > m_FrameFrequency)
        {
            m_FrameTimer-= m_FrameFrequency;
            m_CurrentFrame = (m_CurrentFrame + 1) % m_FrameCount;
        }
    }

    float angle = 0.f;
    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2d WorldPosition, float interp)
    {
        super.Draw(canvas, camera, WorldPosition, interp);
        Draw(canvas, m_Sprites.get(m_CurrentFrame), m_DrawMatrix, camera, WorldPosition);
    }
}
