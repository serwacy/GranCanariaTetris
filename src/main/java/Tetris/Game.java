package Tetris;

import Services.ScoreCounter;
import Services.TetrisEngine;
import Shapes.Block;
import Shapes.Shape;

public enum Game {
    INSTANCE;

    private Block[][] tetrion = new Block[10][20];
    private TetrisEngine tetrisEngine = new TetrisEngine();
    private Shape currentShape;
    private Shape nextShape;
    private int level = 1;

    public void startGame() {
        resetGame();
        this.nextShape = ShapeFactory.createShape();
        tetrisEngine.setInterval(level);
        tetrisEngine.start();
    }

    private void resetGame() {
        this.level = 1;
        ScoreCounter.INSTANCE.resetScore();
    }

    public void nextShapeToCurrentShapeAndInitNext() {
        this.currentShape = this.nextShape;
        this.currentShape.placeOnStartingPosition();
        this.nextShape = ShapeFactory.createShape();
    }

    public void pauseGame() {
        System.out.println("paused game");
    }

    public void endGame() {
        tetrisEngine.stop();
    }

    private boolean canGoIn() {
        return currentShape.getBlocks().stream()
                .allMatch(x -> tetrion[x.getX()][x.getY()] == null);
    }

    private void lineCheck() {

    }

    private void fallAll() {

    }

    private void lineRemove() {

    }

    //TEMP METHOD
    public void addBlockToTetrion(Block block) {
        tetrion[block.getX()][block.getY()] = new Block(block.getX(), block.getY(), block.getColor());
    }

    public Shape getNextShape() {
        return nextShape;
    }

//    public TetrisEngine getTetrisEngine() {
//        return tetrisEngine;
//    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public Block[][] getTetrion() {
        return tetrion;
    }
}
