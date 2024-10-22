package at.fh_burgenland.bswe.algo.cube.util;

import at.fh_burgenland.bswe.algo.cube.CubeColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeUtilsTest {

    @Test
    void rotateArrayClockwise() {
        CubeColor[][] array = {
                {CubeColor.RED, CubeColor.GREEN},
                {CubeColor.BLUE, CubeColor.YELLOW}
        };
        CubeColor[][] rotated = CubeUtils.rotateArrayClockwise(array);

        assertArrayEquals(rotated, new CubeColor[][]{
                {CubeColor.BLUE, CubeColor.RED},
                {CubeColor.YELLOW, CubeColor.GREEN}
        });
    }

    @Test
    void rotateArrayCounterClockwise() {
        CubeColor[][] array = {
                {CubeColor.RED, CubeColor.GREEN},
                {CubeColor.BLUE, CubeColor.YELLOW}
        };
        CubeColor[][] rotated = CubeUtils.rotateArrayCounterClockwise(array);

        assertArrayEquals(rotated, new CubeColor[][]{
                {CubeColor.GREEN, CubeColor.YELLOW},
                {CubeColor.RED, CubeColor.BLUE}
        });
    }

    @Test
    void inverseArray() {
        CubeColor[] array = {CubeColor.RED, CubeColor.YELLOW, CubeColor.GREEN, CubeColor.BLUE};
        CubeColor[] inverse = CubeUtils.inverseArray(array);

        assertArrayEquals(inverse, new CubeColor[]{CubeColor.BLUE, CubeColor.GREEN, CubeColor.YELLOW, CubeColor.RED});
    }
}