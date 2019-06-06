package yjj.nanasreunion.Objects;

import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.NullGraphics;
import yjj.nanasreunion.Vector2f;

public class Object
{
    public Graphics     graphics;
    public Vector2f position;

    Object()
    {
        graphics    = new NullGraphics();
        position    = new Vector2f(0.f, 0.f);
    }

}
