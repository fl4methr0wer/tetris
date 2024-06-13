package pl.lodz.uni.controller;

import pl.lodz.uni.model.Cell;
import pl.lodz.uni.model.Pile;
import pl.lodz.uni.model.Tetromino;
import pl.lodz.uni.model.TetrominoFactory;
import pl.lodz.uni.model.exception.CellTakenException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final Timer timer;
    private final GameStateListener userInterface;
    private Tetromino tetromino = new Tetromino();
    private final Pile pile;
    private Cell topLeftTetrominoCell;

    public GameController(GameStateListener userInterface, Pile pile) {
        this.userInterface = userInterface;
        this.pile = pile;
        this.timer = new Timer(1000, this::onTimerTick);
        this.timer.start();
        this.topLeftTetrominoCell = new Cell(0, pile.getColMaxIndex() / 2);
    }

    private void onTimerTick(ActionEvent actionEvent) {
        System.out.println("TIMER TICK");
        moveTetrominoDown();
    }

    private void notifyCellsChanged() {
        List<Cell> allCells = new ArrayList<>(tetromino.getCells());
        allCells.addAll(pile.getCells());
        userInterface.onCellsMoved(allCells);
    }

    public void moveTetrominoDown() {
        Tetromino movedDown = tetromino.movedDown();
        checkNewTetrominoPositionAndHandleExceptions(movedDown);
        notifyCellsChanged();
    }

    public void checkNewTetrominoPositionAndHandleExceptions(Tetromino movedTetromino) {
        try {
            pile.validateTetrominoPositionOrThrow(movedTetromino, topLeftTetrominoCell);
            topLeftTetrominoCell = new Cell(topLeftTetrominoCell.row() + 1, topLeftTetrominoCell.col());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("New position is invalid");
        } catch (CellTakenException e) {

            // transfer tetromino cells to pile
            pile.addAll(tetromino.getCells());
            topLeftTetrominoCell = new Cell(0, pile.getColMaxIndex() / 2);
            tetromino = TetrominoFactory.createRandomTetromino();
            try {
                pile.validateTetrominoPositionOrThrow(tetromino, topLeftTetrominoCell);
            } catch (CellTakenException ee) {
                System.out.println("GAM OVER" + e.getMessage());
            }
        }
    }









}
