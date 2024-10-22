package at.fh_burgenland.bswe.algo.cube;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SideTest {

    @Test
    void rotateClockwise() {
        Side side = new Side(CubeColor.YELLOW,
                new CubeColor[][]{
                        {CubeColor.RED, CubeColor.GREEN, CubeColor.YELLOW},
                        {CubeColor.BLUE, CubeColor.YELLOW, CubeColor.ORANGE},
                        {CubeColor.WHITE, CubeColor.RED, CubeColor.GREEN}
                });
        side.rotateClockwise();

        assertArrayEquals(side.getValues(),
                new CubeColor[][]{
                        {CubeColor.WHITE, CubeColor.BLUE, CubeColor.RED},
                        {CubeColor.RED, CubeColor.YELLOW, CubeColor.GREEN},
                        {CubeColor.GREEN, CubeColor.ORANGE, CubeColor.YELLOW}
                });
    }

    @Test
    void rotateCounterClockwise() {
        Side side = new Side(CubeColor.YELLOW,
                new CubeColor[][]{
                        {CubeColor.RED, CubeColor.GREEN, CubeColor.YELLOW},
                        {CubeColor.BLUE, CubeColor.YELLOW, CubeColor.ORANGE},
                        {CubeColor.WHITE, CubeColor.RED, CubeColor.GREEN}
                });
        side.rotateCounterClockwise();

        assertArrayEquals(side.getValues(),
                new CubeColor[][]{
                        {CubeColor.YELLOW, CubeColor.ORANGE, CubeColor.GREEN},
                        {CubeColor.GREEN, CubeColor.YELLOW, CubeColor.RED},
                        {CubeColor.RED, CubeColor.BLUE, CubeColor.WHITE}
                });
    }
}