package yjj.nanasreunion.Objects;

/* Core Classes */
import yjj.nanasreunion.Vector2d;


/* Component Classes */
import yjj.nanasreunion.Objects.Components.*;
import yjj.nanasreunion.Objects.Components.Physics.*;
import yjj.nanasreunion.Objects.Components.Graphics.*;

import android.graphics.Canvas;

public class Actor
{

    public Vector2d position;
    public Graphics graphics;
    public Physics  physics;


    public Actor()
    {
        physics     = new NullPhysics();
        graphics    = new NullGraphics();
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
