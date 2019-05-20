package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Objects.Components.Collision;
import yjj.nanasreunion.Vector2d;

public class Graphics
{
    protected Bitmap            m_Bitmap;
    protected Vector2d          m_Offset;

    // checks whether the bitmap will be seen in the screen or not (if not, then skip rendering)
    private   Collision         m_BitmapBorder;

    protected Vector2d          m_BitmapHalfSize;

    protected Graphics()
    {
    }

    public Graphics(Bitmap bitmap)
    {
        SetBitmap(bitmap);
        SetOffset(new Vector2d(0.f, 0.f));
    }

    public void SetBitmap(Bitmap bitmap)
    {
        m_Bitmap = bitmap;
        m_BitmapHalfSize = new Vector2d(m_Bitmap.getWidth()  * 0.5f,
                                        m_Bitmap.getHeight() * 0.5f);
    }

    public void SetOffset(Vector2d Offset)
    {
        m_Offset = Offset;
    }

    public void Draw(Canvas canvas, Vector2d ScreenPosition)
    {
        //check if ScreenPos is inside or out + counting Bitmap offsets to check if to draw the
        // object or not

        canvas.drawBitmap(m_Bitmap,
                ScreenPosition.x + m_Offset.x - m_BitmapHalfSize.x,
                ScreenPosition.y + m_Offset.y - m_BitmapHalfSize.y,
                null);
    }
}
