package ru.bestcoders.aicarsuperracing.entities;

import javafx.scene.layout.Pane;

import java.util.Objects;

public abstract class GameObject extends Pane {

    protected int posX;
    protected int posY;

    public int getPosX() {
        return posX;
    };

    public int getPosY() {
        return posY;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return posX == that.posX &&
                posY == that.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
