package Shapes;

import javafx.scene.paint.Color;

public class ZShape extends Shape {
    public ZShape() {
        super();
        super.addBlocks(new Block(3, 0, Color.ORANGE));
        super.addBlocks(new Block(4, 0, Color.ORANGE));
        super.addBlocks(new Block(4, 1, Color.ORANGE));
        super.addBlocks(new Block(5, 1, Color.ORANGE));
        super.setRotation(1);
    }

    public void rotate() {

    }

    public boolean canRotate() {
        return false;
    }
}
