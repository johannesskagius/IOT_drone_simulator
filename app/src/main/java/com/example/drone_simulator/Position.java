package com.example.drone_simulator;

public class Position {
    private int x;
    private int y;

    public Position(String position) {
        this.x = getXPosition(position);
        this.y = getYPosition(position);
    }

    private int getYPosition(String position) {
        String y = position.substring(0,2);
        return Integer.parseInt(y);
    }

    private int getXPosition(String position) {
        String x = position.substring(0,2);
        return Integer.parseInt(x);
    }
}
