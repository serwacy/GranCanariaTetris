package Tetris;

import Services.Engine;
import Services.KeyControls;
import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import javafx.scene.input.KeyCode;
import lombok.Builder;

@Builder
public class Game{
    private Block[][] tetrion;

    private Shape currentShape = null;
    private Shape nextShape = null;

    private Engine engine;
    private KeyControls controls;
    private ScoreCounter counter;
    private ShapeFactory shapeFactory;
    private Runnable refresh;

    public Game(final Block[][] tetrion, Shape currentShape, Shape nextShape, final Engine engine, final KeyControls controls, final ScoreCounter counter, final ShapeFactory shapeFactory, Runnable refresh) {
        this.tetrion = tetrion;
        this.currentShape = currentShape;
        this.nextShape = nextShape;
        this.engine = engine;
        this.controls = controls;
        this.counter = counter;
        this.shapeFactory = shapeFactory;
        this.refresh = refresh;
    }

    public void startGame() {
        counter.resetScore();
        defineActions();
        this.engine.start();
    }

    private void defineActions() {
        this.controls.addAction(KeyCode.LEFT, () -> {
            currentShape.moveLeft(tetrion);
            refresh.run();
        });
        this.controls.addAction(KeyCode.RIGHT, () -> {
            currentShape.moveRight(tetrion);
            refresh.run();
        });
        this.controls.addAction(KeyCode.UP, () -> {
            currentShape.rotate(tetrion);
            refresh.run();
        });
        this.controls.addAction(KeyCode.DOWN, () -> {
            if (currentShape.fall(tetrion)) {
                refresh.run();
                counter.addScore(1);
            }
        });
    }

    public void pauseGame() {
        System.out.println("paused game");
    }

    public void endGame() {
        engine.stop();
    }

    //TEMP METHOD
    public void addBlockToTetrion(Block block) {
        tetrion[block.getX()][block.getY()] = new Block(block.getX(), block.getY(), block.getColor());
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
