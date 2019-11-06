package Shapes;

import javafx.scene.paint.Color;

public class SShape extends Shape {
    public SShape() {
        super();
        super.addBlocks(new Block(4, 0, Color.YELLOW));
        super.addBlocks(new Block(5, 0, Color.YELLOW));
        super.addBlocks(new Block(3, 1, Color.YELLOW));
        super.addBlocks(new Block(4, 1, Color.YELLOW));
        super.setRotation(1);
    }

    public void rotate() {

    }

    public boolean canRotate() {
        return false;
    }
}
