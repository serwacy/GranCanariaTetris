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
        blocks = new ArrayList<>();
    }

    public void fall() {
        if (canFall()) {
            blocks.forEach(x -> x.setY(x.getY() + 1));
        }
    }

    public void moveLeft() {
        if (canMoveLeft()) {
            blocks.forEach(x -> x.setX(x.getX() - 1));
        }
    }

    public void moveRight() {
        if (canMoveRight()) {
            blocks.forEach(x -> x.setX(x.getX() + 1));
        }
    }

    public abstract void rotate();

    private boolean canFall() {
        if(blocks.stream().allMatch(x->x.getY() < Game.getTetrion()[0].length-1)) {
            return blocks.stream()
                    .allMatch(x -> Game.getTetrion()[x.getX()][x.getY() + 1] == null);
        }
        else{
            return false;
        }
    }

    private boolean canMoveLeft() {
        if(blocks.stream().allMatch(x -> x.getX() > 0)) {
            return blocks.stream()
                    .allMatch(x -> Game.getTetrion()[x.getX() - 1][x.getY()] == null);
        }
        else {
            return false;
        }
    }

    private boolean canMoveRight() {
        if(blocks.stream().allMatch(x->x.getX() < Game.getTetrion().length -1)) {
            return blocks.stream()
                    .allMatch(x -> Game.getTetrion()[x.getX() + 1][x.getY()] == null);
        }
        else{
            return false;
        }
    }

    public abstract boolean canRotate();

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


    public void initGhostBlocks(){
        this.ghostBlocks = new ArrayList<Block>();
        addGhostBlocks(new Block(blocks.get(0).getX(),blocks.get(0).getY(), Color.WHITE));
        addGhostBlocks(new Block(blocks.get(1).getX(),blocks.get(1).getY(), Color.WHITE));
        addGhostBlocks(new Block(blocks.get(2).getX(),blocks.get(2).getY(), Color.WHITE));
        addGhostBlocks(new Block(blocks.get(3).getX(),blocks.get(3).getY(), Color.WHITE));
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
}
