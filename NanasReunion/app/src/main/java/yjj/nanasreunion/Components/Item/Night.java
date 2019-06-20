package yjj.nanasreunion.Components.Item;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.MaxSpeedCommand;
import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.OtherClasses.Scrolling.ScrollingObject;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Scenes.GameplayScene;
import yjj.nanasreunion.Scenes.Scene;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Night extends Item {


    Vector2f OriginalMaxVelocity_Player;

    ScrollingObject OriginalGround;

    float PrevMultiplier;
    int PrevColor;

    protected Night()
    {
        super("Night", 10.f);
    }

    @Override
    public Item Create() {
        return new Night();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {

        PrevColor = ServiceHub.ClearColor;
        ServiceHub.ClearColor = Color.argb(255, 12, 49, 102);

        OriginalMaxVelocity_Player = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity_Player.x * 2.f, OriginalMaxVelocity_Player.y));
        PrevMultiplier = ServiceHub.EnemySpeedMultiplier;
        ServiceHub.EnemySpeedMultiplier = 0.5f;

        Scene CurrScene = ServiceHub.Inst().GetCurrentScene();

        if(!(CurrScene instanceof GameplayScene))
            return;

        Vector2i ScreenSize = ServiceHub.Inst().GetScreenSize();
        ScrollingObject Moon= new ScrollingObject(ServiceHub.Inst().GetBitmap(R.drawable.moon), 0.f, new Vector2f(),
                0.f, new Vector2i(ScreenSize.x / 5 , ScreenSize.x / 5));

        GameplayScene gpScene = (GameplayScene) CurrScene;
        OriginalGround = gpScene.m_Background.GetScrollingObject("A_Sun");
        Moon.SetWorldY(1.15f);
        gpScene.m_Background.SetScrollingObject("A_Sun", Moon);

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        ServiceHub.ClearColor = PrevColor;
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity_Player);
        ServiceHub.EnemySpeedMultiplier = PrevMultiplier;

        Scene CurrScene = ServiceHub.Inst().GetCurrentScene();

        if(!(CurrScene instanceof GameplayScene))
            return;

        GameplayScene gpScene = (GameplayScene) CurrScene;
        gpScene.m_Background.SetScrollingObject("A_Sun", OriginalGround);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {
        super.Draw(canvas, camera, pawn);
    }
}


