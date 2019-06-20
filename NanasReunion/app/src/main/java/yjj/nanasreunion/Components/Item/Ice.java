package yjj.nanasreunion.Components.Item;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.OtherClasses.Scrolling.ScrollingObject;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Scenes.GameplayScene;
import yjj.nanasreunion.Scenes.Scene;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Ice extends Item
{

    private ScrollingObject OriginalGround;
    private static ScrollingObject IceGround;

    static void LoadAssets()
    {
        IceGround = new ScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.frosted_ground),
            0.f, new Vector2f(), 1.f, ServiceHub.Inst().GetScreenSize());
    }

    private  Vector2f   OriginalMaxVelocity;

    protected Ice()
    {
        super("Ice", 10.f);
    }

    @Override
    public Item Create() {
        return new Ice();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        Scene CurrScene = ServiceHub.Inst().GetCurrentScene();

        if(!(CurrScene instanceof GameplayScene))
            return;

        GameplayScene gpScene = (GameplayScene) CurrScene;
        OriginalGround = gpScene.m_Background.GetScrollingObject("F_Ground");
        gpScene.m_Background.SetScrollingObject("F_Ground", IceGround);

        OriginalMaxVelocity = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 5.f, OriginalMaxVelocity.y));
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity);

        Scene CurrScene = ServiceHub.Inst().GetCurrentScene();

        if(!(CurrScene instanceof GameplayScene))
            return;

        GameplayScene gpScene = (GameplayScene) CurrScene;
        gpScene.m_Background.SetScrollingObject("F_Ground", OriginalGround);

    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {
    }
}
