package pl.lodz.uni.view;

import pl.lodz.uni.controller.GameStateListener;
import pl.lodz.uni.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class GridPanel extends JPanel implements GameStateListener {
    private final int ROWS;
    private final int COLUMNS;
    private final int CELL_SIZE;

    public GridPanel(int rows, int col, int cellSize) {
        this.ROWS = rows;
        this.COLUMNS = col;
        this.CELL_SIZE = cellSize;
        setPreferredSize(new Dimension(rows * cellSize, col * cellSize));
    }

    @Override
    public void onCellsMoved(Collection<Cell> cells) {
        System.out.println("GridPanel CELLS MOVED: " + cells);
        // I WANT HERE TO RERENDER EVERYTHING. RERENDER Collection<Cell> cells this method receives
    }

    @Override
    public void onGameOver() {
    }

}
