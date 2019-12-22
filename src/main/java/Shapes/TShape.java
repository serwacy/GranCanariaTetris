package Shapes;

import Tetris.Game;
import javafx.scene.paint.Color;

public class TShape extends Shape {
    public TShape(final String colorPath) {
        super(colorPath);
        super.addBlocks(new Block(1,0, this.getColor()));
        super.addBlocks(new Block(0,1, this.getColor()));
        super.addBlocks(new Block(1,1, this.getColor()));
        super.addBlocks(new Block(2,1, this.getColor()));
        super.setRotation(1);
    }

    public void rotate(Block[][] tetrion) {
        if(canRotate(tetrion)) {
            if (getRotation() == 1) {
                translateRealBlockOfGivenShape(0,1,1);
                translateRealBlockOfGivenShape(1,1,-1);
                translateRealBlockOfGivenShape(3,-1,1);
                super.setRotation(2);
            } else if (getRotation() == 2) {
                translateRealBlockOfGivenShape(0,-1,1);
                translateRealBlockOfGivenShape(1,1,1);
                translateRealBlockOfGivenShape(3,-1,-1);
                super.setRotation(3);
            } else if (getRotation() == 3) {
                translateRealBlockOfGivenShape(0,-1,-1);
                translateRealBlockOfGivenShape(1,-1,1);
                translateRealBlockOfGivenShape(3,1,-1);
                super.setRotation(4);
            } else if (getRotation() == 4) {
                translateRealBlockOfGivenShape(0,1,-1);
                translateRealBlockOfGivenShape(1,-1,-1);
                translateRealBlockOfGivenShape(3,1,1);
                super.setRotation(1);
            }
        }
    }

    public boolean canRotate(Block[][] tetrion) {
        initGhostBlocks();
        if (getRotation() == 1) {
            translateGhostBlockOfGivenShape(0,1,1);
            translateGhostBlockOfGivenShape(1,1,-1);
            translateGhostBlockOfGivenShape(3,-1,1);
        } else if (getRotation() == 2) {
            translateGhostBlockOfGivenShape(0,-1,1);
            translateGhostBlockOfGivenShape(1,1,1);
            translateGhostBlockOfGivenShape(3,-1,-1);
        } else if (getRotation() == 3) {
            translateGhostBlockOfGivenShape(0,-1,-1);
            translateGhostBlockOfGivenShape(1,-1,1);
            translateGhostBlockOfGivenShape(3,1,-1);
        } else if (getRotation() == 4) {
            translateGhostBlockOfGivenShape(0,1,-1);
            translateGhostBlockOfGivenShape(1,-1,-1);
            translateGhostBlockOfGivenShape(3,1,1);
        }
        if(getGhostBlocks().stream().anyMatch(x ->
                x.getX() < 0 ||
                        x.getX() > tetrion.length -1 ||
                        x.getY() < 0 ||
                        x.getY() > tetrion[0].length -1)){
            return false;
        }
        else return getGhostBlocks().stream().noneMatch(x ->
                tetrion[x.getX()][x.getY()] != null);
    }
}
