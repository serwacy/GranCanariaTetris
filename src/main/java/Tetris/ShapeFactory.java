package Tetris;

import Shapes.*;
import java.util.Random;

public class ShapeFactory {
    private static final String GREEN = "/blocks/green.png";
    private static final String BLUE = "/blocks/blue.png";
    private static final String RED = "/blocks/red.png";
    private static final String GREY = "/blocks/grey.png";
    private static final String MAGENTA = "/blocks/magenta.png";
    private static final String LIGHT_BLUE = "/blocks/lightblue.png";
    private static final String YELLOW = "/blocks/yellow.png";

    public Shape createShape() {
        int random = new Random().nextInt(7)+1;
        switch (random) {
            case 1:
                return new LShape(BLUE);
            case 2:
                return new OShape(RED);
            case 3:
                return new IShape(GREEN);
            case 4:
                return new TShape(LIGHT_BLUE);
            case 5:
                return new SShape(YELLOW);
            case 6:
                return new ZShape(GREY);
            case 7:
                return new JShape(MAGENTA);
            default:
                return null;
        }
    }
}
