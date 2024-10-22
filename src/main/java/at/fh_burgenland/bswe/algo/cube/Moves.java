package at.fh_burgenland.bswe.algo.cube;

import at.fh_burgenland.bswe.algo.cube.util.CubeUtils;

/**
 * Possible moves
 */
public enum Moves {

    /**
     * Left clockwise
     */
    L("L"),
    /**
     * Left counter clockwise
     */
    Li("L'"),
    /**
     * Right clockwise
     */
    R("R"),
    /**
     * Right counter clockwise
     */
    Ri("R'"),
    /**
     * Up clockwise
     */
    U("U"),
    /**
     * Up counter clockwise
     */
    Ui("U'"),
    /**
     * Down clockwise
     */
    D("D"),
    /**
     * Down counter clockwise
     */
    Di("D'"),
    /**
     * Front clockwise
     */
    F("F"),
    /**
     * Front counter clockwise
     */
    Fi("F'"),
    /**
     * Back clockwise
     */
    B("B"),
    /**
     * Back counter clockwise
     */
    Bi("B'"),

    /**
     * Rotate on X axis clockwise
     */
    X("X"),
    /**
     * Rotate on X axis counter clockwise
     */
    Xi("X'"),
    /**
     * Rotate on Y axis clockwise
     */
    Y("Y"),
    /**
     * Rotate on Y axis counter clockwise
     */
    Yi("Y'"),
    /**
     * Rotate on Z axis clockwise
     */
    Z("Z"),
    /**
     * Rotate on Z axis counter clockwise
     */
    Zi("Z'"),

    /**
     * Left 180°
     */
    L2("L2"),
    /**
     * Right 180°
     */
    R2("R2"),
    /**
     * Up 180°
     */
    U2("U2"),
    /**
     * Down 180°
     */
    D2("D2"),
    /**
     * Front 180°
     */
    F2("F2"),
    /**
     * Back 180°
     */
    B2("B2"),
    /**
     * Rotate on X axis 180°
     */
    X2("X2"),
    /**
     * Rotate on Y axis 180°
     */
    Y2("Y2"),
    /**
     * Rotate on Z axis 180°
     */
    Z2("Z2"),

    // for 4x4x4+ cubes
    /**
     * Rotate 2 left layers clockwise
     */
    TwoL("2L"),
    /**
     * Rotate 2 left layers counter clockwise
     */
    TwoLi("2L'"),
    // ... to be continued
    ;

    /**
     * The string notation
     */
    private final String notation;

    Moves(String notation) {
        this.notation = notation;
    }

    public void apply(Cube cube) {
        switch (this) {
            case L -> rotateL(cube);
            case Li -> {
                rotateL(cube);
                rotateL(cube);
                rotateL(cube);
            }
            case R -> rotateR(cube);
            case Ri -> {
                rotateR(cube);
                rotateR(cube);
                rotateR(cube);
            }
            case U -> rotateU(cube);
            case Ui -> {
                rotateU(cube);
                rotateU(cube);
                rotateU(cube);
            }
            case D -> rotateD(cube);
            case Di -> {
                rotateD(cube);
                rotateD(cube);
                rotateD(cube);
            }
            case F -> rotateF(cube);
            case Fi -> {
                rotateF(cube);
                rotateF(cube);
                rotateF(cube);
            }
            case B -> rotateB(cube);
            case Bi -> {
                rotateB(cube);
                rotateB(cube);
                rotateB(cube);
            }
            case X -> rotateX(cube);
            case Xi -> {
                rotateX(cube);
                rotateX(cube);
                rotateX(cube);
            }
            case Y -> rotateY(cube);
            case Yi -> {
                rotateY(cube);
                rotateY(cube);
                rotateY(cube);
            }
            case Z -> rotateZ(cube);
            case Zi -> {
                rotateZ(cube);
                rotateZ(cube);
                rotateZ(cube);
            }
            case L2 -> {
                rotateL(cube);
                rotateL(cube);
            }
            case R2 -> {
                rotateR(cube);
                rotateR(cube);
            }
            case U2 -> {
                rotateU(cube);
                rotateU(cube);
            }
            case D2 -> {
                rotateD(cube);
                rotateD(cube);
            }
            case F2 -> {
                rotateF(cube);
                rotateF(cube);
            }
            case B2 -> {
                rotateB(cube);
                rotateB(cube);
            }
            case X2 -> {
                rotateX(cube);
                rotateX(cube);
            }
            case Y2 -> {
                rotateY(cube);
                rotateY(cube);
            }
            case Z2 -> {
                rotateZ(cube);
                rotateZ(cube);
            }
            // to be continued for 4x4x4+ cubes...
            default -> throw new UnsupportedOperationException("Rotation not implemented: " + this);
        }
    }

    /**
     * Gets the Rotate object matching the notation
     *
     * @param notation the notation, e.g. "F"
     * @return the corresponding Rotate
     */
    public static Moves getRotation(String notation) {
        for (Moves moves : Moves.values()) {
            if (moves.notation.equals(notation)) {
                return moves;
            }
        }
        return null;
    }

    /**
     * Gets the inverse of the given move
     *
     * @return the inverse, e.g. the inverse of "L" is "L'"
     */
    public Moves getInverse() {
        return switch (this) {
            case L -> Li;
            case Li -> L;
            case R -> Ri;
            case Ri -> R;
            case U -> Ui;
            case Ui -> U;
            case D -> Di;
            case Di -> D;
            case F -> Fi;
            case Fi -> F;
            case B -> Bi;
            case Bi -> B;
            case X -> Xi;
            case Xi -> X;
            case Y -> Yi;
            case Yi -> Y;
            case Z -> Zi;
            case Zi -> Z;
            case L2 -> L2;
            case R2 -> R2;
            case U2 -> U2;
            case D2 -> D2;
            case F2 -> F2;
            case B2 -> B2;
            case X2 -> X2;
            case Y2 -> Y2;
            case Z2 -> Z2;
            case TwoL -> TwoLi;
            case TwoLi -> TwoL;
        };
    }

    private void rotateL(Cube cube) {
        cube.getLeft().rotateClockwise();

        Side sideToTheLeft = cube.getBack();
        Side sideToTheTop = cube.getTop();
        Side sideToTheRight = cube.getFront();
        Side sideToTheBottom = cube.getBottom();

        CubeColor[] leftEdge = sideToTheLeft.getColumn(cube.getSize() - 1);
        CubeColor[] topEdge = sideToTheTop.getRow(cube.getSize() - 1);
        CubeColor[] rightEdge = sideToTheRight.getColumn(0);
        CubeColor[] bottomEdge = sideToTheBottom.getRow(0);

        sideToTheLeft.setColumn(cube.getSize() - 1, bottomEdge);
        sideToTheTop.setRow(cube.getSize() - 1,  CubeUtils.inverseArray(leftEdge));
        sideToTheRight.setColumn(0, topEdge);
        sideToTheBottom.setRow(0, CubeUtils.inverseArray(rightEdge));
    }

    private void rotateR(Cube cube) {
        cube.getRight().rotateClockwise();
        Side sideToTheLeft = cube.getFront();
        Side sideToTheTop = cube.getTop();
        Side sideToTheRight = cube.getBack();
        Side sideToTheBottom = cube.getBottom();

        CubeColor[] leftEdge = sideToTheLeft.getColumn(cube.getSize() - 1);
        CubeColor[] topEdge = sideToTheTop.getColumn(cube.getSize() - 1);
        CubeColor[] rightEdge = sideToTheRight.getColumn(0);
        CubeColor[] bottomEdge = sideToTheBottom.getColumn(cube.getSize() - 1);

        sideToTheLeft.setColumn(cube.getSize() - 1, bottomEdge);
        sideToTheTop.setColumn(cube.getSize() - 1, leftEdge);
        sideToTheRight.setColumn(0, CubeUtils.inverseArray(topEdge));
        sideToTheBottom.setColumn(cube.getSize() - 1, CubeUtils.inverseArray(rightEdge));
    }

    private void rotateU(Cube cube) {
        // TODO
    }

    private void rotateD(Cube cube) {
        // TODO
    }

    private void rotateF(Cube cube) {
        cube.getFront().rotateClockwise();
        Side sideToTheLeft = cube.getLeft();
        Side sideToTheTop = cube.getTop();
        Side sideToTheRight = cube.getRight();
        Side sideToTheBottom = cube.getBottom();

        CubeColor[] leftEdge = sideToTheLeft.getColumn(cube.getSize() - 1);
        CubeColor[] topEdge = sideToTheTop.getRow(cube.getSize() - 1);
        CubeColor[] rightEdge = sideToTheRight.getColumn(0);
        CubeColor[] bottomEdge = sideToTheBottom.getRow(0);

        sideToTheLeft.setColumn(cube.getSize() - 1, bottomEdge);
        sideToTheTop.setRow(cube.getSize() - 1, CubeUtils.inverseArray(leftEdge));
        sideToTheRight.setColumn(0, topEdge);
        sideToTheBottom.setRow(0, CubeUtils.inverseArray(rightEdge));
    }

    private void rotateB(Cube cube) {
        cube.getBack().rotateClockwise();
        Side sideToTheLeft = cube.getRight();
        Side sideToTheTop = cube.getTop();
        Side sideToTheRight = cube.getLeft();
        Side sideToTheBottom = cube.getBottom();

        CubeColor[] leftEdge = sideToTheLeft.getColumn(cube.getSize() - 1);
        CubeColor[] topEdge = sideToTheTop.getRow(0);
        CubeColor[] rightEdge = sideToTheRight.getColumn(0);
        CubeColor[] bottomEdge = sideToTheBottom.getRow(cube.getSize() - 1);

        sideToTheLeft.setColumn(cube.getSize() - 1, topEdge);
        sideToTheTop.setRow(0, CubeUtils.inverseArray(rightEdge));
        sideToTheRight.setColumn(0, bottomEdge);
        sideToTheBottom.setRow(cube.getSize() - 1, CubeUtils.inverseArray(leftEdge));
    }

    private void rotateX(Cube cube) {
        Side front = cube.getFront();
        Side right = cube.getRight();
        Side top = cube.getTop();
        Side left = cube.getLeft();
        Side back = cube.getBack();
        Side bottom = cube.getBottom();

        cube.setTop(front);
        cube.setFront(bottom);
        cube.setLeft(left.rotateCounterClockwise());
        cube.setRight(right.rotateClockwise());
        cube.setBottom(back.rotateClockwise().rotateClockwise());
        cube.setBack(top.rotateClockwise().rotateClockwise());
    }

    private void rotateY(Cube cube) {
        // TODO
    }

    private void rotateZ(Cube cube) {
        Side front = cube.getFront();
        Side right = cube.getRight();
        Side top = cube.getTop();
        Side left = cube.getLeft();
        Side back = cube.getBack();
        Side bottom = cube.getBottom();

        cube.setTop(left.rotateClockwise());
        cube.setFront(front.rotateClockwise());
        cube.setLeft(bottom.rotateClockwise());
        cube.setRight(top.rotateClockwise());
        cube.setBottom(right.rotateClockwise());
        cube.setBack(back.rotateCounterClockwise());
    }

}