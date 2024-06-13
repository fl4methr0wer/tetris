package pl.lodz.uni;

import pl.lodz.uni.controller.GameController;
import pl.lodz.uni.model.Pile;
import pl.lodz.uni.view.GameFrame;
import pl.lodz.uni.view.GridPanel;

import javax.swing.*;

public class Main {
    private static int ROWS = 20;
    private static int COLS = 12;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame();
            GridPanel gridPanel = new GridPanel();

            GameController gameController = new GameController(gridPanel, new Pile(ROWS, COLS));
        });
    }
}