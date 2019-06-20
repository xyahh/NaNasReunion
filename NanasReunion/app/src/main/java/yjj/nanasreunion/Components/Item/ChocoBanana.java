package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class ChocoBanana extends Item {
    private Graphics graphics;
    private Vector2f position;
    private Vector2f pivot;
    private Vector2f OriginalScale;
    private Vector2f OriginalDimension;
    private Graphics original_graphics;

    private float collisionX = 0.15f;
    private float collisionY = 0.4f;

    protected ChocoBanana()
    {
        super("ChocoBanana", 3.f);
    }

    @Override
    public Item Create() {
        return new ChocoBanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        original_graphics = pawn.graphics;
        Rect padding = new Rect(0, 0, 0, 32);
        SpriteGraphics chocobanana_graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.chocobanana),
                10,6,6, padding);
        chocobanana_graphics.SetScale(0.75f, 0.75f);
        pawn.graphics     = chocobanana_graphics;

        // 캐릭터 크기 키우기
        OriginalScale = pawn.graphics.GetScale();
        OriginalDimension = pawn.collision.GetDimensions();
        float Scale = 2.f;
        pawn.graphics.SetScale(OriginalScale.x * Scale, OriginalScale.y * Scale );
        pawn.collision.SetDimensions(OriginalDimension.x * Scale, OriginalDimension.y * Scale);
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);


    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.graphics.SetScale(OriginalScale.x, OriginalScale.y);
        pawn.collision.SetDimensions(OriginalDimension.x, OriginalDimension.y);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {

    }
}
