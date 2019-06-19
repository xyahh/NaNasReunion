package yjj.nanasreunion.Components;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Camera
{
    private Vector2f m_CameraOffset;

    private Vector2i m_ScreenSize;

    private float    m_ViewportLeft;
    private float    m_ViewportTop;
    private float    m_ViewportRight;
    private float    m_ViewportBottom;

    private boolean    m_CameraMoveWithTargetX;
    private boolean     m_CameraMoveWithTargetY;

    private Vector2f m_CameraPosPure;
    private Vector2f m_CameraPos;
    private Vector2f m_CameraPosPurePrev;

    public Camera()
    {
        m_CameraPos = new Vector2f();
        m_CameraPosPure = new Vector2f();
        m_CameraPosPurePrev = new Vector2f();

        m_CameraMoveWithTargetX = true;
        m_CameraMoveWithTargetY = false;

        m_ViewportLeft = 0;
        m_ViewportRight = 2.f;
        m_ViewportTop = 0;
        m_ViewportBottom = 1.5f;

        m_CameraOffset = new Vector2f();
        m_ScreenSize = ServiceHub.Inst().GetScreenSize();
    }

    public void SetCameraOffset(Vector2f CameraOffset)
    {
        m_CameraOffset = CameraOffset;
    }

    public boolean GetMovingFactorX()
    {
        return m_CameraMoveWithTargetX;
    }
    public boolean GetMovingFactorY()
    {
        return m_CameraMoveWithTargetY;
    }

    public void SetMovingFactor(boolean x, boolean y)
    {
        m_CameraMoveWithTargetX = x;
        m_CameraMoveWithTargetY = y;
    }

    public void UpdateCameraView(Actor target_actor)
    {
        if(target_actor==null) return;

        m_CameraPosPurePrev = m_CameraPosPure;

        Vector2f v = new Vector2f(m_CameraPosPure);

        if(m_CameraMoveWithTargetX)
            v.x += (target_actor.position.x - v.x) / 10.f;
        if(m_CameraMoveWithTargetY)
            v.y += (target_actor.position.y - v.y) / 10.f;

        m_CameraPosPure = v;
        m_CameraPos = Vector2f.Add(m_CameraPosPure, m_CameraOffset);
    }

    public Vector2f GetCamDeltaDistance()
    {
        return Vector2f.Subtract(m_CameraPosPure, m_CameraPosPurePrev);
    }

       public float DeltaXToPixels(float meters)
    {
        return  (m_ScreenSize.x * meters) / (m_ViewportRight - m_ViewportLeft) ;
    }

    public float DeltaYToPixels(float meters)
    {
        return  (m_ScreenSize.y * meters) / (m_ViewportBottom - m_ViewportTop) ;
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