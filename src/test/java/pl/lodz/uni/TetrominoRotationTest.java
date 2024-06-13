package pl.lodz.uni;

import pl.lodz.uni.model.Cell;

import org.junit.jupiter.api.Test;
import pl.lodz.uni.model.Tetromino;
import pl.lodz.uni.model.TetrominoFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TetrominoRotationTest {

    @Test
    void testLShapeFromFactoryIsCorrect() {
        Tetromino factoryLShape = TetrominoFactory.L.create();
        Tetromino expected = new Tetromino(
                List.of(new Cell(0, 1),
                        new Cell(1, 1),
                        new Cell(2, 1),
                        new Cell(2,2))
        );
        assertEquals(expected, factoryLShape);
    }


    @Test
    void testLShapeRotatesCorrectly() {
        Tetromino rotated = TetrominoFactory.L.create().rotatedLeft();
        Tetromino expected = new Tetromino(
                List.of(
                        new Cell(0,2),
                        new Cell(1,0),
                        new Cell(1, 1),
                        new Cell(1,2)
                )
        );
        assertEquals(expected, rotated);
    }

}
