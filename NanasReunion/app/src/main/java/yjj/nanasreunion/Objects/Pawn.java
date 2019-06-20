package yjj.nanasreunion.Objects;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayDeque;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Item.Item;
import yjj.nanasreunion.Components.Physics.NullPhysics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Components.State.*;
import yjj.nanasreunion.MyStack;

public class Pawn extends Actor
{
    private     MyStack<State> states;
    private     Camera         m_Camera;
    public      Physics physics;
    private     ArrayDeque<Item> m_Items;

    public      float           JumpForce;

    public Pawn()
    {
        super();
        JumpForce = 300.f;
        physics     = new NullPhysics();
        states = new MyStack<>();
        PushState(new NullState());
        m_Items = new ArrayDeque<>();
    }

    public void SetCamera(Camera camera)
    {
        m_Camera = camera;
    }

    public void PushState(State state)
    {
        states.push(state);
        states.top().Enter(this);
    }

    public void PopState()
    {
        if(states.size() > 1) //always have NullState. Never empty.
            states.pop().Exit(this);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, float interp) {
        super.Draw(canvas, camera, interp);
        if (m_Items.size() > 0)
            m_Items.getFirst().Draw(canvas, camera, this);
    }

    public void ChangeState(State state)
    {
        PopState();
        PushState(state);
    }

    @Override
    public void Update(float DeltaTime) {
        super.Update(DeltaTime);
        physics.Update(this, DeltaTime);
        states.top().Update(this, DeltaTime);

        if (m_Items.size() > 0)
        {
            Item first = m_Items.getFirst();
            if (first.UpdateAndValidate(DeltaTime))
            {
                first.Stop(this, m_Camera);
                m_Items.removeFirst();
                if(m_Items.size() > 0)
                    m_Items.getFirst().Use(this, m_Camera);
            }
        }

    }

    public boolean OnTouchEvent(MotionEvent event)
    {
        return states.top().OnTouchEvent(this, event);
    }

    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return states.top().OnKeyDown(this,keyCode, event);
    }

    public void AddItem(Item item)
    {
        m_Items.addLast(item);
        if(m_Items.size() == 1)
            m_Items.getFirst().Use(this, m_Camera);
    }
}
