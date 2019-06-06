package yjj.nanasreunion.Objects;

/* Core Classes */
import yjj.nanasreunion.Components.Collision.ActorCollision;
import yjj.nanasreunion.Vector2f;


/* Component Classes */
import yjj.nanasreunion.Components.*;
import yjj.nanasreunion.Components.Physics.*;

import android.graphics.Canvas;

public class Actor extends Object
{

    public Physics          physics;
    public ActorCollision   collision;

    private boolean         m_Destroy;

    public Actor()
    {
        super();
        physics     = new NullPhysics();

        collision   = new ActorCollision(position, new Vector2f());
        collision.SetCollisionEnabled(false);

        m_Destroy = false;
    }

    public void DestroyActor()
    {
        m_Destroy = true;
    }

    public void ProcessCollision(Actor other)
    {
        if(other != this)
            ActorCollision.ProcessCollision(this.collision, other.collision).Execute(this);
    }

    public boolean IsDestroyed()
    {
        return m_Destroy;
    }

    public void Update(float DeltaTime)
    {
        physics.Update(this, DeltaTime);
        graphics.Update(DeltaTime);
        collision.UpdatePosition(position);
    }

    public void Draw(Canvas canvas, Camera camera, float interp)
    {
        graphics.Draw(canvas, camera, position, interp);
    }

}
