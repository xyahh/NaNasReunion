package yjj.nanasreunion.Components;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Camera
{
   private Vector2f m_CameraOffsetScreen;

   private Vector2i m_ScreenSize;

    private float    m_MovingFactorX;
    private float    m_MovingFactorY;

    private float    m_ViewportLeft;
    private float    m_ViewportTop;
    private float    m_ViewportRight;
    private float    m_ViewportBottom;

    private float    m_TargetMovingFactorX;
    private float    m_TargetMovingFactorY;

    private Vector2f m_CameraPos;
    private Vector2f m_CameraPrevPos;
    private float m_DeltaTime;

   public Camera()
   {
       m_CameraPos = new Vector2f();
       m_CameraPrevPos = new Vector2f();
       m_DeltaTime = 0.f;
       m_ViewportLeft = 0;
       m_ViewportRight = 2.f;
       m_ViewportTop = 0;
       m_ViewportBottom = 1.5f;

       m_CameraOffsetScreen = new Vector2f();
       m_ScreenSize = ServiceHub.Inst().GetScreenSize();
       m_MovingFactorX = 1.f;
       m_MovingFactorY = 1.f;
       m_TargetMovingFactorX = 1.f;
       m_TargetMovingFactorY =1.f;
   }

   public void SetCameraOffset(Vector2f CameraOffset)
   {
       m_CameraOffsetScreen = CameraOffset;
       m_CameraPrevPos = m_CameraOffsetScreen;
   }

   public void SetMovingFactor(float x, float y)
   {
       m_TargetMovingFactorX = x;
       m_MovingFactorY = y;
   }

   private float UpdateError = 0.01f;
   private final float DeltaCap = 0.5f;
   public void UpdateCameraView(Actor target_actor, float deltaTime)
   {
       if(target_actor==null) return;
       float DeltaMovingFactor = Math.abs(m_TargetMovingFactorX - m_MovingFactorX);
       boolean MovingCamera =  !( Math.abs(m_TargetMovingFactorX - m_MovingFactorX)< UpdateError);
       if(MovingCamera)
       {
           DeltaMovingFactor = Math.abs(m_TargetMovingFactorX - m_MovingFactorX);

           float delta = (m_TargetMovingFactorX - m_MovingFactorX);
           if(delta < -DeltaCap) delta = -DeltaCap;
           if(delta > DeltaCap) delta = DeltaCap;

           m_MovingFactorX +=  delta * UpdateError;
           if(Math.abs(m_TargetMovingFactorX - m_MovingFactorX) < UpdateError)
           {
               m_MovingFactorX = m_TargetMovingFactorX;
               MovingCamera = false;
           }
           if(m_MovingFactorX > 1.f) m_MovingFactorX = 1.f;
           if(m_MovingFactorX < 0.f) m_MovingFactorX = 0.f;

       }
       m_DeltaTime = deltaTime;
       m_CameraPrevPos = m_CameraPos;
       Vector2f v = Vector2f.Scale(target_actor.position, m_MovingFactorX, m_MovingFactorY);
       v = Vector2f.Add(v, m_CameraOffsetScreen);

       float det1 = 1.f;
       float det2 = 0.f;

       if(MovingCamera)
           det2 = DeltaMovingFactor;
       if(Math.abs(m_TargetMovingFactorX ) < UpdateError)
       {
            det1 = 0.f;
            det2 = 1.f;
       }
        v.x = v.x * det1 + m_CameraPrevPos.x * det2;
        m_CameraPos = v;
   }

    public Vector2f GetCamDeltaDistance()
    {
        return Vector2f.Subtract(m_CameraPos, m_CameraPrevPos);
    }

   public float DeltaXToPixels(float meters)
   {
       return  (m_ScreenSize.x * meters) / (m_ViewportRight - m_ViewportLeft) ;
   }

   public Vector2i GetScreenSpace(Vector2f world_position)
   {
       Vector2f v = Vector2f.Subtract(world_position, m_CameraPos);
       Vector2f p = new Vector2f((v.x - m_ViewportLeft) / (m_ViewportRight - m_ViewportLeft),
               (v.y - m_ViewportTop) / (m_ViewportBottom - m_ViewportTop));
       p.y = 1.f - p.y;
       p = Vector2f.Scale(p, m_ScreenSize.x, m_ScreenSize.y);
       return p.toInt();
   }
}
