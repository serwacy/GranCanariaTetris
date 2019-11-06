package Shapes;

import javafx.scene.paint.Color;

public class OShape extends Shape {
    public OShape() {
        super();
        super.addBlocks(new Block(3, 0, Color.MAGENTA));
        super.addBlocks(new Block(4, 0, Color.MAGENTA));
        super.addBlocks(new Block(3, 1, Color.MAGENTA));
        super.addBlocks(new Block(4, 1, Color.MAGENTA));
        super.setRotation(1);
    }

    public void rotate() {
        //nothing happens
    }

    public boolean canRotate() {
        return false;
    }
}
