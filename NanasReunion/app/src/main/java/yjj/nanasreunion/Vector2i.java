package yjj.nanasreunion;

public class Vector2i
{
    public int x;
    public int y;

    public Vector2i()
    {
        this.x = 0;
        this.y = 0;
    }
    public Vector2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2i(Vector2i other)
    {
        this.x = other.x;
        this.y = other.y;
    }

   public Vector2f toFloat()
    {
        return new Vector2f(this.x, this.y);
    }

    public static Vector2i Scale(Vector2i v, int scale)
    {
        return new Vector2i(v.x * scale, v.y * scale);
    }

}