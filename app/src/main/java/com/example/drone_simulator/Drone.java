package com.example.drone_simulator;

public class Drone {
    private int xPosition;
    private int yPosition;

    public Drone(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
