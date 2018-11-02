package ru.bestcoders.aicarsuperracing.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car extends GameObject {

    private final Image texture = new Image(getClass().getResourceAsStream("/textures/Car_Green_Front.png"));
    private ImageView carOverlay;

    public Car(int x, int y) {
        carOverlay = new ImageView(texture);
        carOverlay.setFitHeight(64);
        carOverlay.setFitWidth(32);
        carOverlay.setViewport(new Rectangle2D(0,0, 32, 58));
        setTranslateX(x);
        setTranslateY(y);

        getChildren().add(carOverlay);
    }
}
