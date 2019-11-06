package Shapes;

import javafx.scene.paint.Color;

public class TShape extends Shape {
    public TShape() {
        super();
        super.addBlocks(new Block(3,0, Color.BLUE));
        super.addBlocks(new Block(4,0, Color.BLUE));
        super.addBlocks(new Block(5,0, Color.BLUE));
        super.addBlocks(new Block(4,1, Color.BLUE));
        super.setRotation(1);
    }

    public void rotate() {

    }

    public boolean canRotate() {
        return true;
    }
}
