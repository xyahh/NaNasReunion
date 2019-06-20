package yjj.nanasreunion.Objects;

/* Core Classes */
import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Components.Collision.ActorCollision;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Physics.NullPhysics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;


/* Component Classes */
import yjj.nanasreunion.Components.*;

import android.graphics.Canvas;

import java.util.ArrayList;

public class  Actor extends Object
{
    public Physics          physics;
    public ActorCollision   collision;
    private boolean        m_Destroy;

    public Actor()
    {
        super();
        physics = new NullPhysics();
        collision   = new ActorCollision(COLLISION_TYPES.DEFAULT);
        collision.SetCollisionEnabled(true);
        m_Destroy = false;
    }

    public void DestroyActor()
    {
        m_Destroy = true;
    }

    public void ProcessCollision(Actor other)
    {
        if(other != this)
        {
            ArrayList<Command> cmds = ActorCollision.ProcessCollision(this.collision, other.collision);
            for(Command c : cmds)
                c.Execute(this, other);
        }
    }

    public boolean IsDestroyed()
    {
        return m_Destroy;
    }

    public void Update(float DeltaTime)
    {
        physics.Update(this, DeltaTime);
        graphics.Update(DeltaTime);
        collision.UpdateCollisionRect(position);
    }

    public void Draw(Canvas canvas, Camera camera, float interp)
    {
        graphics.Draw(canvas, camera, position, pivot, interp);
        collision.Draw(canvas, camera, pivot);
    }

}
