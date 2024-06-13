package pl.lodz.uni;

import pl.lodz.uni.controller.GameController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameController gameController = new GameController();
        });
    }
}