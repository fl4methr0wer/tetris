package pl.lodz.uni.model;

import java.util.ArrayList;
import java.util.Collection;

public class CellContainer {
    protected Collection<Cell> cells;

    protected CellContainer() {
        this.cells = new ArrayList<>();
    }

    protected CellContainer(Collection<Cell> cells) {
        this.cells = cells;
    }

    public void addAll(Collection<Cell> cells) {
        this.cells.addAll(cells);
    }

    public Collection<Cell> getCells() {
        return cells;
    }
}
