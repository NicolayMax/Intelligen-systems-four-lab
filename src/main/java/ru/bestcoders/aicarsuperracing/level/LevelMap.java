package ru.bestcoders.aicarsuperracing.level;

import javafx.scene.layout.Pane;
import ru.bestcoders.aicarsuperracing.level.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class LevelMap {

    private List<Tile> tiles;

    public LevelMap() {
        tiles = new ArrayList<>();

        // Example
        tiles.add(new Tile(Tile.TileType.GRASS,0,0));
        tiles.add(new Tile(Tile.TileType.GRASS,64,0));
        tiles.add(new Tile(Tile.TileType.GRASS,64*2,0));
        tiles.add(new Tile(Tile.TileType.GRASS,64*3,0));
        tiles.add(new Tile(Tile.TileType.HORIZONTAL,0,64));
        tiles.add(new Tile(Tile.TileType.HORIZONTAL,64,64));
        tiles.add(new Tile(Tile.TileType.HORIZONTAL,64*2,64));
        tiles.add(new Tile(Tile.TileType.HORIZONTAL,64*3,64));
        tiles.add(new Tile(Tile.TileType.GRASS,0,64 * 2));
        tiles.add(new Tile(Tile.TileType.GRASS,64,64 * 2));
        tiles.add(new Tile(Tile.TileType.GRASS,64*2,64 * 2));
        tiles.add(new Tile(Tile.TileType.GRASS,64*3,64 * 2));
    }

    public void drawTiles(Pane pane) {
        pane.getChildren().addAll(tiles);
    }
}
