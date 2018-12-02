package ru.bestcoders.aicarsuperracing.level;

import javafx.scene.layout.Pane;
import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.entities.GameObject;
import ru.bestcoders.aicarsuperracing.level.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class LevelMap {

    private List<Tile> tiles;
    private int mapWidth;

    public int[][] map = {{0, 9,0,0,0,9,0,0,0,0,0,9,0,0,0,0,0,9,0,0},
                          {0, 2,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0},
                          {11,1,3,3,3,1,3,3,3,3,3,1,3,3,3,3,3,1,3,10},
                          {0, 2,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0},
                          {0, 2,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0},
                          {11,1,3,3,3,1,3,3,3,3,3,1,3,3,3,3,3,1,3,10},//
                          {0, 2,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0},
                          {0, 2,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0},
                          {11,1,3,3,3,1,3,3,3,3,3,1,3,3,3,3,3,1,3,10},
                          {0, 2,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0},
                          {0, 8,0,0,0,8,0,0,0,0,0,8,0,0,0,0,0,8,0,0}};

    public int[][] mapForBreakingPoints =
           {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    public int[][] objectMap =                          //не изменять
           {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    public int[][] visitedMap =                         //карта посещенных перекрестков. не изменять
                   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    public LevelMap() {
        tiles = new ArrayList<>();
        mapWidth = map[0].length;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Tile.TileType type;

                switch (map[i][j]){
                    case 0:
                        type = Tile.TileType.GRASS;
                        break;
                    case 1:
                        type = Tile.TileType.CROSS;
                        break;
                    case 2:
                        type = Tile.TileType.VERTICAL;
                        break;
                    case 3:
                        type = Tile.TileType.HORIZONTAL;
                        break;
                    case 4:
                        type = Tile.TileType.TRICROSSRIGHT;
                        break;
                    case 5:
                        type = Tile.TileType.TRICROSSLEFT;
                        break;
                    case 6:
                        type = Tile.TileType.TRICROSSTOP;
                        break;
                    case 7:
                        type = Tile.TileType.TRICROSSBOTTOM;
                        break;
                    case 8:
                        type = Tile.TileType.ENDTOP;
                        break;
                    case 9:
                        type = Tile.TileType.ENDBOTTOM;
                        break;
                    case 10:
                        type = Tile.TileType.ENDLEFT;
                        break;
                    case 11:
                        type = Tile.TileType.ENDRIGHT;
                        break;
                    default:
                        type = Tile.TileType.GRASS;
                }

                tiles.add(new Tile(type, 64 * j, 64 * i));
            }
        }
    }

    public void drawTiles(Pane pane) {
        pane.getChildren().addAll(tiles);
    }

    public void drawCheckpoints(Pane pane) {
        ArrayList<Tile> points = new ArrayList<>();

        for (int i = 0; i < mapForBreakingPoints.length; i++) {
            for (int j = 0; j < mapForBreakingPoints[i].length; j++) {
                if (mapForBreakingPoints[i][j] > 0) {
                    points.add(new Tile(Tile.TileType.CHECKPOINT, 64 * j, 64 * i));
                }
            }
        }

        pane.getChildren().addAll(points);
    }

    public void placeCar(Car car) {
        int tileIndex = car.getPosY() * mapWidth + car.getPosX();
        tiles.get(tileIndex).hold(car.getDirection());
    }

    public void hold(int x, int y, Car.Direction direction) {
        int tileIndex = y * mapWidth + x;
        if (tiles.get(tileIndex).isBusy(direction)) {
            System.out.println("Car at " + x + " " + y + " already exists!");
        } else {
            tiles.get(tileIndex).hold(direction);
            System.out.println("Car holds at " + x + " " + y);
        }
        busyLog();
    }

    public void release(int x, int y, Car.Direction direction) {
        int tileIndex = y * mapWidth + x;
        tiles.get(tileIndex).release(direction);
        System.out.println("Car released at " + x + " " + y);
    }

    public void changeDirection(int x, int y, Car.Direction direction) throws Exception {
        int tileIndex = y * mapWidth + x;
        tiles.get(tileIndex).changeDirection(direction);
    }

    public boolean isBusy(int x, int y, Car.Direction direction) {
        int tileIndex = y * mapWidth + x;
        return tiles.get(tileIndex).isBusy(direction);
    }

    public void busyLog() {
        System.out.println("\nTile busy test started:");
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            if (t.isBusyBL()) {
                System.out.println("Busy to bottom or left on " + i);
            } else if (t.isBusyUR()) {
                System.out.println("Busy to top or right on " + i);
            }
        }
    }

    public boolean setGameObject(GameObject object) {
        if (objectMap[object.getPosY()][object.getPosX()] == 0) {
            objectMap[object.getPosY()][object.getPosX()] = 1;
            int tileIndex = object.getPosY() * mapWidth + object.getPosX();
            tiles.get(tileIndex).holdAll();
            return true;
        } else {
            return false;
        }
    }

    public void removeGameObject(int posX, int posY) {
        objectMap[posY][posX] = 0;
        int tileIndex = posY * mapWidth + posX;
        tiles.get(tileIndex).releaseAll();
    }
}
