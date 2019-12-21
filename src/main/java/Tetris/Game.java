package Tetris;

import Services.Engine;
import Services.KeyControls;
import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import javafx.scene.input.KeyCode;
import lombok.Builder;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

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
        engine.addToOnTick(this::clearLines);
        this.engine.start();
    }

    public boolean fall() {
        if (canFall()) {
            currentShape.getBlocks().forEach(block -> block.setY(block.getY() + 1));
            return true;
        }else {
            copyShapeToTetrion();
            switchShapes();
            return false;
        }
    }

    private boolean canFall() {
        if (currentShape.getBlocks().stream().allMatch(block -> block.getY() < tetrion[0].length - 1)) {
            return currentShape.getBlocks().stream()
                    .allMatch(block -> tetrion[block.getX()][block.getY() + 1] == null);
        } else {
            return false;
        }
    }

    public void clearLines(){
        for (int rowIndex = tetrion[0].length-1; rowIndex > 0; rowIndex--) {
            if (isLineFull(rowIndex)) {
                removeLine(rowIndex);
                moveAllBlocksAboveDown(rowIndex);
            }
        }
    }

    private boolean isLineFull(final int rowIndex){
        for (final Block[] blocks : tetrion) {
            if (blocks[rowIndex] == null) {
                return false;
            }
        }
            return true;
    }

    private void moveAllBlocksAboveDown(final int rowIndex) {
        for (int row = rowIndex-1; row > 0 ; row--) {
            for (int column = 0; column < tetrion.length; column++) {
                Optional<Block> presentBlock = Optional.ofNullable(tetrion[column][row]);
                if (presentBlock.isPresent()) {
                    tetrion[column][row].setY(row + 1);

                }else {tetrion[column][row] = null;}
            }
        }
    }


    private void removeLine(final int rowIndex) {
        for (int columnIndex = 0; columnIndex < tetrion.length; columnIndex++) {
            tetrion[columnIndex][rowIndex] = null;
        }
    }

    private void copyShapeToTetrion(){
        currentShape.getBlocks().forEach(this::addBlockToTetrion);
    }

    private void switchShapes(){
        currentShape = nextShape;
        nextShape = shapeFactory.createShape();
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
            if (fall()) {
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

    public void setCurrentShape(final Shape currentShape) {
        this.currentShape = currentShape;
    }

    public void setNextShape(final Shape nextShape) {
        this.nextShape = nextShape;
    }
}
