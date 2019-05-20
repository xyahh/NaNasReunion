package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Vector2d;

public class Graphics
{
    protected Bitmap            m_Bitmap;
    protected Vector2d          m_Offset;

    protected Graphics()
    {
    }

    public Graphics(Bitmap bitmap)
    {
        m_Bitmap = bitmap;
        m_Offset = new Vector2d(0.f, 0.f);
    }

    public void SetBitmap(Bitmap bitmap)
    {
        m_Bitmap = bitmap;
    }

    public void SetOffset(Vector2d Offset)
    {
        m_Offset = new Vector2d(Offset);
    }

    public void Draw(Canvas canvas, Vector2d ScreenPosition)
    {
        //check if ScreenPos is inside or out + counting Bitmap offsets to check if to draw the
        // object or not


        canvas.drawBitmap(m_Bitmap,
                ScreenPosition.x + m_Offset.x,
                ScreenPosition.y + m_Offset.y,
                null);
    }
}
