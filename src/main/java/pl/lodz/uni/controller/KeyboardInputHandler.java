package pl.lodz.uni.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputHandler implements KeyListener {

    private final GameController gameController;

    public KeyboardInputHandler(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                gameController.moveTetrominoLeft();
                break;
            case KeyEvent.VK_RIGHT:
                gameController.moveTetrominoRight();
                break;
            case KeyEvent.VK_UP:
                gameController.rotateTetromino();
                break;
            case KeyEvent.VK_DOWN:
                gameController.moveTetrominoDown();
                break;
            default:
                // Handle other keys if needed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}
