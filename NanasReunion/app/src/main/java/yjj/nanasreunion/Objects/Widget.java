package yjj.nanasreunion.Objects;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Collision.Collision;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Widget
{
    public Graphics     graphics;
    public Actor       owner;

    private Vector2i m_ScreenPosition;
    private Collision   m_Collision;

    private ArrayList<Command> m_PressedCommands;
    private ArrayList<Command> m_ReleasedCommands;
    private boolean             m_Pressed;

    public Widget(Vector2i widget_position, Vector2i collision_extents)
    {
        owner = null;
        m_PressedCommands = new ArrayList<>();
        m_ReleasedCommands = new ArrayList<>();
        m_Pressed = false;
        m_ScreenPosition = widget_position;
        m_Collision = new Collision(m_ScreenPosition.toFloat(), collision_extents.toFloat());
    }

    public void AddCommand(Command command, boolean PressedCommand)
    {
        if(PressedCommand)
        {
            m_PressedCommands.add(command);
        } else
        {
            m_ReleasedCommands.add(command);
        }
    }

    public void Draw(Canvas canvas, float interp)
    {
        graphics.Draw(canvas, null, m_ScreenPosition.toFloat(), interp);
    }

    public boolean OnTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(Collision.Check(m_Collision, new Vector2f(event.getX(), event.getY())) && !m_Pressed)
                {
                    for(Command c : m_PressedCommands)
                        c.Execute(owner);
                    m_Pressed = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(Collision.Check(m_Collision, new Vector2f(event.getX(), event.getY())) && m_Pressed)
                {
                    for(Command c : m_ReleasedCommands)
                        c.Execute(owner);
                    m_Pressed = false;
                    return false;
                }
        }

        return false;
    }

}
