package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class Bigbanana extends Item {
    private SpriteGraphics graphics;
    private Vector2f position;
    private Vector2f pivot;
    private float scaleX = 0.75f;
    private float scaleY = 0.75f;
    private float collisionX = 0.15f;
    private float collisionY = 0.4f;

    protected Bigbanana()
    {
        super("BigBanana", 5.f);
    }

    @Override
    public Item Create() {
        return new Bigbanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        // 캐릭터 크기 키우기
        pawn.graphics.SetScale(scaleX + 1, scaleY + 1 );
        pawn.collision.SetDimensions(collisionX+0.3f, collisionY+1);
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);


    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {

    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {

    }
}
