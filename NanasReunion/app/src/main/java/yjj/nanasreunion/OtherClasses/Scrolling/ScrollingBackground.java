package yjj.nanasreunion.OtherClasses.Scrolling;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class ScrollingBackground
{
    private ArrayList<ScrollingObject> m_ScrollingObjects;


    public ScrollingBackground()
    {
        m_ScrollingObjects = new ArrayList<>();
    }

    public void AddScrollingObject(Bitmap bitmap, float StartY, float TargetRelativeSpeed, Vector2i DesiredScreenSize)
    {
        m_ScrollingObjects.add(new ScrollingObject(bitmap, StartY, TargetRelativeSpeed, DesiredScreenSize));
    }

    public void Update(Camera camera, float deltaTime)
    {
        for(ScrollingObject o : m_ScrollingObjects)
            o.UpdateBackground(camera, deltaTime);
    }

    private void DrawScrollingObject(Canvas canvas, Paint paint, Camera camera, ScrollingObject bg)
    {
        // define what portion of images to capture and
        // what coordinates of screen to draw them at
        Vector2i v = camera.ScreenSpace(new Vector2f(0.f, bg.m_StartY));
        int deltaX = (int)(bg.m_DeltaX);
        int posY =  v.y;
        int endY = posY + bg.m_Height;

        // For the regular bitmap
        Rect fromRect1 = new Rect(0, 0, bg.m_Width - deltaX, bg.m_Height);
        Rect toRect1 = new Rect(deltaX, posY, bg.m_Width, endY);

        // For the reversed background
        Rect fromRect2 = new Rect(bg.m_Width - deltaX, 0, bg.m_Width, bg.m_Height);
        Rect toRect2 = new Rect(0, posY, deltaX, endY);

        //draw the two background bitmaps
        if (!bg.m_ReversedFirst) {
            canvas.drawBitmap(bg.m_Bitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(bg.m_ReversedBitmap, fromRect2, toRect2, paint);
        } else {
            canvas.drawBitmap(bg.m_Bitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(bg.m_ReversedBitmap, fromRect1, toRect1, paint);
        }
    }

    public void DrawObjects(Canvas canvas, Paint paint, Camera camera)
    {
        for(ScrollingObject o : m_ScrollingObjects)
            DrawScrollingObject(canvas, paint, camera, o);
    }

}
