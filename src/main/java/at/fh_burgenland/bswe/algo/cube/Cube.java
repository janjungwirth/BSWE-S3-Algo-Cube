package at.fh_burgenland.bswe.algo.cube;

import at.fh_burgenland.bswe.algo.cube.util.CubeUtils;
import at.fh_burgenland.bswe.algo.fx.CubeChangeHandler;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Rubik's cube
 */
@Getter
@Setter
public class Cube {

    private int size;

    private Side front;
    private Side right;
    private Side top;
    private Side left;
    private Side back;
    private Side bottom;

    @JsonIgnore
    private CubeChangeHandler cubeChangeHandler;

    public Cube(final int size) {
        this.size = size;
        this.front = new Side(size, CubeColor.ORANGE);
        this.right = new Side(size, CubeColor.BLUE);
        this.top = new Side(size, CubeColor.YELLOW);
        this.left = new Side(size, CubeColor.GREEN);
        this.back = new Side(size, CubeColor.RED);
        this.bottom = new Side(size, CubeColor.WHITE);
    }

    @JsonCreator
    public Cube(@JsonProperty("size") int size,
                @JsonProperty("front") Side front,
                @JsonProperty("right") Side right,
                @JsonProperty("top") Side top,
                @JsonProperty("left") Side left,
                @JsonProperty("back") Side back,
                @JsonProperty("bottom") Side bottom) {
        this.size = size;
        this.front = front;
        this.right = right;
        this.top = top;
        this.left = left;
        this.back = back;
        this.bottom = bottom;
    }

    public void rotate(Moves rotation) {
        rotation.apply(this);
        cubeChanged();
    }

    private void cubeChanged() {
        if (cubeChangeHandler != null) {
            cubeChangeHandler.drawScene();
        }
    }

    /**
     * Print the cube to the console
     */
    public void printCube() {
        var empty = CubeUtils.getBlankSpace(size);
        var s = top.getValuesAsStringArray();

        var topString = CubeUtils.joinStrings(empty, s);
        for (String string : topString) {
            System.out.println(string);
            System.out.println();
        }

        var l = left.getValuesAsStringArray();
        var f = front.getValuesAsStringArray();
        var r = right.getValuesAsStringArray();
        var b = back.getValuesAsStringArray();
        var middle = CubeUtils.joinStrings(l, f, r, b);
        for (String string : middle) {
            System.out.println(string);
            System.out.println();
        }

        var bot = bottom.getValuesAsStringArray();
        var bottom = CubeUtils.joinStrings(empty, bot);
        for (String string : bottom) {
            System.out.println(string);
            System.out.println();
        }
    }

    /**
     * Check if the cube is solved
     * @return true if solved
     */
    @JsonIgnore
    public boolean isSolved() {
        return front.isSolved() &&
                right.isSolved() &&
                top.isSolved() &&
                left.isSolved() &&
                back.isSolved() &&
                bottom.isSolved();
    }

}
