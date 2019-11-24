package Tetris;

import Controlers.PlayController;
import Services.KeyControls;
import Services.ScoreCounter;
import Services.ShapeDynamics;
import Shapes.Block;
import Shapes.Shape;

public class Game {
    private static Block[][] tetrion;

    private Shape currentShape;
    private Shape nextShape;
    private ShapeDynamics shapeDynamics;
    private PlayController playController;
    private KeyControls keyControls;
    private int level;


    public Game(final PlayController playController,Shape currentShape, Shape nextShape, int level) {
        tetrion = new Block[10][20];

        this.playController = playController;
        this.currentShape = currentShape;
        this.nextShape = nextShape;
        this.level = level;

        this.shapeDynamics = new ShapeDynamics(currentShape, playController);
        this.keyControls = new KeyControls(playController, this);
    }

    public void startGame(){
        ScoreCounter.INSTANCE.resetScore();
        this.currentShape.placeOnStartingPosition();
        this.keyControls.addKeyControls();
        this.shapeDynamics.setInterval(level);
        this.shapeDynamics.start();
    }
    public void pauseGame(){
        System.out.println("paused game");
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
    //TEMP METHOD
    public void addBlockToTetrion(Block block){
        tetrion[block.getX()][block.getY()] = new Block(block.getX(),block.getY(),block.getColor());
    }

    public Shape getNextShape() {
        return nextShape;
    }
    public ShapeDynamics getShapeDynamics() {
        return shapeDynamics;
    }
    public Shape getCurrentShape() {
        return currentShape;
    }
    public static Block[][] getTetrion() {
        return tetrion;
    }
}
