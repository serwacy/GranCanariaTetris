package Shapes;

import javafx.scene.paint.Color;

public class OShape extends Shape {
    public OShape() {
        super();
        super.addBlocks(new Block(0, 0, Color.MAGENTA));
        super.addBlocks(new Block(1, 0, Color.MAGENTA));
        super.addBlocks(new Block(0, 1, Color.MAGENTA));
        super.addBlocks(new Block(1, 1, Color.MAGENTA));
        super.setRotation(1);
    }

    public void rotate() {
        //nothing happens
    }

//    public boolean canRotate() {
//        return false;
//    }
}
