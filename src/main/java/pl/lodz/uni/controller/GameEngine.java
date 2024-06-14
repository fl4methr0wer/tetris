package pl.lodz.uni.controller;

import pl.lodz.uni.model.Cell;
import pl.lodz.uni.model.Pile;
import pl.lodz.uni.model.Tetromino;
import pl.lodz.uni.model.TetrominoFactory;
import pl.lodz.uni.model.exception.CellTakenException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GameEngine {

    private Timer timer = new Timer(500, this::onTimerTick);;
    private final GameStateListener userInterface;
    private GameOverListener gameOverListener;
    private Tetromino tetromino = TetrominoFactory.createRandomTetromino();
    private final Pile pile;
    private Cell topLeftTetrominoCell;
    public GameState gameState = GameState.STOPPED;

    public GameEngine(GameStateListener userInterface, Pile pile) {
        this.userInterface = userInterface;
        this.pile = pile;
        this.topLeftTetrominoCell = new Cell(0, pile.getColMaxIndex() / 2);
        notifyCellsChanged();
    }

    public void start() {
        this.timer.start();
        gameState = GameState.RUNNING;
    }

    public void stop() {
        timer.stop();
        gameState = GameState.STOPPED;
    }

    public void restart() {
        this.pile.getCells().clear();
        this.tetromino = TetrominoFactory.createRandomTetromino();
        topLeftTetrominoCell = new Cell(0, pile.getColMaxIndex() / 2);
        gameState = GameState.RUNNING;
    }

    public void registerGameOverListener(GameOverListener gameOverListener) {
        this.gameOverListener = gameOverListener;
    }

    private void onTimerTick(ActionEvent actionEvent) {
        moveTetrominoDown();
    }

    private boolean isValidNewTetrominoPosition(Tetromino tetromino, Cell topLeft) {
        try {
            pile.validateTetrominoPositionOrThrow(tetromino, topLeft);
        } catch (IndexOutOfBoundsException outOfBoundsException) {
            System.out.println("OUT OF BOUNDS" + outOfBoundsException);
            return false;
        } catch (CellTakenException cellTakenException) {
            System.out.println("CELL TAKEN: " + cellTakenException);
            return false;
        }
        return true;
    }

    private Collection<Cell> cellsWithOffset(Collection<Cell> cells, Cell offset) {
        return cells.stream()
                .map(c -> new Cell(c.row() + offset.row(), c.col() + offset.col()))
                .collect(Collectors.toList());
    }

    private void notifyCellsChanged() {
        List<Cell> allCells = new ArrayList<>(cellsWithOffset(tetromino.getCells(), topLeftTetrominoCell));
        allCells.addAll(pile.getCells());
        userInterface.onCellsMoved(allCells);
    }

    private void onBottomReached() {
        System.out.println("BOTTOM REACHED");
        pile.addAll(cellsWithOffset(tetromino.getCells(), topLeftTetrominoCell));
        pile.purgeFullRows();

        tetromino = TetrominoFactory.createRandomTetromino();
        topLeftTetrominoCell = new Cell(0, pile.getColMaxIndex() / 2);
        if (!isValidNewTetrominoPosition(tetromino, topLeftTetrominoCell)) {
            System.out.println("CONTROLLER GAME OVER");

            gameOverListener.onGameOver();
            this.pile.getCells().clear();
            this.stop();
        }
        notifyCellsChanged();
    }

    public void moveTetrominoDown() {
        Cell movedDown = topLeftTetrominoCell.movedDown();
        if (!isValidNewTetrominoPosition(tetromino, movedDown)) {
            onBottomReached();
            return;
        }
        topLeftTetrominoCell = movedDown;
        notifyCellsChanged();
    }

    public void moveTetrominoRight() {
        Cell movedRight = topLeftTetrominoCell.movedRight();
        if (!isValidNewTetrominoPosition(tetromino, movedRight))
            return;
        topLeftTetrominoCell = movedRight;
        notifyCellsChanged();
    }

    public void moveTetrominoLeft() {
        Cell movedLeft = topLeftTetrominoCell.movedLeft();
        if (!isValidNewTetrominoPosition(tetromino, movedLeft))
            return;
        topLeftTetrominoCell = movedLeft;
        notifyCellsChanged();
    }

    public void rotateTetromino() {
        Tetromino rotated = tetromino.rotatedLeft();
        if (!isValidNewTetrominoPosition(rotated, topLeftTetrominoCell))
            return;
        this.tetromino = rotated;
        notifyCellsChanged();
    }

}
