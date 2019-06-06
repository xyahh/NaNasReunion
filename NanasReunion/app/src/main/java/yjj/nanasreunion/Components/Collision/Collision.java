package yjj.nanasreunion.Components.Collision;
import yjj.nanasreunion.Vector2f;

public class Collision
{
    private Vector2f m_Position;
    private Vector2f m_Extents;
    private boolean m_CollisionEnabled;

    public Collision(Vector2f Position, Vector2f Extents)
    {
        m_Position = Position;
        m_Extents  = Extents;
        m_CollisionEnabled = true;
    }

    public Vector2f GetMin()
    {
        return new Vector2f( m_Position.x - m_Extents.x,
                                m_Position.y - m_Extents.y);
    }

    public Vector2f GetMax()
    {
        return new Vector2f( m_Position.x + m_Extents.x,
                m_Position.y + m_Extents.y);
    }

    public void UpdatePosition(Vector2f position)
    {
        m_Position = position;
    }

    public void SetCollisionEnabled(boolean flag)
    {
        m_CollisionEnabled = flag;
    }

    public static boolean Check(Collision collisionA, Collision collisionB)
    {
        Vector2f A_min = collisionA.GetMin();
        Vector2f A_max = collisionA.GetMax();

        Vector2f B_min = collisionB.GetMin();
        Vector2f B_max = collisionB.GetMax();

        return collisionA.m_CollisionEnabled &&
                collisionB.m_CollisionEnabled &&
                !(A_min.x > B_max.x
                || B_min.x > A_max.x
                || A_min.y > B_max.y
                || B_min.y > A_max.y);
    }

    public static boolean Check(Collision collision, Vector2f point)
    {
        Vector2f Max = collision.GetMax();
        Vector2f Min = collision.GetMin();

        return collision.m_CollisionEnabled &&
                !( point.x > Max.x
                || point.x < Min.x
                || point.y > Max.y
                || point.y < Min.y);
    }

    public static boolean Check(Collision collision,
                                float left, float top, float right, float bottom)
    {
        Vector2f Max = collision.GetMax();
        Vector2f Min = collision.GetMin();

        return collision.m_CollisionEnabled &&
                !( left > Max.x
                        || right < Min.x
                        || top > Max.y
                        || bottom < Min.y);
    }

}
