package pl.lodz.uni;

import pl.lodz.uni.controller.GameEngine;
import pl.lodz.uni.controller.KeyboardInputHandler;
import pl.lodz.uni.model.Pile;
import pl.lodz.uni.view.GameFrame;
import pl.lodz.uni.view.GameOverPopup;
import pl.lodz.uni.view.GridPanel;

import javax.swing.*;

public class Main {
    private static int ROWS = 20;
    private static int COLS = 12;
    private static int CELL_SIZE = 36;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame();

            GridPanel gridPanel = new GridPanel(ROWS, COLS, CELL_SIZE);
            gameFrame.setGridPanel(gridPanel); // Pass the gridPanel instance here

            GameEngine gameEngine = new GameEngine(gridPanel, new Pile(ROWS, COLS));

            gameEngine.registerGameOverListener(new GameOverPopup());
            //gameEngine.registerGameOverListener(gridPanel);

            KeyboardInputHandler inputHandler = new KeyboardInputHandler(gameEngine);
            gridPanel.setFocusable(true);
            gridPanel.addKeyListener(inputHandler);

        });
    }
}