package pl.lodz.uni.controller;

import pl.lodz.uni.model.Cell;
import java.util.Collection;

public interface GameStateListener {
    void onCellsMoved(Collection<Cell> cells);
}
