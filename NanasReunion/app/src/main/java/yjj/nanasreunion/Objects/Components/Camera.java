package yjj.nanasreunion.Objects.Components;

import yjj.nanasreunion.Vector2d;

public class Camera
{
   private Vector2d m_Target;
   private Vector2d m_CameraOffset;
   private Vector2d m_ViewVector;

   public Camera()
   {
       m_CameraOffset = new Vector2d(0.f, 0.f);
       m_Target = new Vector2d(0.f, 0.f);
   }

   public void SetTarget(Vector2d Target)
   {
       m_Target = Target;
   }

   public void SetCameraOffset(Vector2d CameraOffset)
   {
       m_CameraOffset = CameraOffset;
   }

   public void GenerateView()
   {
       m_ViewVector = Vector2d.Add(m_CameraOffset, m_Target);
   }

   public Vector2d GetScreenPosition(Vector2d position)
   {
       Vector2d v = Vector2d.Subtract(position, m_ViewVector);

       return v;
   }


   public void UpdateViewport(int width, int height)
   {

   }
}
