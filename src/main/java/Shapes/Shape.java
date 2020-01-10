package Shapes;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
    private List<Block> blocks;
    private List<Block> ghostBlocks;
    protected int rotation;
    protected ImagePattern color;

    public Shape(String colorPath) {
        this.color = new ImagePattern(new Image(this.getClass().getResourceAsStream(colorPath)));
        this.blocks = new ArrayList<>();
        this.ghostBlocks = new ArrayList<>();
    }

    //R.I.P. BIMETHOG() [*]
    protected abstract int calculateRotation(List<Block> blocks);

    public void rotate(Block[][] tetrion) {
        int rotationValue = rotation;
        if (canRotate(tetrion)) {
            rotationValue = calculateRotation(blocks);
        }
        rotation = rotationValue;
    }

    private boolean canRotate(Block[][] tetrion) {
        initGhostBlocks();
        calculateRotation(ghostBlocks);
        if (ghostBlocks.stream().anyMatch(x ->
                x.getX() < 0 ||
                        x.getX() > tetrion.length - 1 ||
                        x.getY() < 0 ||
                        x.getY() > tetrion[0].length - 1)) {
            return false;
        } else return ghostBlocks.stream().noneMatch(x ->
                tetrion[x.getX()][x.getY()] != null);
    }

    private void initGhostBlocks() {
        this.ghostBlocks.clear();
        this.blocks.forEach(block -> ghostBlocks.add(new Block(block.getX(), block.getY(), color)));
    }

    public void moveLeft(Block[][] tetrion) {
        if (canMoveLeft(tetrion)) {
            blocks.forEach(block -> block.setX(block.getX() - 1));
        }
    }

    private boolean canMoveLeft(Block[][] tetrion) {
        if (blocks.stream().allMatch(block -> block.getX() > 0)) {
            return blocks.stream()
                    .allMatch(block -> tetrion[block.getX() - 1][block.getY()] == null);
        } else {
            return false;
        }
    }

    public void moveRight(Block[][] tetrion) {
        if (canMoveRight(tetrion)) {
            blocks.forEach(block -> block.setX(block.getX() + 1));
        }
    }

    private boolean canMoveRight(Block[][] tetrion) {
        if (blocks.stream().allMatch(block -> block.getX() < tetrion.length - 1)) {
            return blocks.stream()
                    .allMatch(block -> tetrion[block.getX() + 1][block.getY()] == null);
        } else {
            return false;
        }
    }

    protected void addBlocks(Block block) {
        this.blocks.add(block);
    }

    protected void translateBlockOfGivenShape(List<Block> givenBlocks, int blockIndex, int translateX, int translateY) {
        givenBlocks.get(blockIndex).setX(givenBlocks.get(blockIndex).getX() + translateX);
        givenBlocks.get(blockIndex).setY(givenBlocks.get(blockIndex).getY() + translateY);
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
