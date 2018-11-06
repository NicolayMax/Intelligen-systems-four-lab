package ru.bestcoders.aicarsuperracing.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

public class Car extends GameObject {

    public enum Direction {
            UP,
            DOWN,
            LEFT,
            RIGHT
    };

    private final Image texture = new Image(getClass().getResourceAsStream("/textures/Car_Green_Front.png"));
    private ImageView carOverlay;
    private LevelMap map;

    int posX;
    int posY;
    Direction direction;

    public Car(int posX, int posY, Direction direction, LevelMap map) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
        this.map = map;

        carOverlay = new ImageView(texture);
        carOverlay.setFitHeight(64);
        carOverlay.setFitWidth(64);
        carOverlay.setViewport(new Rectangle2D(0,0, 64, 64));

        recalculateTranslation();
        recalculateRotation();

        getChildren().add(carOverlay);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        int x = posX;
        int y = posY;


        switch (direction) {
            case DOWN:
                y++;
                break;
            case UP:
                y--;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        if (!map.isBusy( x, y, direction)) {
            map.release(posX, posY, direction);
            posY = y;
            posX = x;
            map.hold(posX, posY, direction);
            recalculateTranslation();
        } else {
            System.out.println("Can't move!");
        }
    }

    public void rotateLeft() {
        try {
            switch (direction) {
                case DOWN:
                    direction = Direction.RIGHT;
                    map.changeDirection(posX, posY, direction);
                    break;
                case UP:
                    direction = Direction.LEFT;
                    map.changeDirection(posX, posY, direction);
                    break;
                case LEFT:
                    direction = Direction.DOWN;
                    break;
                case RIGHT:
                    direction = Direction.UP;
                    break;
            }

            recalculateRotation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void rotateRight() {
        try {
            switch (direction) {
                case DOWN:
                    direction = Direction.LEFT;
                    break;
                case UP:
                    direction = Direction.RIGHT;
                    break;
                case LEFT:
                    direction = Direction.UP;
                    map.changeDirection(posX, posY, direction);
                    break;
                case RIGHT:
                    direction = Direction.DOWN;
                    map.changeDirection(posX, posY, direction);
                    break;
            }

            recalculateRotation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void recalculateTranslation() {
        //temp
        int blockWidth = 64;
        setTranslateX(blockWidth * posX);
        setTranslateY(blockWidth * posY);
        System.out.println("recalcTranslation: "+blockWidth * posX+" "+blockWidth * posY);
    }

    private void recalculateRotation() {
        switch (direction) {
            case DOWN:
                setRotate(0);
                break;
            case UP:
                setRotate(180.0);
                break;
            case LEFT:
                setRotate(90.0);
                break;
            case RIGHT:
                setRotate(270.0);
                break;
        }
    }
}
