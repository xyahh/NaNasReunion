package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Enemy;
import yjj.nanasreunion.Objects.Pawn;

public class OnionSoup extends Item {
    protected OnionSoup(String item_name, float item_duration) {
        super(item_name, item_duration);
    }

    @Override
    public Item Create() {
        return null;
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {


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
        super.Draw(canvas, camera, pawn);
    }
}
