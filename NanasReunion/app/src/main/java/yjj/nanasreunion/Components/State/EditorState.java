package yjj.nanasreunion.Components.State;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.Iterator;
import java.util.LinkedList;

import yjj.nanasreunion.Components.Collision.Collision;
import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Objects.Enemy;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Scenes.GameplayScene;
import yjj.nanasreunion.Scenes.Scene;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class EditorState extends State
{
    LinkedList<Actor> m_Actors;

    public EditorState()
    {
        super(STATE_ID.EDITOR);
    }

    @Override
    public void Enter(Pawn pawn)
    {
        Scene scene = ServiceHub.Inst().GetCurrentScene();

        if(scene instanceof GameplayScene)
        {
            GameplayScene gp = (GameplayScene)scene;
            m_Actors = gp.m_Actors;
        } else
        {
            pawn.PopState();
        }
    }

    @Override
    public void Exit(Pawn pawn) {

    }

    @Override
    public void Update(Pawn pawn, float DeltaTime) {
        pawn.physics.ApplyForce(new Vector2f(pawn.RunningForce, 0.f));
    }

    @Override
    public boolean OnTouchEvent(Pawn pawn, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Vector2f world_pos = pawn.GetCamera().GetWorldSpace(new Vector2f(event.getX(), event.getY()));

            Scene scene = ServiceHub.Inst().GetCurrentScene();
            GameplayScene gp = null;
            if(scene instanceof GameplayScene)
            {
                gp = (GameplayScene)scene;
            }

            float sq_extent = 0.05f;
            RectF rect = new RectF();
            rect.left = world_pos.x - sq_extent;
            rect.right = world_pos.x + sq_extent;

            rect.top = world_pos.y - sq_extent;
            rect.bottom = world_pos.y + sq_extent;

            Iterator itr = m_Actors.iterator();
            while (itr.hasNext())
            {
                Actor a = (Actor)itr.next();
                if((a instanceof Enemy) && Collision.Check(a.collision, rect.left, rect.top, rect.right, rect.bottom))
                {
                    itr.remove();
                    if(gp != null)
                        gp.m_TotalTime += 10.f;
                }

            }

            return true;
        }
        return false;
    }

    @Override
    public boolean OnKeyDown(Pawn pawn, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public State CreateState() {
        return new EditorState();
    }
}
