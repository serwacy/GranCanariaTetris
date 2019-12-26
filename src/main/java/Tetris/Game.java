package Tetris;

import Controlers.ControllerManager;
import Services.Engine;
import Services.KeyControls;
import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import javafx.scene.input.KeyCode;
import lombok.Builder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Builder
public class Game {
    //should it be here or in Engine class, where field 'level' is
    private final static int MAX_LEVEL = 20;
    private final List<Integer> levelThreshold =
            Arrays.asList(2000, 6000, 12000, 20000, 30000, 42000, 56000, 72000, 90000, 110000,
                    132000, 156000, 182000, 210000, 240000, 272000, 306000, 342000, 380000, 420000);

    private Block[][] tetrion;

    private Shape currentShape;
    private Shape nextShape;

    private Engine engine;
    private KeyControls controls;
    private ScoreCounter counter;
    private ShapeFactory shapeFactory;
    private Runnable refresh;
    private int lastNumberOfLinesCleared;

    public Game(final Block[][] tetrion, Shape currentShape, Shape nextShape,
                final Engine engine, final KeyControls controls, final ScoreCounter counter,
                final ShapeFactory shapeFactory, Runnable refresh, int lastNumberOfLinesCleared) {
        this.tetrion = tetrion;
        this.currentShape = currentShape;
        this.nextShape = nextShape;
        this.engine = engine;
        this.controls = controls;
        this.counter = counter;
        this.shapeFactory = shapeFactory;
        this.refresh = refresh;
        this.lastNumberOfLinesCleared = lastNumberOfLinesCleared;
    }

    public void startGame() {
        counter.resetScore();
        defineActions();
        this.engine.start();
    }

    public void fall() {
        if (canFall()) {
            currentShape.getBlocks().forEach(block -> block.setY(block.getY() + 1));
//            return true;
        } else {
            copyShapeToTetrion();
            clearLines();
            switchShapes();

            //How to put result of this method in PlayController to stop the game? refresh?
            if(!canFall()){
                ControllerManager.getPlayController().endGameAndExitToMenu();
            }

            raiseGameLevel(counter.getScore());
//            return false;
        }
    }

    //temporary place of this method, to change during refactor
    private void raiseGameLevel (final Integer score){
        if(engine.getLevel() < MAX_LEVEL) {
            if (score > levelThreshold.get(engine.getLevel() - 1)) {
                engine.setLevel(engine.getLevel() + 1);
            }
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

    private void clearLines() {
        int numberOfLinesCleared = 0;
        for (int rowIndex = tetrion[0].length - 1; rowIndex > 0; rowIndex--) {
            if (isLineFull(rowIndex)) {
                removeLine(rowIndex);
                moveAllBlocksAboveDown(rowIndex);
                rowIndex++;
                numberOfLinesCleared++;
            }
        }
        addScoreForClearingLines(numberOfLinesCleared);
    }

    private boolean isLineFull(final int rowIndex) {
        for (final Block[] blocks : tetrion) {
            if (blocks[rowIndex] == null) {
                return false;
            }
        }
        return true;
    }

    private void moveAllBlocksAboveDown(final int rowIndex) {
        for (int row = rowIndex - 1; row > 0; row--) {
            for (int column = 0; column < tetrion.length; column++) {
                Optional<Block> presentBlock = Optional.ofNullable(tetrion[column][row]);
                if (presentBlock.isPresent()) {
                    Block block = tetrion[column][row];
                    tetrion[column][row] = null;
                    block.setY(row + 1);
                    addBlockToTetrion(block);
                } else {
                    tetrion[column][row] = null;
                }
            }
        }
    }


    private void removeLine(final int rowIndex) {
        for (int columnIndex = 0; columnIndex < tetrion.length; columnIndex++) {
            tetrion[columnIndex][rowIndex] = null;
            counter.addScore(200);
        }
    }

    private void addScoreForClearingLines (final int linesCleared){
        switch (linesCleared){
            case 1:
                counter.addScore(100*engine.getLevel());
                break;
            case 2:
                counter.addScore(300*engine.getLevel());
                break;
            case 3:
                counter.addScore(500*engine.getLevel());
                break;
            case 4:
                if(lastNumberOfLinesCleared == 4){
                    counter.addScore(1200*engine.getLevel());
                } else {
                    counter.addScore(800*engine.getLevel());
                }
//                counter.addScore(800*engine.getLevel());
                break;
            default:
                //do nothing
        }
        this.lastNumberOfLinesCleared = linesCleared;
    }

    private void copyShapeToTetrion() {
        currentShape.getBlocks().forEach(this::addBlockToTetrion);
    }

    private void switchShapes() {
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
//            if (fall()) {
            fall();
            refresh.run();
            counter.addScore(1);
//            }
        });
    }

    public void pauseGame() {
        System.out.println("paused game");
    }

    public void endGame() {
        engine.stop();
    }

    //TEMP METHOD
    private void addBlockToTetrion(Block block) {
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
