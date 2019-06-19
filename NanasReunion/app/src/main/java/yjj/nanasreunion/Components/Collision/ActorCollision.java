package yjj.nanasreunion.Components.Collision;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.NullCommand;
import yjj.nanasreunion.Vector2f;

public class ActorCollision extends Collision
{

    private HashMap<COLLISION_TYPES, ArrayList<Command>>    m_CollisionCommands;
    private COLLISION_TYPES                                 m_CollisionType;

    public ActorCollision(COLLISION_TYPES type)
    {
        super();
        m_CollisionCommands = new HashMap<>();
        SetCollisionType(type);
    }

    public COLLISION_TYPES GetCollisionType()
    {
        return m_CollisionType;
    }

    public void SetCollisionType(COLLISION_TYPES collisionType)
    {
        m_CollisionType = collisionType;
    }

    public void AddCollisionCommand(COLLISION_TYPES CollisionType, ArrayList<Command> e)
    {
        m_CollisionCommands.put(CollisionType, e);
    }

    public static ArrayList<Command> ProcessCollision(ActorCollision a, ActorCollision b)
    {
        if(Collision.Check(a, b))
        {
            ArrayList<Command> commands =  a.m_CollisionCommands.get(b.GetCollisionType());
            if(commands == null) return new ArrayList<Command>();
            return commands;
        }
        return new ArrayList<Command>();
    }
}
