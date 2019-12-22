package Tetris;

import Shapes.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

public class ShapeFactory {
    private static final String GREEN = "./src/main/resources/blocks/green.png";
    private static final String BLUE = "./src/main/resources/blocks/blue.png";
    private static final String RED = "./src/main/resources/blocks/red.png";
    private static final String GREY = "./src/main/resources/blocks/grey.png";
    private static final String MAGENTA = "./src/main/resources/blocks/magenta.png";
    private static final String LIGHT_BLUE = "./src/main/resources/blocks/lightblue.png";
    private static final String YELLOW = "./src/main/resources/blocks/yellow.png";

    public Shape createShape() {
        int random = new Random().nextInt(7)+1;
        switch (random) {
            case 1:
                return new LShape(assignColor(BLUE));
            case 2:
                return new OShape(assignColor(RED));
            case 3:
                return new IShape(assignColor(GREEN));
            case 4:
                return new TShape(assignColor(LIGHT_BLUE));
            case 5:
                return new SShape(assignColor(YELLOW));
            case 6:
                return new ZShape(assignColor(GREY));
            case 7:
                return new JShape(assignColor(MAGENTA));
            default:
                return null;
        }
    }
    private String assignColor(String colorPath){
         String color = "";
            try {
               color = new File(colorPath).toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return color;
    }
}
