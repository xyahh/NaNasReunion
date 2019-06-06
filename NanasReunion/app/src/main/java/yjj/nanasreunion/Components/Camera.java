package yjj.nanasreunion.Components;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Components.Collision.Collision;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Camera
{
   private Vector2f m_CameraOffsetScreen;
   private Vector2f m_ViewVector;
   private float   m_PixelsToMeters;
   private Vector2i m_ScreenSize;

   public Camera()
   {
       m_CameraOffsetScreen = new Vector2f();
       m_ScreenSize = ServiceHub.Inst().GetScreenSize();
       m_PixelsToMeters= 400.f * ServiceHub.Inst().GetDPI();
       m_ViewVector = new Vector2f();
   }

   public void SetCameraOffset(Vector2f CameraOffset)
   {
       m_CameraOffsetScreen = CameraOffset;
   }

   public void UpdateCameraView(Actor target_actor)
   {
       if(target_actor==null) return;
       m_ViewVector = Vector2f.Add(target_actor.position, m_CameraOffsetScreen);
   }

   public float toPixelsF(float meters)
   {
       return m_PixelsToMeters* meters;
   }

   public Vector2i ScreenSpace(Vector2f world_position)
   {
       Vector2f v = Vector2f.Subtract(world_position, m_ViewVector);
       v = Vector2f.Scale(v, m_PixelsToMeters); //px to meter
       v.y = m_ScreenSize.y - v.y;
       return v.toInt();
   }

   public boolean AppearsOnScreen(Collision collision)
   {
       return Collision.Check(collision, 0.f, 0.f, m_ScreenSize.x, m_ScreenSize.y);
   }
}
