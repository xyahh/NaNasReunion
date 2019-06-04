package yjj.nanasreunion.Objects.Components.Collision;

import android.graphics.Canvas;

import yjj.nanasreunion.Vector2d;

enum COLLISION_TYPES
{
    DEFAULT,
    OBSTACLE,
    DESTRUCTIVE,
    EDIBLE
}

public class Collision
{
    private Vector2d m_Position;
    private Vector2d m_Extents;
    private boolean m_CollisionEnabled;

    public Collision(Vector2d Position, Vector2d Extents)
    {
        m_Position = Position;
        m_Extents  = Extents;
        m_CollisionEnabled = true;
    }

    public Vector2d GetMin()
    {
        return new Vector2d( m_Position.x - m_Extents.x,
                                m_Position.y - m_Extents.y);
    }

    public Vector2d GetMax()
    {
        return new Vector2d( m_Position.x + m_Extents.x,
                m_Position.y + m_Extents.y);
    }

    public void Draw(Canvas canvas)
    {
        //ALSO DRAW IF COLLISION IS DISABLED BUT IN ANOTHER COLOR

    }

    public void SetPosition(Vector2d position)
    {
        m_Position = position;
    }

    public void SetCollisionEnabled(boolean flag)
    {
        m_CollisionEnabled = flag;
    }

    public static boolean Check(Collision collisionA, Collision collisionB)
    {
        Vector2d A_min = collisionA.GetMin();
        Vector2d A_max = collisionA.GetMax();

        Vector2d B_min = collisionB.GetMin();
        Vector2d B_max = collisionB.GetMax();

        return collisionA.m_CollisionEnabled &&
                collisionB.m_CollisionEnabled &&
                !(A_min.x > B_max.x
                || B_min.x > A_max.x
                || A_min.y > B_max.y
                || B_min.y > A_max.y);
    }

    public static boolean Check(Collision collision, Vector2d point)
    {
        Vector2d Max = collision.GetMax();
        Vector2d Min = collision.GetMin();

        return collision.m_CollisionEnabled &&
                !( point.x > Max.x
                || point.x < Min.x
                || point.y > Max.y
                || point.y < Min.y);
    }

    public static boolean Check(Collision collision,
                                float left, float top, float right, float bottom)
    {
        Vector2d Max = collision.GetMax();
        Vector2d Min = collision.GetMin();

        return collision.m_CollisionEnabled &&
                !( left > Max.x 
                        || right < Min.x
                        || top > Max.y
                        || bottom < Min.y);
    }

}
