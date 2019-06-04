package yjj.nanasreunion.Objects;

/* Core Classes */
import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Objects.Components.Collision.ActorCollision;
import yjj.nanasreunion.Vector2d;


/* Component Classes */
import yjj.nanasreunion.Objects.Components.*;
import yjj.nanasreunion.Objects.Components.Physics.*;
import yjj.nanasreunion.Objects.Components.Graphics.*;

import android.graphics.Canvas;

public class Actor
{
    public Vector2d     position;
    public Graphics     graphics;
    public Physics      physics;
    public ActorCollision collision;

    private boolean     m_Destroy;

    public Actor()
    {
        position    = new Vector2d(0.f, 0.f);
        physics     = new NullPhysics();
        graphics    = new NullGraphics();

        collision   = new ActorCollision(position, new Vector2d());
        collision.SetCollisionEnabled(false);

        m_Destroy = false;
    }

    public void Destroy()
    {
        m_Destroy = true;
    }

    public void ProcessCollision(Actor other)
    {
        Command c = ActorCollision.ProcessCollision(this.collision, other.collision);
        if(c!=null) c.Execute(this);
    }

    public boolean IsDestroyed()
    {
        return m_Destroy;
    }

    public void Update(float DeltaTime)
    {
        physics.Update(this, DeltaTime);
    }

    public void Draw(Canvas canvas, Camera camera, float interp)
    {
        graphics.Draw(canvas, camera, position, interp);
    }

}
