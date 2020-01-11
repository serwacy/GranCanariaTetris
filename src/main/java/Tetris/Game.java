package Tetris;

import Controlers.ControllerManager;
import Services.Engine;
import Services.KeyControls;
import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import javafx.scene.input.KeyCode;
import lombok.Builder;

import java.util.Optional;

@Builder
public class Game {
    private static int lastNumberOfLinesCleared = 0;

    private Block[][] tetrion;

    private Shape currentShape;
    private Shape nextShape;

    private Engine engine;
    private KeyControls controls;
    private ScoreCounter counter;
    private ShapeFactory shapeFactory;
    private Runnable refresh;

    public Game(final Block[][] tetrion, Shape currentShape, Shape nextShape,
                final Engine engine, final KeyControls controls, final ScoreCounter counter,
                final ShapeFactory shapeFactory, Runnable refresh) {
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
        moveCurrentShapeToCenter();
    }

    private void moveCurrentShapeToCenter() {
        currentShape.getBlocks()
                .forEach(x -> x.setX(x.getX() + 3));
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
            invokeShapeFall();
            refresh.run();
            counter.addScore(1);
        });
    }

    public void invokeShapeFall() {
        if (canFall()) {
            currentShape.getBlocks().forEach(block -> block.setY(block.getY() + 1));
        } else {
            copyShapeToTetrion();
            clearLines();
            switchShapeReferencesAndGenerateNewShape();

            if (!canFall()) {
                ControllerManager.getPlayController().endGameAndExitToMenu();
            }
            engine.raiseGameLevel(counter.getScore());
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

    private void copyShapeToTetrion() {
        currentShape.getBlocks().forEach(this::addBlockToTetrion);
    }

    private void addBlockToTetrion(Block block) {
        tetrion[block.getX()][block.getY()] = new Block(block.getX(), block.getY(), block.getColor());
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
        if (numberOfLinesCleared > 0) {
            addScoreForClearingLines(numberOfLinesCleared);
        }
    }

    private void removeLine(final int rowIndex) {
        for (int columnIndex = 0; columnIndex < tetrion.length; columnIndex++) {
            tetrion[columnIndex][rowIndex] = null;
        }
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

    private void addScoreForClearingLines(final int linesCleared) {
        switch (linesCleared) {
            case 1:
                counter.addScore(100 * engine.getLevel());
                break;
            case 2:
                counter.addScore(300 * engine.getLevel());
                break;
            case 3:
                counter.addScore(500 * engine.getLevel());
                break;
            case 4:
                if (lastNumberOfLinesCleared == 4) {
                    counter.addScore(1200 * engine.getLevel());
                } else {
                    counter.addScore(800 * engine.getLevel());
                }
                break;
            default:
                //do nothing
        }
        lastNumberOfLinesCleared = linesCleared;
    }

    private void switchShapeReferencesAndGenerateNewShape() {
        currentShape = nextShape;
        nextShape = shapeFactory.createShape();
        moveCurrentShapeToCenter();
    }

    public void endGame() {
        controls.removeKeyControls();
        engine.stop();
    }

    public Shape getNextShape() {
        return nextShape;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public Block[][] getTetrion() {
        return tetrion;
    }

}
