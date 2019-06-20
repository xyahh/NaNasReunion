package yjj.nanasreunion.OtherClasses.Scrolling;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.TreeMap;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class ScrollingBackground
{
    private TreeMap<String, ScrollingObject> m_ScrollingObjects;


    public ScrollingBackground()
    {
        m_ScrollingObjects = new TreeMap<>();
    }

    public void SetScrollingObject(String name, Bitmap bitmap, float StartY, Vector2f AbsoluteSpeed, float TargetRelativeSpeed, Vector2i DesiredScreenSize)
    {
        m_ScrollingObjects.put(name, new ScrollingObject(bitmap, StartY, AbsoluteSpeed, TargetRelativeSpeed, DesiredScreenSize));
    }

    public void SetScrollingObject(String name, ScrollingObject so)
    {
        m_ScrollingObjects.put(name, so);
    }

    public ScrollingObject GetScrollingObject(String name)
    {
        return m_ScrollingObjects.get(name);
    }

    public void Update(Camera camera, float deltaTime)
    {
        for(ScrollingObject o : m_ScrollingObjects.values())
            o.UpdateBackground(camera, deltaTime);
    }

    public void DrawObjects(Canvas canvas, Paint paint, Camera camera)
    {
        for(ScrollingObject o : m_ScrollingObjects.values())
            o.Draw(canvas, paint, camera);
    }

}
