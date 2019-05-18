package yjj.nanasreunion.Actors;

/* Core Classes */
import yjj.nanasreunion.MyStack;
import yjj.nanasreunion.Vector2d;


/* Component Classes */
import yjj.nanasreunion.Actors.Components.*;
import yjj.nanasreunion.Actors.Components.Physics.*;
import yjj.nanasreunion.Actors.Components.State.*;
import android.graphics.Canvas;

public class Actor
{

    public Vector2d             position;

    public Graphics             graphics;
    public Physics              physics;



    public Actor()
    {
        physics     = new NullPhysics();

    }

    public void Update(float DeltaTime)
    {
        physics.Update(this, DeltaTime);
    }

    public void Draw(Canvas canvas, Camera camera, float interp)
    {
        graphics.Draw(canvas, camera.GetScreenPosition(position));
    }

}
