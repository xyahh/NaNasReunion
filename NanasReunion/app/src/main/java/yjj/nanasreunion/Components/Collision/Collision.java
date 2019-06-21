package yjj.nanasreunion.Components.Collision;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.RectGraphics;
import yjj.nanasreunion.Vector2f;

public class Collision
{
    private RectF  m_CollisionRect;
    //private RectGraphics m_DebugRect;

    private float m_Width;
    private float m_Height;
    private boolean m_CollisionEnabled;

    private static Paint m_Paint = new Paint() {
        {
            setStyle(Style.STROKE);
            setColor(Color.RED);
        }
    };

    public Collision()
    {
        m_CollisionRect = new RectF();
        m_CollisionEnabled = true;
       // m_DebugRect = new RectGraphics(m_Paint);
    }

    public void SetDimensions(float width, float height)
    {
        m_Width = width;
        m_Height = height;
       // m_DebugRect.SetDimensions(m_Width, m_Height);
    }

    public Vector2f GetDimensions()
    {
        return new Vector2f(m_Width, m_Height);
    }

    public void Draw(Canvas canvas, Camera camera, Vector2f pivot)
    {
       // m_DebugRect.Draw(canvas, camera,
       //         new Vector2f(m_CollisionRect.left, m_CollisionRect.top),
       //         m_Width, m_Height, pivot, m_Paint);
    }

    public void UpdateCollisionRect(Vector2f world_position)
    {
        m_CollisionRect.left = world_position.x;
        m_CollisionRect.right = world_position.x + m_Width;

        m_CollisionRect.top = world_position.y ;
        m_CollisionRect.bottom = world_position.y +  m_Height;
    }

    public void SetCollisionEnabled(boolean flag)
    {
        m_CollisionEnabled = flag;
    }

    public static boolean Check(Collision collisionA, Collision collisionB)
    {
        return collisionA.m_CollisionRect.intersect(collisionB.m_CollisionRect)
                && collisionA.m_CollisionEnabled && collisionB.m_CollisionEnabled;
    }

    public static boolean Check(Collision collision, Vector2f point)
    {
        return collision.m_CollisionRect.contains(point.x, point.y)
                && collision.m_CollisionEnabled;
    }

    public static boolean Check(Collision collision,
                                float left, float top, float right, float bottom)
    {
       return collision.m_CollisionRect.intersect(left, top, right, bottom)
               && collision.m_CollisionEnabled;
    }

}
