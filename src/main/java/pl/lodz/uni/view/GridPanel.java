package pl.lodz.uni.view;

import pl.lodz.uni.controller.GameStateListener;
import pl.lodz.uni.model.Cell;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class GridPanel extends JPanel implements GameStateListener {

    @Override
    public Collection<Cell> onCellsMoved(Collection<Cell> cells) {
        return List.of();
    }

    @Override
    public void onGameOver() {

    }
}
