package Tetris;

import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Game {
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

    public Shape getCurrentShape() {
        return currentShape;
    }

    public Shape getNextShape() {
        return nextShape;
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
