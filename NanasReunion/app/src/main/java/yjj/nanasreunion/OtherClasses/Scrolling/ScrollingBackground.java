package yjj.nanasreunion.OtherClasses.Scrolling;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class ScrollingBackground
{
    private ArrayList<ScrollingObject> m_ScrollingObjects;


    public ScrollingBackground()
    {
        m_ScrollingObjects = new ArrayList<>();
    }

    public void AddScrollingObject(Bitmap bitmap, float StartY, Vector2f AbsoluteSpeed, float TargetRelativeSpeed, Vector2i DesiredScreenSize)
    {
        m_ScrollingObjects.add(new ScrollingObject(bitmap, StartY, AbsoluteSpeed, TargetRelativeSpeed, DesiredScreenSize));
    }

    public void Update(Camera camera, float deltaTime)
    {
        for(ScrollingObject o : m_ScrollingObjects)
            o.UpdateBackground(camera, deltaTime);
    }


    public void DrawObjects(Canvas canvas, Paint paint, Camera camera)
    {
        for(ScrollingObject o : m_ScrollingObjects)
            o.Draw(canvas, paint, camera);
    }

}
