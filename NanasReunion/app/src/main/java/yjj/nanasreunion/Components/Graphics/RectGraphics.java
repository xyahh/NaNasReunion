package yjj.nanasreunion.Components.Graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class RectGraphics extends Graphics
{
    Vector2i m_Size;
    Paint m_Paint;

    public RectGraphics(Vector2i size, Paint paint)
    {
        super();
        m_Size = size;
        m_Paint = paint;
    }

    @Override
    public void Update(float DeltaTime) {
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2f WorldPosition, float interp)
    {
        float dpi = ServiceHub.Inst().GetDPI();
        int sz_x = (int)(m_Size.x * dpi);
        int sz_y = (int)(m_Size.y * dpi);

        Rect rect = new Rect
                (
                        (int)(WorldPosition.x),
                        (int)(WorldPosition.y),
                        (int)(WorldPosition.x) + m_Size.x,
                        (int)(WorldPosition.y) + m_Size.y
                );
        canvas.drawRect(rect, m_Paint);
    }
}
