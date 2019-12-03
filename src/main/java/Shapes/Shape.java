package Shapes;

import Tetris.Game;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
    private List<Block> blocks;
    private List<Block> ghostBlocks;
    private int rotation;

    public Shape() {
        this.blocks = new ArrayList<>();
        this.ghostBlocks = new ArrayList<>();
    }

    public abstract void rotate();
    public abstract boolean canRotate();

    public void fall(Block[][] tetrion) {
        if (canFall(tetrion)) {
            blocks.forEach(block -> block.setY(block.getY() + 1));
        }
    }

    public void moveLeft() {
        if (canMoveLeft()) {
            blocks.forEach(block -> block.setX(block.getX() - 1));
        }
    }

    public void moveRight() {
        if (canMoveRight()) {
            blocks.forEach(block -> block.setX(block.getX() + 1));
        }
    }

    private boolean canFall(Block[][] tetrion) {
        if(blocks.stream().allMatch(block -> block.getY() < tetrion[0].length-1)) {
            return blocks.stream()
                    .allMatch(block -> tetrion[block.getX()][block.getY() + 1] == null);
        }
        else{
            return false;
        }
    }

    private boolean canMoveLeft() {
        if(blocks.stream().allMatch(block -> block.getX() > 0)) {
            return blocks.stream()
                    .allMatch(block -> Game.getTetrion()[block.getX() - 1][block.getY()] == null);
        }
        else {
            return false;
        }
    }

    private boolean canMoveRight() {
        if(blocks.stream().allMatch(block->block.getX() < Game.getTetrion().length -1)) {
            return blocks.stream()
                    .allMatch(block -> Game.getTetrion()[block.getX() + 1][block.getY()] == null);
        }
        else{
            return false;
        }
    }

    public void addBlocks(Block block) {
        this.blocks.add(block);
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<Block> getGhostBlocks() {
        return ghostBlocks;
    }

    void initGhostBlocks(){
        this.ghostBlocks.clear();
        this.blocks.forEach(block -> addGhostBlocks(new Block(block.getX(),block.getY(),Color.WHITE)));
    }

    public void addGhostBlocks(Block block) {
        this.ghostBlocks.add(block);
    }

    public void translateBlockOfGivenShape(List<Block> givenBlocks, int blockIndex, int translateX, int translateY){
        givenBlocks.get(blockIndex).setX(givenBlocks.get(blockIndex).getX() + translateX);
        givenBlocks.get(blockIndex).setY(givenBlocks.get(blockIndex).getY() + translateY);
    }

    public void translateRealBlockOfGivenShape(int blockIndex, int translateX, int translateY){
        translateBlockOfGivenShape(blocks, blockIndex, translateX, translateY);
    }
    public void translateGhostBlockOfGivenShape(int blockIndex, int translateX, int translateY){
        translateBlockOfGivenShape(ghostBlocks, blockIndex, translateX, translateY);
    }
    public void placeOnStartingPosition() {
        this.blocks.forEach(block -> block.setX(block.getX()+3));
    }
}
