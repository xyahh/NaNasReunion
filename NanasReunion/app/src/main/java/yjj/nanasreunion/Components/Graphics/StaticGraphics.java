package yjj.nanasreunion.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Vector2f;

public class StaticGraphics extends Graphics
{
    protected Bitmap m_Bitmap;

    public StaticGraphics(Bitmap bitmap)
    {
        super();
        m_Bitmap = bitmap;
        SetDrawSize(m_Bitmap.getWidth(), m_Bitmap.getHeight());
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2f worldPos, Vector2f pivot, float interp)
    {
        super.Draw(canvas, camera, worldPos, pivot, interp);
        Draw(canvas, m_Bitmap, camera, worldPos, pivot);
    }
}
