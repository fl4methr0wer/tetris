package pl.lodz.uni.view;

import pl.lodz.uni.controller.GameOverListener;

import javax.swing.*;

public class GameOverPopup implements GameOverListener {
    @Override
    public void onGameOver() {
        JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("GAME OVER IN GAME OVER POPUP called");
    }
}