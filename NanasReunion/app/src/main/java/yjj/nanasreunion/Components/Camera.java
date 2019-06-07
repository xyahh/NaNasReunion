package yjj.nanasreunion.Components;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Components.Collision.Collision;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Camera
{
   private Vector2f m_CameraOffsetScreen;

   private float   m_PixelsToMeters;
   private Vector2i m_ScreenSize;

    private float    m_MovingFactorX;
    private float    m_MovingFactorY;

    private Vector2f m_CameraPos;
    private Vector2f m_CameraPrevPos;

   public Camera()
   {
       m_CameraPos = new Vector2f();
       m_CameraPrevPos = new Vector2f();

       m_CameraOffsetScreen = new Vector2f();
       m_ScreenSize = ServiceHub.Inst().GetScreenSize();
       m_PixelsToMeters= 400.f * ServiceHub.Inst().GetDPI();
       m_MovingFactorX = 1.f;
       m_MovingFactorY = 1.f;
   }

   public void SetCameraOffset(Vector2f CameraOffset)
   {
       m_CameraOffsetScreen = CameraOffset;
       m_CameraPrevPos = m_CameraOffsetScreen;
   }

   public void SetMovingFactor(float x, float y)
   {
       m_MovingFactorX = x;
       m_MovingFactorY = y;
   }

   public void UpdateCameraView(Actor target_actor)
   {
       if(target_actor==null) return;
       m_CameraPrevPos = m_CameraPos;
       Vector2f v = Vector2f.Scale(target_actor.position, m_MovingFactorX, m_MovingFactorY);
       v = Vector2f.Add(v, m_CameraOffsetScreen);
       if(m_MovingFactorX == 0.f)
           v.x = m_CameraPrevPos.x;
       //do interpolation here
       m_CameraPos = v;
   }

    public Vector2f GetCameraDeltaVelocity()
    {
        return Vector2f.Subtract(m_CameraPos, m_CameraPrevPos);
    }

   public float toPixelsF(float meters)
   {
       return m_PixelsToMeters* meters;
   }

   public Vector2i ScreenSpace(Vector2f world_position)
   {
       Vector2f v = Vector2f.Subtract(world_position, m_CameraPos);
       v = Vector2f.Scale(v, m_PixelsToMeters); //px to meter
       v.y = m_ScreenSize.y - v.y;
       return v.toInt();
   }

   public boolean AppearsOnScreen(Collision collision)
   {
       return Collision.Check(collision, 0.f, 0.f, m_ScreenSize.x, m_ScreenSize.y);
   }
}
