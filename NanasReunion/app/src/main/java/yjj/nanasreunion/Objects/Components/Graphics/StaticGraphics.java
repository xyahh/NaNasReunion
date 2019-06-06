package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Vector2d;

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
    public void Draw(Canvas canvas, Camera camera, Vector2d worldPos, float interp)
    {
        super.Draw(canvas, camera, worldPos, interp);
        Draw(canvas, m_Bitmap, m_DrawMatrix, camera, worldPos);
    }
}
