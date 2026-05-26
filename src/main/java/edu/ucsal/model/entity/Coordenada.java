package edu.ucsal.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Coordenada implements Serializable{

    private int x;
    private int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
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

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Coordenada outra = (Coordenada) obj;

        return x == outra.x && y == outra.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
