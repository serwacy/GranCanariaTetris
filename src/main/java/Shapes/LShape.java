package Shapes;

import javafx.scene.paint.Color;

public class LShape extends Shape {
    public LShape() {
        super();
        super.addBlocks(new Block(3,0,Color.RED));
        super.addBlocks(new Block(4,0,Color.RED));
        super.addBlocks(new Block(5,0,Color.RED));
        super.addBlocks(new Block(3,1,Color.RED));
        super.setRotation(1);
    }

    public void rotate() {

    }

    public boolean canRotate() {
        return true;
    }
}
