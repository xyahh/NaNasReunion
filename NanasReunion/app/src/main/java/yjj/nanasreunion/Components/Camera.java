package yjj.nanasreunion.Components;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Camera
{
    private Vector2f m_CameraOffset;

    private Vector2i m_ScreenSize;

    public float ViewportLeft;
    public float ViewportTop;
    public float ViewportRight;
    public float ViewportBottom;

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

        ViewportLeft = 0;
        ViewportRight = 2.f;
        ViewportTop = 0;
        ViewportBottom = 1.5f;

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
        return  (m_ScreenSize.x * meters) / (ViewportRight - ViewportLeft) ;
    }

    public float DeltaYToPixels(float meters)
    {
        return  (m_ScreenSize.y * meters) / (ViewportBottom - ViewportTop) ;
    }

    public Vector2f GetWorldSpace(Vector2f screen_space)
    {
        Vector2f world_out = screen_space;
        world_out = Vector2f.Scale(world_out, 1.f / m_ScreenSize.x, 1.f / m_ScreenSize.y);

        world_out.y = 1.f - world_out.y;
        if(!ServiceHub.RightwardGameplay)
            world_out.x = 1.f - world_out.x;

        world_out.x *= (ViewportRight - ViewportLeft);
        world_out.x += ViewportLeft;

        world_out.y *= (ViewportBottom - ViewportTop);
        world_out.y += ViewportTop;

        world_out = Vector2f.Add(world_out, m_CameraPos);

        return world_out;
    }

    public Vector2i GetScreenSpace(Vector2f world_position)
    {
        Vector2f v = Vector2f.Subtract(world_position, m_CameraPos);
        Vector2f p = new Vector2f((v.x - ViewportLeft) / (ViewportRight - ViewportLeft),
                (v.y - ViewportTop) / (ViewportBottom - ViewportTop));

        p.y = 1.f - p.y;
        if(!ServiceHub.RightwardGameplay)
            p.x = 1.f - p.x;

        p = Vector2f.Scale(p, m_ScreenSize.x, m_ScreenSize.y);
        return p.toInt();
    }
}