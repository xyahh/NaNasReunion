package yjj.nanasreunion.Objects;

import java.util.Vector;

import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.NullGraphics;
import yjj.nanasreunion.Vector2f;

public class Object
{
    public Graphics graphics;
    public Vector2f position;
    public Vector2f pivot;

    protected Object()
    {
        graphics    = new NullGraphics();
        position    = new Vector2f(0.f, 0.f);
        pivot       = new Vector2f(0.5f, 0.5f);
    }
}
