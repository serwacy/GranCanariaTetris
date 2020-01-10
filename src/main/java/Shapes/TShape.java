package Shapes;

import java.util.List;

public class TShape extends Shape {
    public TShape(final String colorPath) {
        super(colorPath);
        super.addBlocks(new Block(1, 0, this.color));
        super.addBlocks(new Block(0, 1, this.color));
        super.addBlocks(new Block(1, 1, this.color));
        super.addBlocks(new Block(2, 1, this.color));
        super.rotation = 1;
    }

    @Override
    protected int calculateRotation(final List<Block> blocks) {
        if (rotation == 1) {
            translateBlockOfGivenShape(blocks, 0, 1, 1);
            translateBlockOfGivenShape(blocks, 1, 1, -1);
            translateBlockOfGivenShape(blocks, 3, -1, 1);
            return 2;
        } else if (rotation == 2) {
            translateBlockOfGivenShape(blocks, 0, -1, 1);
            translateBlockOfGivenShape(blocks, 1, 1, 1);
            translateBlockOfGivenShape(blocks, 3, -1, -1);
            return 3;
        } else if (rotation == 3) {
            translateBlockOfGivenShape(blocks, 0, -1, -1);
            translateBlockOfGivenShape(blocks, 1, -1, 1);
            translateBlockOfGivenShape(blocks, 3, 1, -1);
            return 4;
        } else if (rotation == 4) {
            translateBlockOfGivenShape(blocks, 0, 1, -1);
            translateBlockOfGivenShape(blocks, 1, -1, -1);
            translateBlockOfGivenShape(blocks, 3, 1, 1);
            return 1;
        }
        return rotation;
    }
}
