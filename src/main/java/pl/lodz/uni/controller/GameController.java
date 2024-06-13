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

public class GameController {

    private final Timer timer;
    private final GameStateListener userInterface;
    private Tetromino tetromino = TetrominoFactory.createRandomTetromino();
    private final Pile pile;
    private Cell topLeftTetrominoCell;

    public GameController(GameStateListener userInterface, Pile pile) {
        this.userInterface = userInterface;
        this.pile = pile;
        this.timer = new Timer(500, this::onTimerTick);
        this.timer.start();
        this.topLeftTetrominoCell = new Cell(0, pile.getColMaxIndex() / 2);
    }

    private void onTimerTick(ActionEvent actionEvent) {
        //System.out.println("TIMER TICK");
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
            System.out.println("GAME OVER");
            userInterface.onGameOver();
            timer.stop();
        }
        notifyCellsChanged();
    }

    public void moveTetrominoDown() {
        if (!isValidNewTetrominoPosition(tetromino, topLeftTetrominoCell.movedDown())) {
            onBottomReached();
        }

        topLeftTetrominoCell = topLeftTetrominoCell.movedDown();
        notifyCellsChanged();
    }

    public void moveTetrominoRight() {
        if (!isValidNewTetrominoPosition(tetromino, topLeftTetrominoCell.movedRight()))
            return;
        topLeftTetrominoCell = topLeftTetrominoCell.movedRight();
        notifyCellsChanged();
    }

    public void moveTetrominoLeft() {
        if (!isValidNewTetrominoPosition(tetromino, topLeftTetrominoCell.movedLeft()))
            return;
        topLeftTetrominoCell = topLeftTetrominoCell.movedLeft();
        notifyCellsChanged();
    }

    public void rotateTetromino() {
        Tetromino rotated = tetromino.rotatedLeft();
        if (!isValidNewTetrominoPosition(rotated, topLeftTetrominoCell))
            return;
        System.out.println("GC AFTER ROTATE: " + rotated);
        this.tetromino = rotated;
        notifyCellsChanged();
    }

}
