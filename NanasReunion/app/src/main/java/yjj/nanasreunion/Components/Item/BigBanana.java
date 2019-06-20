package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.KillCommand;
import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class BigBanana extends Item {
    private SpriteGraphics graphics;
    private Vector2f position;
    private Vector2f pivot;
    private Vector2f OriginalScale;
    private Vector2f OriginalDimension;
    private ArrayList<Command> OriginalCommandList;

    private float collisionX = 0.15f;
    private float collisionY = 0.4f;

    protected BigBanana()
    {
        super("Big Banana", 5.f);
    }

    @Override
    public Item Create() {
        return new BigBanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        // 캐릭터 크기 키우기
        OriginalScale = pawn.graphics.GetScale();
        OriginalDimension = pawn.collision.GetDimensions();
        float Scale = 2.f;
        pawn.graphics.SetScale(OriginalScale.x * Scale, OriginalScale.y * Scale );
        pawn.collision.SetDimensions(OriginalDimension.x * Scale, OriginalDimension.y * Scale);
        OriginalCommandList = pawn.collision.GetCollisionCommands(COLLISION_TYPES.ENEMY);
        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ENEMY, new ArrayList<Command>()
        {
            {
                add(new KillCommand());
            }
        });
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);


    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.graphics.SetScale(OriginalScale.x, OriginalScale.y);
        pawn.collision.SetDimensions(OriginalDimension.x, OriginalDimension.y);
        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ENEMY, OriginalCommandList);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {

    }
}
