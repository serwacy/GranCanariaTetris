package Shapes;

import java.util.List;

public class OShape extends Shape {
    public OShape(final String colorPath) {
        super(colorPath);
        super.addBlocks(new Block(0, 0, this.color));
        super.addBlocks(new Block(1, 0, this.color));
        super.addBlocks(new Block(0, 1, this.color));
        super.addBlocks(new Block(1, 1, this.color));
        super.rotation = 1;
    }

    @Override
    protected int calculateRotation(final List<Block> blocks) {
        return rotation;
    }
}
