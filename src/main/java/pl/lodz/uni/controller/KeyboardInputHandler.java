package pl.lodz.uni.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputHandler implements KeyListener {

    private final GameEngine gameEngine;

    public KeyboardInputHandler(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
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
                gameEngine.moveTetrominoLeft();
                break;
            case KeyEvent.VK_RIGHT:
                gameEngine.moveTetrominoRight();
                break;
            case KeyEvent.VK_UP:
                gameEngine.rotateTetromino();
                break;
            case KeyEvent.VK_DOWN:
                gameEngine.moveTetrominoDown();
                break;
            case KeyEvent.VK_SPACE:
                if (gameEngine.gameState.equals(GameState.STOPPED))
                    gameEngine.start();
                else
                    gameEngine.stop();
            case KeyEvent.VK_R:
                gameEngine.restart();
            default:
                // Handle other keys if needed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}
