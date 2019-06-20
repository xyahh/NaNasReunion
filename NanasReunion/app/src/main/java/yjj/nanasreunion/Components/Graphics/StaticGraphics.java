package yjj.nanasreunion.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Vector2f;

public class StaticGraphics extends Graphics
{
    protected Bitmap m_Bitmap;

    public StaticGraphics(Bitmap bitmap)
    {
        this(bitmap, new Rect(0, 0, 0, 0));
    }

    public StaticGraphics(Bitmap bitmap, Rect Padding)
    {
        super();
        m_Bitmap = bitmap;

        int width = m_Bitmap.getWidth();
        int height = m_Bitmap.getHeight();

        SetDrawSize( width - (Padding.left + Padding.right),
                height - (Padding.top + Padding.bottom));

        m_Bitmap = Bitmap.createBitmap(bitmap,
                Padding.left,
                Padding.top,
                width - Padding.right,
                height - Padding.bottom);

    }


    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2f worldPos, Vector2f pivot, float interp)
    {
        super.Draw(canvas, camera, worldPos, pivot, interp);
        Draw(canvas, m_Bitmap, camera, worldPos, pivot);
    }
}
