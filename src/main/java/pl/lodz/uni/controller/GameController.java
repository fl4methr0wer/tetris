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
        System.out.println("TIMER TICK");
        //moveTetrominoDown();
    }

    private void notifyCellsChanged() {
        List<Cell> absoluteTetrominoCells = tetromino.getCells().stream()
                .map(c -> new Cell(c.row() + topLeftTetrominoCell.row(), c.col() + topLeftTetrominoCell.col()))
                .toList();
        List<Cell> allCells = new ArrayList<>(absoluteTetrominoCells);
        allCells.addAll(pile.getCells());
        userInterface.onCellsMoved(allCells);
    }

    public void moveTetrominoDown() {
        topLeftTetrominoCell = new Cell(topLeftTetrominoCell.row() + 1, topLeftTetrominoCell.col());
        notifyCellsChanged();
    }

    public void moveTetrominoRight() {
        topLeftTetrominoCell = topLeftTetrominoCell.movedRight();
        notifyCellsChanged();
    }

    public void moveTetrominoLeft() {
        topLeftTetrominoCell = topLeftTetrominoCell.movedLeft();
        notifyCellsChanged();
    }

    public void rotateTetromino() {
        System.out.println("GC BEFORE ROTATE: " + tetromino.getCells());
        Tetromino rotated = tetromino.rotatedLeft();
        System.out.println("GC AFTER ROTATE: " + rotated);
        this.tetromino = rotated;
        notifyCellsChanged();
    }

}
