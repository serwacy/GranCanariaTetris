package Tetris;

import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Game extends Application {
    private static Block[][] tetrion;
    private Shape currentShape;
    private Shape nextShape;
    private int level;

    public Game() {
        tetrion = new Block[10][20];
        level = 1;
        currentShape = ShapeFactory.createShape();
        nextShape = ShapeFactory.createShape();
        ScoreCounter scoreCounter = new ScoreCounter();
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.LEFT) {
                currentShape.moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                currentShape.moveRight();
            } else if (event.getCode() == KeyCode.SPACE) {
                currentShape.rotate();
            }
        });
    }

    private boolean canGoIn() {
        return currentShape.getBlocks().stream()
                .allMatch(x -> tetrion[x.getX()][x.getY()] == null);
    }
    private void lineCheck(){

    }
    private void fallAll(){

    }
    private void lineRemove(){

    }

    public static Block[][] getTetrion() {
        return tetrion;
    }
}
