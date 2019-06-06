package yjj.nanasreunion.OtherClasses.Scrolling;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class ScrollingBackground
{
    private ArrayList<ScrollingObject> m_BackgroundScrollingObjects;
    private ArrayList<ScrollingObject> m_ForegroundScrollingObjects;


    public ScrollingBackground()
    {
        m_BackgroundScrollingObjects = new ArrayList<>();
        m_ForegroundScrollingObjects = new ArrayList<>();
    }

    public void AddScrollingObject(Bitmap bitmap, float StartY, float TargetRelativeSpeed, Vector2i DesiredScreenSize)
    {
        if(TargetRelativeSpeed >= 1.f) // if faster, then means that it's closer to the camera
        {
            m_ForegroundScrollingObjects.add(new ScrollingObject(bitmap, StartY, TargetRelativeSpeed, DesiredScreenSize));
        }
        else
        {
            m_BackgroundScrollingObjects.add(new ScrollingObject(bitmap, StartY, TargetRelativeSpeed, DesiredScreenSize));
        }
    }

    public void Update(Actor target_actor, Camera camera, float deltaTime)
    {
        for(ScrollingObject o : m_BackgroundScrollingObjects)
            o.UpdateBackground(target_actor, camera, deltaTime);
        for(ScrollingObject o : m_ForegroundScrollingObjects)
            o.UpdateBackground(target_actor, camera, deltaTime);
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

    public void DrawBackground(Canvas canvas, Paint paint, Camera camera)
    {
        for(ScrollingObject o : m_BackgroundScrollingObjects)
            DrawScrollingObject(canvas, paint, camera, o);
    }

    public void DrawForeground(Canvas canvas, Paint paint, Camera camera)
    {
        for(ScrollingObject o : m_ForegroundScrollingObjects)
            DrawScrollingObject(canvas, paint, camera, o);
    }

}
