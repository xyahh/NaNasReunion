package yjj.nanasreunion;

public class Vector2f
{
    public float x;
    public float y;

    public Vector2f()
    {
        this.x = 0.f;
        this.y = 0.f;
    }
    public Vector2f(float x, float y)
    {
      this.x = x;
      this.y = y;
    }
    public Vector2f(Vector2f other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2i toInt()
    {
        return new Vector2i((int)(this.x), (int)(this.y));
    }

    public static Vector2f Scale(Vector2f v, float scale)
    {
        return new Vector2f(v.x * scale, v.y * scale);
    }

    public static Vector2f Scale(Vector2f v, float scaleX, float scaleY)
    {
        return new Vector2f(v.x * scaleX, v.y * scaleY);
    }

    public static Vector2f Add(Vector2f a, Vector2f b)
    {
        return new Vector2f(a.x + b.x, a.y + b.y);
    }

    public static Vector2f Subtract(Vector2f a, Vector2f b)
    {
        return new Vector2f(a.x - b.x, a.y - b.y);
    }


}