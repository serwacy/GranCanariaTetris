package Shapes;

import javafx.scene.paint.Color;

public class OShape extends Shape {
    public OShape(final String colorPath) {
        super(colorPath);
        super.addBlocks(new Block(0, 0, this.getColor()));
        super.addBlocks(new Block(1, 0, this.getColor()));
        super.addBlocks(new Block(0, 1, this.getColor()));
        super.addBlocks(new Block(1, 1, this.getColor()));
        super.setRotation(1);
    }

    public void rotate(Block[][] tetrion) {
        //nothing happens
    }

    public boolean canRotate(Block[][] tetrion) {
        return false;
    }
}
