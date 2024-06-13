package pl.lodz.uni.model;

import pl.lodz.uni.model.exception.CellTakenException;

import java.util.Collection;

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

    public void validateTetrominoPositionOrThrow(Tetromino tetromino, Cell topLeftCellOffset)
            throws IndexOutOfBoundsException, CellTakenException {
        Collection<Cell> tetrominoCells = tetromino.getCells();

        for (Cell cell : tetrominoCells) {
            int absoluteRow = cell.row() + topLeftCellOffset.row();
            int absoluteCol = cell.col() + topLeftCellOffset.col();
            boolean rowOutOfBounds = absoluteRow > ROW_MAX_INDEX || absoluteRow < 0;
            boolean colOutOfBounds = absoluteCol > COL_MAX_INDEX || absoluteCol < 0;
            if (colOutOfBounds || rowOutOfBounds)
                throw new IndexOutOfBoundsException("Tetromino " + tetromino + " is out of bounds");
            if (this.cells.contains(cell))
                throw new CellTakenException("Cell " + cell + " is already taken");
        }
    }
}
