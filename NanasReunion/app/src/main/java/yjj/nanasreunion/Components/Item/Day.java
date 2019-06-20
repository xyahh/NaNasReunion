package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.MaxSpeedCommand;
import yjj.nanasreunion.Command.SpeedCommandUp;
import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Objects.Enemy;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Scenes.GameplayScene;
import yjj.nanasreunion.Scenes.Scene;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Day extends Item {


    Vector2f OriginalMaxVelocity_Player;
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

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity_Player);
        ServiceHub.EnemySpeedMultiplier = PrevMultiplier;
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {
        super.Draw(canvas, camera, pawn);
    }
}


