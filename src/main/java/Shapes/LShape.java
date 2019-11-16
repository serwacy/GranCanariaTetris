package Shapes;

import Tetris.Game;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

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
                getBlocks().get(0).setY(getBlocks().get(0).getY() + 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() - 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() + 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() + 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() - 1);
                super.setRotation(2);
            } else if (getRotation() == 2) {
                getBlocks().get(0).setX(getBlocks().get(0).getX() - 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() - 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() - 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() + 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() + 1);
                super.setRotation(3);
            } else if (getRotation() == 3) {
                getBlocks().get(0).setY(getBlocks().get(0).getY() - 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() + 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() - 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() - 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() + 1);
                super.setRotation(4);
            } else if (getRotation() == 4) {
                getBlocks().get(0).setX(getBlocks().get(0).getX() + 2);
                getBlocks().get(1).setX(getBlocks().get(1).getX() + 1);
                getBlocks().get(1).setY(getBlocks().get(1).getY() + 1);
                getBlocks().get(3).setX(getBlocks().get(3).getX() - 1);
                getBlocks().get(3).setY(getBlocks().get(3).getY() - 1);
                super.setRotation(1);
            }
        }
    }

    public boolean canRotate() {
        List<Block> ghostBlocks = new ArrayList<Block>();
        ghostBlocks.addAll(getBlocks());
        if (getRotation() == 1) {
            ghostBlocks.get(0).setY(ghostBlocks.get(0).getY() + 2);
            ghostBlocks.get(1).setX(ghostBlocks.get(1).getX() - 1);
            ghostBlocks.get(1).setY(ghostBlocks.get(1).getY() + 1);
            ghostBlocks.get(3).setX(ghostBlocks.get(3).getX() + 1);
            ghostBlocks.get(3).setY(ghostBlocks.get(3).getY() - 1);
        } else if (getRotation() == 2) {
            ghostBlocks.get(0).setX(ghostBlocks.get(0).getX() - 2);
            ghostBlocks.get(1).setX(ghostBlocks.get(1).getX() - 1);
            ghostBlocks.get(1).setY(ghostBlocks.get(1).getY() - 1);
            ghostBlocks.get(3).setX(ghostBlocks.get(3).getX() + 1);
            ghostBlocks.get(3).setY(ghostBlocks.get(3).getY() + 1);
        } else if (getRotation() == 3) {
            ghostBlocks.get(0).setY(ghostBlocks.get(0).getY() - 2);
            ghostBlocks.get(1).setX(ghostBlocks.get(1).getX() + 1);
            ghostBlocks.get(1).setY(ghostBlocks.get(1).getY() - 1);
            ghostBlocks.get(3).setX(ghostBlocks.get(3).getX() - 1);
            ghostBlocks.get(3).setY(ghostBlocks.get(3).getY() + 1);
        } else if (getRotation() == 4) {
            ghostBlocks.get(0).setX(ghostBlocks.get(0).getX() + 2);
            ghostBlocks.get(1).setX(ghostBlocks.get(1).getX() + 1);
            ghostBlocks.get(1).setY(ghostBlocks.get(1).getY() + 1);
            ghostBlocks.get(3).setX(ghostBlocks.get(3).getX() - 1);
            ghostBlocks.get(3).setY(ghostBlocks.get(3).getY() - 1);
        }
        if(ghostBlocks.stream().anyMatch(x ->
                Game.getTetrion()[x.getX()][x.getY()] != null ||
                        x.getX() < 0 ||
                        x.getX() > Game.getTetrion().length -1 ||
                        x.getY() < 0 ||
                        x.getY() > Game.getTetrion()[0].length -1)){
            return false;
        }
        else{
            return true;
        }
//        return true;
    }
}
