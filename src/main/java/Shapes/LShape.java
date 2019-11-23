package Shapes;

import Tetris.Game;
import javafx.scene.paint.Color;

public class LShape extends Shape {
    public LShape() {
        super();
        super.addBlocks(new Block(2,0,Color.RED));
        super.addBlocks(new Block(2,1,Color.RED));
        super.addBlocks(new Block(1,1,Color.RED));
        super.addBlocks(new Block(0,1,Color.RED));
        super.setRotation(1);
    }

    public void rotate() {
        if(canRotate()) {
            if (getRotation() == 1) {
                translateRealBlockOfGivenShape(0,0,2);
                translateRealBlockOfGivenShape(1,-1,1);
                translateRealBlockOfGivenShape(3,1,-1);
                super.setRotation(2);
            } else if (getRotation() == 2) {
                translateRealBlockOfGivenShape(0,-2,0);
                translateRealBlockOfGivenShape(1,-1,-1);
                translateRealBlockOfGivenShape(3,1,1);
                super.setRotation(3);
            } else if (getRotation() == 3) {
                translateRealBlockOfGivenShape(0,0,-2);
                translateRealBlockOfGivenShape(1,1,-1);
                translateRealBlockOfGivenShape(3,-1,1);
                super.setRotation(4);
            } else if (getRotation() == 4) {
                translateRealBlockOfGivenShape(0,2,0);
                translateRealBlockOfGivenShape(1,1,1);
                translateRealBlockOfGivenShape(3,-1,-1);
                super.setRotation(1);
            }
        }
    }

    public boolean canRotate() {
        initGhostBlocks();
        if (getRotation() == 1) {
            translateGhostBlockOfGivenShape(0,0,2);
            translateGhostBlockOfGivenShape(1,-1,1);
            translateGhostBlockOfGivenShape(3,1,-1);
        } else if (getRotation() == 2) {
            translateGhostBlockOfGivenShape(0,-2,0);
            translateGhostBlockOfGivenShape(1,-1,-1);
            translateGhostBlockOfGivenShape(3,1,1);
        } else if (getRotation() == 3) {
            translateGhostBlockOfGivenShape(0,0,-2);
            translateGhostBlockOfGivenShape(1,1,-1);
            translateGhostBlockOfGivenShape(3,-1,1);
        } else if (getRotation() == 4) {
            translateGhostBlockOfGivenShape(0,2,0);
            translateGhostBlockOfGivenShape(1,1,1);
            translateGhostBlockOfGivenShape(3,-1,-1);
        }
        if(getGhostBlocks().stream().anyMatch(x ->
                x.getX() < 0 ||
                        x.getX() > Game.getTetrion().length -1 ||
                        x.getY() < 0 ||
                        x.getY() > Game.getTetrion()[0].length -1)){
            return false;
        }
        else return getGhostBlocks().stream().noneMatch(x ->
                Game.getTetrion()[x.getX()][x.getY()] != null);
    }
}
