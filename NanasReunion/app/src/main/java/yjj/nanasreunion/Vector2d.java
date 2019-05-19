package yjj.nanasreunion;

public class Vector2d
{
    public float x;
    public float y;

    public Vector2d()
    {
        this.x = 0.f;
        this.y = 0.f;
    }
    public Vector2d(float x, float y)
    {
      this.x = x;
      this.y = y;
    }
    public Vector2d(Vector2d other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    public static Vector2d Scale(Vector2d v, float scale)
    {
        return new Vector2d(v.x * scale, v.y * scale);
    }

    public static Vector2d Add(Vector2d a, Vector2d b)
    {
        return new Vector2d(a.x + b.x, a.y + b.y);
    }

    public static Vector2d Subtract(Vector2d a, Vector2d b)
    {
        return new Vector2d(a.x - b.x, a.y - b.y);
    }


}