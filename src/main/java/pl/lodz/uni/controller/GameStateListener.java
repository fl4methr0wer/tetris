package pl.lodz.uni.controller;

import pl.lodz.uni.model.Cell;
import java.util.Collection;

public interface GameStateListener {
    Collection<Cell> onCellsMoved(Collection<Cell> cells);
    void onGameOver();
}
