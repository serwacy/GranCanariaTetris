package Tetris;

import Shapes.*;

import java.util.Random;

public class ShapeFactory {
    public static Shape createShape() {
        int random = new Random().nextInt(7);
        switch (random) {
            case 1:
                return new LShape();
            case 2:
                return new OShape();
            case 3:
                return new IShape();
            case 4:
                return new TShape();
            case 5:
                return new SShape();
            case 6:
                return new ZShape();
            case 7:
                return new JShape();
            default:
                return null;
        }
    }
}
