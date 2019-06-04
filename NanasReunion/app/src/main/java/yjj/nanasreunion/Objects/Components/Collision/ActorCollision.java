package yjj.nanasreunion.Objects.Components.Collision;

import java.util.HashMap;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Vector2d;

public class ActorCollision extends Collision
{

    private HashMap<COLLISION_TYPES, Command>   m_CollisionCommands;
    private COLLISION_TYPES                     m_CollisionType;

    public ActorCollision(Vector2d Position, Vector2d Extents)
    {
        super(Position, Extents);
        m_CollisionCommands = new HashMap<>();
        SetCollisionType(COLLISION_TYPES.DEFAULT);
    }

    public COLLISION_TYPES GetCollisionType()
    {
        return m_CollisionType;
    }

    public void SetCollisionType(COLLISION_TYPES collisionType)
    {
        m_CollisionType = collisionType;
    }

    public void AddCollisionCommand(COLLISION_TYPES CollisionType, Command e)
    {
        m_CollisionCommands.put(CollisionType, e);
    }

    public static Command ProcessCollision(ActorCollision a, ActorCollision b)
    {
        if(Collision.Check(a, b))
        {
            return  a.m_CollisionCommands.get(b.GetCollisionType());
        }
        return null;
    }
}
