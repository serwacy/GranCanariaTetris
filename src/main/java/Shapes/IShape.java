package Shapes;

import javafx.scene.paint.Color;

import java.util.List;

public class IShape extends Shape {
    public IShape() {
        super();
        super.addBlocks(new Block(0,0, Color.GREEN));
        super.addBlocks(new Block(1,0, Color.GREEN));
        super.addBlocks(new Block(2,0, Color.GREEN));
        super.addBlocks(new Block(3,0, Color.GREEN));
        super.setRotation(1);
    }

    public void rotate() {
        if(getRotation() == 1){
            blocks.get(0).setX(blocks.get(0).getX()+2);
            blocks.get(0).setY(blocks.get(0).getY()-1);
            blocks.get(1).setX(blocks.get(1).getX()+1);
            blocks.get(2).setY(blocks.get(2).getY()+1);
            blocks.get(3).setX(blocks.get(3).getX()-1);
            blocks.get(3).setY(blocks.get(3).getY()+2);
            super.setRotation(2);
        }
        if(getRotation() == 2){
            blocks.get(0).setX(blocks.get(0).getX()+1);
            blocks.get(0).setY(blocks.get(0).getY()+2);
            blocks.get(1).setY(blocks.get(1).getY()+1);
            blocks.get(2).setX(blocks.get(2).getX()-1);
            blocks.get(3).setX(blocks.get(3).getX()-2);
            blocks.get(3).setY(blocks.get(3).getY()-1);
            super.setRotation(3);
        }
        if(getRotation() == 3){
            blocks.get(0).setX(blocks.get(0).getX()-2);
            blocks.get(0).setY(blocks.get(0).getY()+1);
            blocks.get(1).setX(blocks.get(1).getX()-1);
            blocks.get(2).setY(blocks.get(2).getY()-1);
            blocks.get(3).setX(blocks.get(3).getX()+1);
            blocks.get(3).setY(blocks.get(3).getY()-2);
            super.setRotation(4);
        }
        if(getRotation() == 4){
            blocks.get(0).setX(blocks.get(0).getX()-1);
            blocks.get(0).setY(blocks.get(0).getY()-2);
            blocks.get(1).setY(blocks.get(1).getY()-1);
            blocks.get(2).setX(blocks.get(2).getX()+1);
            blocks.get(3).setX(blocks.get(3).getX()+2);
            blocks.get(3).setY(blocks.get(3).getY()+1);
            super.setRotation(1);
        }
    }

//    public boolean canRotate() {
//        return false;
//    }
}
