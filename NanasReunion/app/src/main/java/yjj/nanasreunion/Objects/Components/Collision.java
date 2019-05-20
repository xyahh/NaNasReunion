package yjj.nanasreunion.Objects.Components;

import android.graphics.Canvas;

import yjj.nanasreunion.Vector2d;

public class Collision
{
    private Vector2d m_Position;
    private Vector2d m_Extents;

    public Collision(Vector2d Position, Vector2d Extents)
    {
        m_Position = Position;
        m_Extents = Extents;
    }

    Vector2d GetMin()
    {
        return new Vector2d( m_Position.x - m_Extents.x,
                                m_Position.y - m_Extents.y);
    }

    Vector2d GetMax()
    {
        return new Vector2d( m_Position.x + m_Extents.x,
                m_Position.y + m_Extents.y);
    }

    public void Draw(Canvas canvas)
    {

    }

    public static boolean Check(Collision collisionA, Collision collisionB)
    {
        Vector2d A_min = collisionA.GetMin();
        Vector2d A_max = collisionA.GetMax();

        Vector2d B_min = collisionB.GetMin();
        Vector2d B_max = collisionB.GetMax();

        return !( A_min.x > B_max.x
                || B_min.x > A_max.x
                || A_min.y > B_max.y
                || B_min.y > A_max.y);
    }

    public static boolean Check(Collision collision, Vector2d point)
    {
        Vector2d Max = collision.GetMax();
        Vector2d Min = collision.GetMin();
        return !( point.x > Max.x
                || point.x < Min.x
                || point.y > Max.y
                || point.y < Min.y);
    }
}
