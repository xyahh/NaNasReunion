package yjj.nanasreunion.Components.Collision;

import java.util.HashMap;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.NullCommand;
import yjj.nanasreunion.Vector2f;

public class ActorCollision extends Collision
{

    private HashMap<COLLISION_TYPES, Command>   m_CollisionCommands;
    private COLLISION_TYPES                     m_CollisionType;

    static private final NullCommand          m_NullCommand = new NullCommand();

    public ActorCollision(Vector2f Position, Vector2f Extents)
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
        return m_NullCommand;
    }
}
