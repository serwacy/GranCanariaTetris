package Tetris;

import Services.ScoreCounter;
import Services.ShapeDynamics;
import Shapes.Block;
import Shapes.Shape;

public class Game {
    private static Block[][] tetrion;
    private Shape currentShape;
    private Shape nextShape;
    private ShapeDynamics shapeDynamics;
    private int level;

    public Game() {
        tetrion = new Block[10][20];
        level = 1;
        currentShape = ShapeFactory.createShape();
        nextShape = ShapeFactory.createShape();
        shapeDynamics = new ShapeDynamics(currentShape);
    }

    public void startGame(){
        shapeDynamics.setInterval(level);
        shapeDynamics.start();
        ScoreCounter.INSTANCE.resetScore();
    }
    public void pauseGame(){
        System.out.println("paused game");
    }

    public Shape getNextShape() {
        return nextShape;
    }
    public void endGame(){
        shapeDynamics.stop();
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
    public Shape getCurrentShape() {
        return currentShape;
    }

    public static Block[][] getTetrion() {
        return tetrion;
    }

    public void addBlockToTetrion(Block block){
        tetrion[block.getX()][block.getY()]=new Block(block.getX(),block.getY(),block.getColor());
    }
}
