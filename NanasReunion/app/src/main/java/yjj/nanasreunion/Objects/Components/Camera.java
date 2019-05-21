package yjj.nanasreunion.Objects.Components;

import yjj.nanasreunion.Vector2d;

public class Camera
{
   private Vector2d m_Target;
   private Vector2d m_CameraOffset;
   private float    m_CameraDistance;
   private Vector2d m_ViewVector;

   public Camera()
   {
       m_CameraOffset = new Vector2d(0.f, 0.f);
       m_CameraDistance = 10.f;
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
       m_ViewVector = Vector2d.Add(m_Target, m_CameraOffset);
   }

   public Vector2d ToCameraSpace(Vector2d world_position)
   {
       //Camera Space of range [-1, 1] and origin (0, 0);
       Vector2d v = Vector2d.Subtract(world_position, m_ViewVector);

       return v;
   }

   public Vector2d ToScreenSpace(Vector2d camera_position)
   {
       Vector2d v = new Vector2d();

        return v;
   }

   public boolean AppearsOnCamera(Collision collision)
   {
        return Collision.Check(collision, -1.f, -1.f, 1.f, 1.f);
   }

   public void UpdateViewport(int width, int height)
   {
   }
}
