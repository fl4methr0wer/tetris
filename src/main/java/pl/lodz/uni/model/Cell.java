package pl.lodz.uni.model;

public record Cell(
        int row,
        int col
) {
    public Cell movedDown() {
        return new Cell(row + 1, col);
    }
    public Cell movedRight() {
        return new Cell(row, col + 1);
    }
    public Cell movedLeft() {
        return new Cell(row , col - 1);
    }
}
