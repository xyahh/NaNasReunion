package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Objects.Components.Collision.Collision;
import yjj.nanasreunion.Vector2d;

public class Graphics
{
    protected Bitmap            m_Bitmap;
    protected Rect              m_SourceRect;
    // checks whether the bitmap will be seen in the screen or not (if not, then skip rendering)
    protected Vector2d          m_BitmapHalfSize;
    protected Collision         m_BitmapBox;

    protected float             m_Scale;

    protected Graphics()
    {
        //only called by Child classes (e.g. NullGraphics)
    }

    public Graphics(Bitmap bitmap, float Scale)
    {
        SetBitmap(bitmap, Scale);
    }

    public void SetBitmap(Bitmap bitmap, float Scale)
    {
        m_Bitmap = bitmap;

        int Width = m_Bitmap.getWidth();
        int Height = m_Bitmap.getHeight();

        m_SourceRect        = new Rect(0, 0, Width, Height);
        m_BitmapHalfSize    = new Vector2d(Width  * 0.5f * Scale,
                                            Height * 0.5f * Scale);
        m_BitmapBox         = new Collision(new Vector2d(), m_BitmapHalfSize);
    }

    public void Draw(Canvas canvas, Camera camera, Vector2d WorldPosition, float interp)
    {
        Vector2d ScreenPosition = camera.ToScreenSpace(WorldPosition);
        m_BitmapBox.SetPosition(ScreenPosition);
        if(camera.AppearsOnScreen(m_BitmapBox))
        {
            int X1 = (int)(ScreenPosition.x - m_BitmapHalfSize.x);
            int X2 = (int)(ScreenPosition.x + m_BitmapHalfSize.x);

            int Y1 = (int)(ScreenPosition.y - m_BitmapHalfSize.y);
            int Y2 = (int)(ScreenPosition.y + m_BitmapHalfSize.y);

            Rect dest = new Rect(X1, Y1, X2, Y2);
            canvas.drawBitmap(m_Bitmap, m_SourceRect, dest, null);
        }
    }
}
