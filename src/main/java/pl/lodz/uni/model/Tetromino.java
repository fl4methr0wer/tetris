package pl.lodz.uni.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tetromino extends CellContainer {

    public Tetromino() {
        super(new ArrayList<>());
    }

    public Tetromino(Collection<Cell> cells) {
        super(cells);
    }

    public Tetromino movedRigh() {
        Collection<Cell> movedRightCells = this.getCells().stream()
                .map(c -> new Cell(c.row(), c.col()+1))
                .collect(Collectors.toList());
        return new Tetromino(movedRightCells);
    }

    public Tetromino movedLeft() {
        Collection<Cell> movedRightCells = this.getCells().stream()
                .map(c -> new Cell(c.row(), c.col() - 1))
                .collect(Collectors.toList());
        return new Tetromino(movedRightCells);
    }

    public Tetromino movedDown() {
        Collection<Cell> movedRightCells = this.getCells().stream()
                .map(c -> new Cell(c.row() + 1, c.col()))
                .collect(Collectors.toList());
        return new Tetromino(movedRightCells);
    }

    public Tetromino rotatedLeft() {
        return new Tetromino(flippedHorizontally(transposed(this.getCells())));
    }

    private Collection<Cell> transposed(Collection<Cell> cells) {
        return cells.stream()
                .map(cell -> new Cell(cell.col(), cell.row())) // Swap row and column
                .collect(Collectors.toList());
    }

    private Collection<Cell> flippedHorizontally(Collection<Cell> cells) {
        int maxDimension = maxRowOrCol(cells);
        Collection<Cell> flipped = new ArrayList<>();
        for (Cell cell : cells) {
            flipped.add(new Cell(maxDimension - cell.row(), cell.col()));
        }
        return flipped;
    }

    private int maxRowOrCol(Collection<Cell> cells) {
        int max = 0;
        for (Cell cell : cells) {
            max = Math.max(max, cell.row());
            max = Math.max(max, cell.col());
        }
        return max;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Tetromino))
            return false;

        Tetromino other = (Tetromino) obj;

        // Compare sizes of cell collections
        if (getCells().size() != other.getCells().size())
            return false;

        // Check if each cell in this Tetromino exists in the other Tetromino
        for (Cell cell : getCells()) {
            if (!other.getCells().contains(cell)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCells());
    }


    @Override
    public String toString() {
        return "Tetromino{" +
                "cells=" + cells +
                '}';
    }
}
