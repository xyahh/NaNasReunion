package yjj.nanasreunion.Components.Graphics;

import android.gesture.OrientedBoundingBox;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

import java.lang.reflect.Array;
import java.util.ArrayList;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class RectGraphics extends Graphics
{
    private float m_Width;
    private float m_Height;
    private Paint m_Paint;

    public RectGraphics(Paint paint)
    {
        super();
        m_Paint = paint;
    }

    public void SetDimensions(float width, float height)
    {
        m_Width = width;
        m_Height = height;
        SetDrawSize(m_Width, m_Height);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2f WorldPosition, Vector2f pivot, float interp)
    {
        Draw(canvas, camera, WorldPosition, m_Width, m_Height, pivot, m_Paint);
    }

    public void Draw(Canvas canvas, Camera camera, Vector2f WorldPosition, float width, float height, Vector2f pivot, Paint paint)
    {
        Vector2i ScreenSpace = camera.GetScreenSpace(WorldPosition);

        float sx = camera.DeltaXToPixels(width);
        float sy = camera.DeltaYToPixels(height);

        float Left = ScreenSpace.x - (pivot.x)* sx;
        float Right = ScreenSpace.x + (1.f - pivot.x) * sx;

        float Top = ScreenSpace.y - (pivot.y) * sy;
        float Bottom = ScreenSpace.y + (1.f - pivot.y) * sy;

        canvas.drawRect(Left, Top, Right, Bottom, paint);
    }
}
