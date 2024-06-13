package pl.lodz.uni.model;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public enum TetrominoFactory {
    T(() -> List.of(
            new Cell(1, 1),
            new Cell(2, 0),
            new Cell(2, 1),
            new Cell(2, 2))),
    I(() -> List.of(
            new Cell(0, 1),
            new Cell(1, 1),
            new Cell(2, 1),
            new Cell(3, 1))),
    S(() -> List.of(
            new Cell(0, 1),
            new Cell(0, 2),
            new Cell(1, 0),
            new Cell(1, 1))),
    Z(() -> List.of(
            new Cell(0, 0),
            new Cell(0, 1),
            new Cell(1, 1),
            new Cell(1, 2))),
    L(() -> List.of(
            new Cell(0, 1),
            new Cell(1, 1),
            new Cell(2, 1),
            new Cell(2, 2))),
    J(() -> List.of(
            new Cell(0, 1),
            new Cell(1, 1),
            new Cell(2, 1),
            new Cell(2, 0))),
    SQUARE(() -> List.of(
            new Cell(0, 0),
            new Cell(0, 1),
            new Cell(1, 0),
            new Cell(1, 1)));

    private final List<Cell> cells;

    TetrominoFactory(Supplier<List<Cell>> cellsSupplier) {
        this.cells = cellsSupplier.get();
    }

    public Tetromino create() {
        return new Tetromino(cells);
    }

    public static Tetromino createRandomTetromino() {
        Random random = new Random();
        TetrominoFactory[] factories = values();
        TetrominoFactory randomFactory = factories[random.nextInt(factories.length)];
        return randomFactory.create();
    }
}

