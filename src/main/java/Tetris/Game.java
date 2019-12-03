package Tetris;

import Services.Engine;
import Services.KeyControls;
import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import lombok.Builder;

@Builder
public class Game {
    private Block[][] tetrion;

    private Shape currentShape;
    private Shape nextShape;

    private Engine engine;
    private KeyControls controls;
    private ScoreCounter counter;
    private ShapeFactory shapeFactory;

    public Game(final Engine engine, final KeyControls controls, final ScoreCounter counter, final ShapeFactory shapeFactory) {
        this.engine = engine;
        this.controls = controls;
        this.counter = counter;
        this.shapeFactory = shapeFactory;
        this.tetrion = new Block[10][20];
    }

    public void startGame(){
        counter.resetScore();
        this.nextShape = shapeFactory.createShape();
        this.currentShape = shapeFactory.createShape();
        this.controls.addKeyControls(this);
        this.engine.start();
    }
    public void pauseGame(){
        System.out.println("paused game");
    }

    public void endGame(){
        engine.stop();
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
    //TEMP METHOD
    public void addBlockToTetrion(Block block){
        tetrion[block.getX()][block.getY()] = new Block(block.getX(),block.getY(),block.getColor());
    }

    public Shape getNextShape() {
        return nextShape;
    }
    public Engine getEngine() {
        return engine;
    }
    public Shape getCurrentShape() {
        return currentShape;
    }
    public Block[][] getTetrion() {
        return tetrion;
    }
}
