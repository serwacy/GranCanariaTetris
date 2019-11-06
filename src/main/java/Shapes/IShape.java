package Shapes;

import javafx.scene.paint.Color;

public class IShape extends Shape {
    public IShape() {
        super();
        super.addBlocks(new Block(3,0, Color.GREEN));
        super.addBlocks(new Block(4,0, Color.GREEN));
        super.addBlocks(new Block(5,0, Color.GREEN));
        super.addBlocks(new Block(6,0, Color.GREEN));
        super.setRotation(1);
    }

    public void rotate() {

    }

    public boolean canRotate() {
        return false;
    }
}
