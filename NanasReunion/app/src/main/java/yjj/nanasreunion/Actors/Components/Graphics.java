package yjj.nanasreunion.Actors.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Vector2d;

public class Graphics
{
    protected Bitmap            m_Bitmap;
    protected Vector2d          m_Offset;

    public Graphics(Bitmap bitmap)
    {
        m_Bitmap = bitmap;
        m_Offset.Set(0.f, 0.f);
    }

    public void SetOffset(Vector2d Offset)
    {
        m_Offset.Set(Offset);
    }

    public void Draw(Canvas canvas, Vector2d ScreenPosition)
    {
        canvas.drawBitmap(m_Bitmap,
                ScreenPosition.x + m_Offset.x,
                ScreenPosition.y + m_Offset.y,
                null);
    }
}
