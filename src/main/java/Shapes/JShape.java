package Shapes;

import javafx.scene.paint.Color;

public class JShape extends Shape {
    public JShape() {
        super();
        super.addBlocks(new Block(3, 0, Color.PURPLE));
        super.addBlocks(new Block(4, 0, Color.PURPLE));
        super.addBlocks(new Block(5, 0, Color.PURPLE));
        super.addBlocks(new Block(5, 1, Color.PURPLE));
        super.setRotation(1);
    }

    @Override
    public void rotate() {

    }

    @Override
    public boolean canRotate() {
        return false;
    }
}
