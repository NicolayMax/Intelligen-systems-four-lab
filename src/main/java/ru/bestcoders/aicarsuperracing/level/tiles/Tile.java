package ru.bestcoders.aicarsuperracing.level.tiles;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ru.bestcoders.aicarsuperracing.entities.Car;

public class Tile extends Pane {

    public enum TileType {
        GRASS,
        CROSS,
        VERTICAL,
        HORIZONTAL,
        TRICROSSRIGHT,
        TRICROSSLEFT,
        TRICROSSTOP,
        TRICROSSBOTTOM,
        ENDTOP,
        ENDBOTTOM,
        ENDLEFT,
        ENDRIGHT,
        CHECKPOINT,
    }

    private final Image texture = new Image(getClass().getResourceAsStream("/textures/4bit_road_tiles.png"));
    private ImageView block;
    private boolean busyUR = false;
    private boolean busyBL = false;
    private boolean road;
    private TileType type;

    public Tile (TileType type,int x, int y) {
        block = new ImageView(texture);
        this.type = type;

        switch (type) {
            case CROSS:
                block.setViewport(new Rectangle2D(0, 0, 64, 64));
                road = true;
                break;
            case HORIZONTAL:
                block.setViewport(new Rectangle2D(64, 64, 64, 64));
                road = true;
                break;
            case VERTICAL:
                block.setViewport(new Rectangle2D(64 * 2, 64 * 2, 64, 64));
                road = true;
                break;
            case GRASS:
                block.setViewport(new Rectangle2D(64 * 3, 64 * 3, 64, 64));
                road = false;
                break;
            case TRICROSSRIGHT:
                block.setViewport(new Rectangle2D(0, 64 * 2, 64, 64));
                road = true;
                break;
            case TRICROSSLEFT:
                block.setViewport(new Rectangle2D(64 * 2, 0, 64, 64));
                road = true;
                break;
            case TRICROSSTOP:
                block.setViewport(new Rectangle2D(0, 64, 64, 64));
                road = true;
                break;
            case TRICROSSBOTTOM:
                block.setViewport(new Rectangle2D(64, 0, 64, 64));
                road = true;
                break;
            case ENDRIGHT:
                block.setViewport(new Rectangle2D(64, 64 * 3, 64, 64));
                road = true;
                holdAll();
                break;
            case ENDLEFT:
                block.setViewport(new Rectangle2D(64 * 3, 64, 64, 64));
                road = true;
                holdAll();
                break;
            case ENDTOP:
                block.setViewport(new Rectangle2D(64 * 2, 64 * 3, 64, 64));
                road = true;
                holdAll();
                break;
            case ENDBOTTOM:
                block.setViewport(new Rectangle2D(64 * 3, 64 * 2, 64, 64));
                road = true;
                holdAll();
                break;
            case CHECKPOINT:
                block = new ImageView(new Image(getClass().getResourceAsStream("/textures/checkpoint.png")));
                block.setViewport(new Rectangle2D(0, 0, 64, 64));
                road = true;
                break;
        }

        block.setFitHeight(64);
        block.setFitWidth(64);
        setTranslateX(x);
        setTranslateY(y);

        getChildren().add(block);
    }

    public boolean isBusyUR() {
        return busyUR;
    }

    public boolean isBusyBL() {
        return busyBL;
    }

    public boolean isRoad() {
        return road;
    }

    public void hold(Car.Direction direction) {
        if (direction.equals(Car.Direction.DOWN) || direction.equals(Car.Direction.LEFT)) {
            busyBL = true;
        } else {
            busyUR = true;
        }
    }

    public void holdAll() {
        busyBL = true;
        busyUR = true;
    }

    public void releaseAll() {
        if (type != Tile.TileType.ENDTOP &&
                type != Tile.TileType.ENDBOTTOM &&
                type != Tile.TileType.ENDLEFT &&
                type != Tile.TileType.ENDRIGHT) {
            busyBL = false;
            busyUR = false;
        }
    }

    public void changeDirection(Car.Direction direction) throws Exception {
        if (direction.equals(Car.Direction.DOWN) || direction.equals(Car.Direction.LEFT)) {
            if (!busyBL) {
                busyUR = false;
                busyBL = true;
            } else {
                throw new Exception("Can't rotate to " + direction +"!");
            }
        } else {
            if (!busyUR) {
                busyBL = false;
                busyUR = true;
            } else {
                throw new Exception("Can't rotate to " + direction +"!");
            }
        }
    }

    public void release(Car.Direction direction) {
        if (direction.equals(Car.Direction.DOWN) || direction.equals(Car.Direction.LEFT)) {
            busyBL = false;
        } else {
            busyUR = false;
        }
    }

    public boolean isBusy(Car.Direction direction) {
        if (direction.equals(Car.Direction.DOWN) || direction.equals(Car.Direction.LEFT)) {
            return busyBL;
        } else {
            return busyUR;
        }
    }
}
