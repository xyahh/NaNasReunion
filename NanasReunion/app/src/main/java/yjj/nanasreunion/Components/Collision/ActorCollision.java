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

    public ArrayList<Command> GetCollisionCommands(COLLISION_TYPES collisionType)
    {
        return m_CollisionCommands.get(collisionType);
    }

    public void ClearCollisionCommands(COLLISION_TYPES collisionTypes)
    {
        ArrayList<Command> c = m_CollisionCommands.get(collisionTypes);
        if(c!=null)
            c.clear();
    }

    public void SetCollisionCommands(COLLISION_TYPES CollisionType, ArrayList<Command> e)
    {
        m_CollisionCommands.put(CollisionType, e);
    }

    public void AddCollisionCommands(COLLISION_TYPES CollisionType, ArrayList<Command> e)
    {
        ArrayList<Command> c = m_CollisionCommands.get(CollisionType);
        if(c==null)
            c = e;
        else
            c.addAll(e);
        SetCollisionCommands(CollisionType, c);
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
