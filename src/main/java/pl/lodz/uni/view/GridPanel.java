package pl.lodz.uni.view;

import pl.lodz.uni.controller.GameStateListener;
import pl.lodz.uni.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class GridPanel extends JPanel implements GameStateListener {
    private final int ROWS;
    private final int COLUMNS;
    private final int CELL_SIZE;
    private Collection<Cell> cells;

    public GridPanel(int rows, int columns, int cellSize) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.CELL_SIZE = cellSize;
        this.cells = new ArrayList<>();
        setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));
    }

    @Override
    public void onCellsMoved(Collection<Cell> cells) {
        this.cells = cells;
        repaint();
    }

    @Override
    public void onGameOver() {
        System.out.println("UI Game Over"); // Debugging statement
        repaint();
        // Handle game over logic here, if needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintCells(g);
    }

    private void paintBackground(Graphics g) {
        Color lightGreen = new Color(167, 171, 138);
        g.setColor(lightGreen);
        int fullWidth = COLUMNS * CELL_SIZE;
        int fullHeight = ROWS * CELL_SIZE;
        g.fillRect(0, 0, fullWidth, fullHeight);
    }

    private void paintCells(Graphics graphics) {
        Color darkGreen = new Color(136, 145, 118);
        Color black = new Color(10, 10, 25);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                if (cells.contains(new Cell(row, col))) {
                    graphics.setColor(black);
                } else {
                    graphics.setColor(darkGreen);
                }
                paintRectangle(graphics, x, y);
            }
        }
    }

    private void paintRectangle(Graphics g, int x, int y) {
        int outerOffset = (int) (CELL_SIZE * 0.1);
        int innerOffset = (int) (CELL_SIZE * 0.1);
        // Outer rectangle
        g.drawRect(x + outerOffset, y + outerOffset, CELL_SIZE - outerOffset, CELL_SIZE - outerOffset);
        // Inner rectangle
        g.fillRect(x + outerOffset + innerOffset, y + outerOffset + innerOffset,
                CELL_SIZE - outerOffset - 2 * innerOffset, CELL_SIZE - outerOffset - 2 * innerOffset);
    }
}
