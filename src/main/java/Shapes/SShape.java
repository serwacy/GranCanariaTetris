package Shapes;

import Tetris.Game;
import javafx.scene.paint.Color;

public class SShape extends Shape {
    public SShape() {
        super();
        super.addBlocks(new Block(2, 0, Color.YELLOW));
        super.addBlocks(new Block(1, 0, Color.YELLOW));
        super.addBlocks(new Block(1, 1, Color.YELLOW));
        super.addBlocks(new Block(0, 1, Color.YELLOW));
        super.setRotation(1);
    }

    public void rotate() {
        if(canRotate()) {
            if (getRotation() == 1) {
                getBlocks().get(0).setY(getBlocks().get(0).getY() + 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() + 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() + 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() + 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() - 1);
                super.setRotation(2);
            } else if (getRotation() == 2) {
                getBlocks().get(0).setX(getBlocks().get(0).getX() - 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() - 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() + 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() + 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() + 1);
                super.setRotation(3);
            } else if (getRotation() == 3) {
                getBlocks().get(0).setY(getBlocks().get(0).getY() - 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() - 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() - 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() - 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() + 1);
                super.setRotation(4);
            } else if (getRotation() == 4) {
                getBlocks().get(0).setX(getBlocks().get(0).getX() + 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() + 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() - 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() - 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() - 1);
                super.setRotation(1);
            }
        }
    }

    public boolean canRotate() {
        initGhostBlocks();
        if (getRotation() == 1) {
            getGhostBlocks().get(0).setY(getGhostBlocks().get(0).getY() + 2);
            getGhostBlocks().get(1).setX(getGhostBlocks().get(1).getX() + 1);
            getGhostBlocks().get(1).setY(getGhostBlocks().get(1).getY() + 1);
            getGhostBlocks().get(3).setX(getGhostBlocks().get(3).getX() + 1);
            getGhostBlocks().get(3).setY(getGhostBlocks().get(3).getY() - 1);
        } else if (getRotation() == 2) {
            getGhostBlocks().get(0).setX(getGhostBlocks().get(0).getX() - 2);
            getGhostBlocks().get(1).setX(getGhostBlocks().get(1).getX() - 1);
            getGhostBlocks().get(1).setY(getGhostBlocks().get(1).getY() + 1);
            getGhostBlocks().get(3).setX(getGhostBlocks().get(3).getX() + 1);
            getGhostBlocks().get(3).setY(getGhostBlocks().get(3).getY() + 1);
        } else if (getRotation() == 3) {
            getGhostBlocks().get(0).setY(getGhostBlocks().get(0).getY() - 2);
            getGhostBlocks().get(1).setX(getGhostBlocks().get(1).getX() - 1);
            getGhostBlocks().get(1).setY(getGhostBlocks().get(1).getY() - 1);
            getGhostBlocks().get(3).setX(getGhostBlocks().get(3).getX() - 1);
            getGhostBlocks().get(3).setY(getGhostBlocks().get(3).getY() + 1);
        } else if (getRotation() == 4) {
            getGhostBlocks().get(0).setX(getGhostBlocks().get(0).getX() + 2);
            getGhostBlocks().get(1).setX(getGhostBlocks().get(1).getX() + 1);
            getGhostBlocks().get(1).setY(getGhostBlocks().get(1).getY() - 1);
            getGhostBlocks().get(3).setX(getGhostBlocks().get(3).getX() - 1);
            getGhostBlocks().get(3).setY(getGhostBlocks().get(3).getY() - 1);
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
