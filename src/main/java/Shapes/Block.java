package Shapes;

import javafx.scene.paint.ImagePattern;

public class Block {
    private int x;
    private int y;
    private ImagePattern color;

    public Block(int x, int y, ImagePattern color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public ImagePattern getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
