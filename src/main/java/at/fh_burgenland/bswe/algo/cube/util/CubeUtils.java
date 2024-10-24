package at.fh_burgenland.bswe.algo.cube.util;

import at.fh_burgenland.bswe.algo.cube.Cube;
import at.fh_burgenland.bswe.algo.cube.CubeColor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

@Log4j2
public class CubeUtils {

    private CubeUtils() {
    }

    /**
     * Rotates a 2d array clockwise
     *
     * @param array array to rotate
     * @return the rotated array
     */
    public static CubeColor[][] rotateArrayClockwise(CubeColor[][] array) {
        int length = array.length;
        CubeColor[][] rotated = new CubeColor[length][length];

        for(int x=0;x<=(array.length-1);x++)
            for (int y=0;y<=(array[0].length-1);y++)
                rotated[x][y]=array[(array[0].length-1)-y][x];

        return rotated;
    }

    /**
     * Rotates a 2d array counter clockwise
     *
     * @param array array to rotate
     * @return the rotated array
     */
    public static CubeColor[][] rotateArrayCounterClockwise(CubeColor[][] array) {
        int length = array.length;
        CubeColor[][] rotated = new CubeColor[length][length];

        for(int x=0;x<=(array.length-1);x++)
            for (int y=0;y<=(array[0].length-1);y++)
                rotated[(array[0].length-1)-y][x]=array[x][y];

        return rotated;
    }

    /**
     * inverses an array
     *
     * @param array array to inverse
     * @return the inversed array
     */
    public static CubeColor[] inverseArray(CubeColor[] array) {
        int length = array.length;
        CubeColor[] inverse = new CubeColor[length];
        for(int i=length-1; i>=0;i--){
            inverse[i]=array[(length-1)-i];
        }
        return inverse;
    }

    public static String[] getBlankSpace(final int size) {
        String[] space = new String[size];
        Arrays.fill(space, "     ".repeat(size));
        return space;
    }

    public static String[] joinStrings(String[]... sides) {
        return Arrays.stream(sides)
                .reduce((a, b) -> IntStream.range(0, a.length)
                        .mapToObj(i -> a[i] + " " + b[i])
                        .toArray(String[]::new))
                .orElse(new String[0]);
    }

    public static Cube readCubeFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), Cube.class);
        } catch (IOException e) {
            log.error("Error reading cube from file", e);
            return null;
        }
    }

    public static void writeCubeToFile(Cube cube, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), cube);
        } catch (IOException e) {
            log.error("Error writing cube to file", e);
        }
    }
}
