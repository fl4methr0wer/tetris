package pl.lodz.uni.model;

import pl.lodz.uni.model.exception.CellTakenException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Pile extends CellContainer {

    private final int ROW_MAX_INDEX;
    private final int COL_MAX_INDEX;

    public Pile(int rows, int columns) {
        super();
        this.ROW_MAX_INDEX = rows - 1;
        this.COL_MAX_INDEX = columns - 1;
    }

    public int getRowMaxIndex() {
        return ROW_MAX_INDEX;
    }

    public int getColMaxIndex() {
        return COL_MAX_INDEX;
    }

    public void purgeFullRows() {
        List<Integer> fullRows = new ArrayList<>();
        for (int row = 0; row <= ROW_MAX_INDEX; row++) {
            if (isFullRow(row, COL_MAX_INDEX + 1)) {
                fullRows.add(row);
            }
        }
        for (int row : fullRows) {
            purgeRow(row);
            moveDownRowsAbove(row);
        }
    }

    private void moveDownRowsAbove(int row) {
        cells = cells.stream()
                .map(cell -> cell.row() <= row ? cell.movedDown() : cell)
                .collect(Collectors.toSet());
    }

    private void purgeRow(int row) {
        cells = cells.stream()
                .filter(cell -> cell.row() != row)
                .collect(Collectors.toSet());
    }

    private boolean isFullRow(int row, int width) {
        return cells.stream()
                .filter(cell -> cell.row() == row)
                .count() == width;
    }

    public void validateTetrominoPositionOrThrow(Tetromino tetromino, Cell topLeftCellOffset)
            throws IndexOutOfBoundsException, CellTakenException {

        for (Cell cell : tetromino.getCells()) {
            int absoluteRow = cell.row() + topLeftCellOffset.row();
            int absoluteCol = cell.col() + topLeftCellOffset.col();
            Cell absolutePosition = new Cell(absoluteRow, absoluteCol);
            boolean rowOutOfBounds = absoluteRow > ROW_MAX_INDEX || absoluteRow < 0;
            boolean colOutOfBounds = absoluteCol > COL_MAX_INDEX || absoluteCol < 0;
            if (colOutOfBounds || rowOutOfBounds)
                throw new IndexOutOfBoundsException("Tetromino " + tetromino + " is out of bounds");
            if (this.cells.contains(absolutePosition))
                throw new CellTakenException("Cell " + cell + " is already taken");
        }
    }
}
