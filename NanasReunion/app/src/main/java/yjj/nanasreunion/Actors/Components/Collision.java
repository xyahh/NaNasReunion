package yjj.nanasreunion.Actors.Components;

import yjj.nanasreunion.Vector2d;

public class Collision
{
    private Vector2d m_Position;
    private Vector2d m_Extents;

    public void Configure(Vector2d Position, Vector2d Extents)
    {
        m_Position = Position;
        m_Extents = Extents;
    }
    public static boolean Check(Collision collisionA,
                                Collision collisionB)
    {

        return false;
    }
}
