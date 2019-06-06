package yjj.nanasreunion.Objects.Components;

import yjj.nanasreunion.Objects.Components.Collision.Collision;
import yjj.nanasreunion.Vector2d;

public class Camera
{
   private Vector2d m_CameraOffsetScreen;
   private Vector2d m_ViewVector;

   private float    m_ScreenWidth;
   private float    m_ScreenHeight;

   public Camera()
   {
       m_ViewVector = new Vector2d();
       m_CameraOffsetScreen = new Vector2d();
   }

   public void SetCameraOffset(Vector2d CameraOffset)
   {
       m_CameraOffsetScreen = CameraOffset;
   }

   public void UpdateCameraView(Vector2d Target)
   {
       m_ViewVector = Vector2d.Add(Target, m_CameraOffsetScreen);
   }

   public Vector2d ScreenSpace(Vector2d world_position)
   {
       Vector2d v = Vector2d.Subtract(world_position, m_ViewVector);
       v.y = m_ScreenHeight - v.y;
       return v;
   }

   public boolean AppearsOnScreen(Collision collision)
   {
       return Collision.Check(collision, 0.f, 0.f, m_ScreenWidth, m_ScreenHeight);
   }

   public void UpdateViewport(int width, int height)
   {
       m_ScreenWidth  = width;
       m_ScreenHeight = height;
   }
}
