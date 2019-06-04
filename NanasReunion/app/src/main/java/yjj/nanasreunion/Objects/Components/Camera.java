package yjj.nanasreunion.Objects.Components;

import yjj.nanasreunion.Objects.Components.Collision.Collision;
import yjj.nanasreunion.Vector2d;

public class Camera
{
   private Vector2d m_CameraOffset;
   private Vector2d m_ViewVector;
   private float    m_ScreenWidth = 0.f;
   private float    m_ScreenHeight = 0.f;


   public Camera()
   {
       m_CameraOffset = new Vector2d();
       m_ViewVector = new Vector2d();
   }

   public void SetCameraOffset(Vector2d CameraOffset)
   {
       m_CameraOffset = CameraOffset;
   }

   public void GenerateView(Vector2d Target)
   {
       m_ViewVector = Vector2d.Add(Target,
               Vector2d.Scale(m_CameraOffset, m_ScreenWidth, m_ScreenHeight));
   }

   public Vector2d ToScreenSpace(Vector2d world_position)
   {
       return Vector2d.Subtract(world_position, m_ViewVector);
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
