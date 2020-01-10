package Shapes;

import java.util.List;

public class IShape extends Shape {
    public IShape(final String colorPath) {
        super(colorPath);
        super.addBlocks(new Block(0, 0, this.color));
        super.addBlocks(new Block(1, 0, this.color));
        super.addBlocks(new Block(2, 0, this.color));
        super.addBlocks(new Block(3, 0, this.color));
        super.rotation = 1;
    }

    protected int calculateRotation(List<Block> blocks) {
        if (rotation == 1) {
            translateBlockOfGivenShape(blocks,0, 2, -1);
            translateBlockOfGivenShape(blocks,1, 1, 0);
            translateBlockOfGivenShape(blocks,2, 0, 1);
            translateBlockOfGivenShape(blocks,3, -1, 2);
            return  2;
        } else if (rotation == 2) {
            translateBlockOfGivenShape(blocks, 0, 1, 2);
            translateBlockOfGivenShape(blocks, 1, 0, 1);
            translateBlockOfGivenShape(blocks, 2, -1, 0);
            translateBlockOfGivenShape(blocks, 3, -2, -1);
            return  3;
        } else if (rotation == 3) {
            translateBlockOfGivenShape(blocks, 0, -2, 1);
            translateBlockOfGivenShape(blocks, 1, -1, 0);
            translateBlockOfGivenShape(blocks, 2, 0, -1);
            translateBlockOfGivenShape(blocks, 3, 1, -2);
            return  4;
        } else if (rotation == 4) {
            translateBlockOfGivenShape(blocks, 0, -1, -2);
            translateBlockOfGivenShape(blocks, 1, 0, -1);
            translateBlockOfGivenShape(blocks, 2, 1, 0);
            translateBlockOfGivenShape(blocks, 3, 2, 1);
            return  1;
        }
        return rotation;
    }
}
