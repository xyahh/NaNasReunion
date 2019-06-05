package yjj.nanasreunion.Objects;

import android.graphics.Canvas;
import android.view.MotionEvent;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Objects.Components.Collision.Collision;
import yjj.nanasreunion.Objects.Components.Graphics.Graphics;
import yjj.nanasreunion.Vector2d;

public class Widget
{
    public Graphics     graphics;

    private Vector2d    m_ScreenPosition;
    private Collision   m_Collision;
    private Actor       m_Owner;
    private Command     m_WidgetCommand;

    public Widget(Vector2d position, Vector2d extents, Command command)
    {
        m_WidgetCommand = command;
        m_Owner = new Actor(); //empty actor

        m_ScreenPosition = position;
        m_Collision = new Collision(m_ScreenPosition, extents);
    }

    public void Draw(Canvas canvas, Camera camera, float interp)
    {
        graphics.Draw(canvas, camera, m_ScreenPosition, interp);
    }

    public boolean OnTouchEvent(MotionEvent event)
    {
        if(Collision.Check(m_Collision, new Vector2d(event.getX(), event.getY())))
        {
            m_WidgetCommand.Execute(m_Owner);
            return true;
        }
        return false;
    }

}
