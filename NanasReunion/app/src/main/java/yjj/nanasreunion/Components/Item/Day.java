package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;

import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.OtherClasses.Scrolling.ScrollingObject;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Scenes.GameplayScene;
import yjj.nanasreunion.Scenes.Scene;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Day extends Item {


    Vector2f OriginalMaxVelocity_Player;
    ScrollingObject OriginalSun;
    float PrevMultiplier;

    protected Day()
    {
        super("Day", 10.f);
    }

    @Override
    public Item Create() {
        return new Day();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        OriginalMaxVelocity_Player = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity_Player.x * 0.5f, OriginalMaxVelocity_Player.y));
        PrevMultiplier = ServiceHub.EnemySpeedMultiplier;
        ServiceHub.EnemySpeedMultiplier = 2.f;

        Scene CurrScene = ServiceHub.Inst().GetCurrentScene();

        if(!(CurrScene instanceof GameplayScene))
            return;

        Vector2i ScreenSize = ServiceHub.Inst().GetScreenSize();
        ScrollingObject Sun= new ScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.upsun), 0.f, new Vector2f(),
                0.f, new Vector2i(ScreenSize.x / 4 , ScreenSize.x / 4));

        GameplayScene gpScene = (GameplayScene) CurrScene;
        OriginalSun = gpScene.m_Background.GetScrollingObject("A_Sun");
        Sun.SetWorldY(1.15f);
        gpScene.m_Background.SetScrollingObject("A_Sun", Sun);

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity_Player);
        ServiceHub.EnemySpeedMultiplier = PrevMultiplier;

        Scene CurrScene = ServiceHub.Inst().GetCurrentScene();

        if(!(CurrScene instanceof GameplayScene))
            return;

        GameplayScene gpScene = (GameplayScene) CurrScene;
        gpScene.m_Background.SetScrollingObject("A_Sun", OriginalSun);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {
        super.Draw(canvas, camera, pawn);
    }
}


