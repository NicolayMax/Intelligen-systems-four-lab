package ru.bestcoders.aicarsuperracing.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

public class Construction extends GameObject {

    private final Image texture = new Image(getClass().getResourceAsStream("/textures/construction.png"));
    private ImageView constOverlay;
    private LevelMap map;

    public Construction(int posX, int posY, LevelMap map) {
        this.posX = posX;
        this.posY = posY;
        this.map = map;

        constOverlay = new ImageView(texture);
        constOverlay.setFitWidth(64);
        constOverlay.setFitHeight(64);
        constOverlay.setViewport(new Rectangle2D(0,0, 64,64));
        setTranslateX(posX * 64);
        setTranslateY(posY * 64);

        getChildren().add(constOverlay);
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
}
