package yjj.nanasreunion.Actors.Components;

import yjj.nanasreunion.Actors.Actor;
import yjj.nanasreunion.Vector2d;

public class Camera
{
   private Actor   m_FocusedActor;

   public void SetFocusedActor(Actor actor)
   {
       m_FocusedActor = actor;
   }

   public Vector2d GetScreenPosition(Vector2d position)
   {
       if(m_FocusedActor == null) return new Vector2d(position);
       return  Vector2d.Add(position, Vector2d.Scale(m_FocusedActor.position, -1.f));
   }

   public void UpdateViewport(int width, int height)
   {

   }
}
