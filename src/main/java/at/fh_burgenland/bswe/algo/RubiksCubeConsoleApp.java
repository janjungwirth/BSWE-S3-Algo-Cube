package at.fh_burgenland.bswe.algo;

import at.fh_burgenland.bswe.algo.cube.Cube;
import at.fh_burgenland.bswe.algo.cube.util.CubeUtils;


public class RubiksCubeConsoleApp {

    public static void main(String[] args) {
        Cube cube = CubeUtils.readCubeFromFile("src/main/resources/3x3cube-superFlip.json");
        cube.printCube();
    }

}
