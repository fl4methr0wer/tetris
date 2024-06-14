package pl.lodz.uni;

import pl.lodz.uni.controller.GameController;
import pl.lodz.uni.controller.KeyboardInputHandler;
import pl.lodz.uni.model.Pile;
import pl.lodz.uni.view.GameFrame;
import pl.lodz.uni.view.GameOverPopup;
import pl.lodz.uni.view.GridPanel;
import pl.lodz.uni.view.ScorePanel;

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

            GameController gameController = new GameController(gridPanel, new Pile(ROWS, COLS));

            gameController.registerGameOverListener(new GameOverPopup());

            KeyboardInputHandler inputHandler = new KeyboardInputHandler(gameController);
            gridPanel.setFocusable(true);
            gridPanel.addKeyListener(inputHandler);

            //gameFrame.setScorePanel(new ScorePanel());
        });
    }
}