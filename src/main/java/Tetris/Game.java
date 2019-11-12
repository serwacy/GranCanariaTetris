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
    public Shape currentShape;
    public Shape nextShape;
    private int level;

    public Game() {
        tetrion = new Block[10][20];
        level = 1;
        currentShape = ShapeFactory.createShape();
        nextShape = ShapeFactory.createShape();
        ScoreCounter scoreCounter = new ScoreCounter();
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
