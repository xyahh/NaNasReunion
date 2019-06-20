package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.State.EditorState;
import yjj.nanasreunion.Objects.Pawn;

public class EditorMode extends Item {

    protected EditorMode() {
        super("Editor Mode!", 10.f);
    }


    @Override
    public Item Create() {
        return new EditorMode();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
            pawn.PushState(new EditorState());
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
            pawn.PopState();
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        super.Draw(canvas, camera, pawn);
    }
}
