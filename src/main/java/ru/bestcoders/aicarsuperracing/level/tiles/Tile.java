package ru.bestcoders.aicarsuperracing.level.tiles;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile extends Pane {

    public enum TileType {
        CROSS,
        VERTICAL,
        HORIZONTAL,
        GRASS,
    }

    private final Image texture = new Image(getClass().getResourceAsStream("/textures/4bit_road_tiles.png"));
    private ImageView block;

    public Tile (TileType type,int x, int y) {
        block = new ImageView(texture);
        block.setFitHeight(64);
        block.setFitWidth(64);
        setTranslateX(x);
        setTranslateY(y);

        switch (type) {
            case CROSS:
                block.setViewport(new Rectangle2D(0, 0, 64, 64));
                break;
            case HORIZONTAL:
                block.setViewport(new Rectangle2D(64, 64, 64, 64));
                break;
            case VERTICAL:
                block.setViewport(new Rectangle2D(64 * 2, 64 * 2, 64, 64));
                break;
            case GRASS:
                block.setViewport(new Rectangle2D(64 * 3, 64 * 3, 64, 64));
                break;
        }

        getChildren().add(block);
    }

}
