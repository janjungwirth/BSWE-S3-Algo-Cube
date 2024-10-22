package at.fh_burgenland.bswe.algo.cube;

import at.fh_burgenland.bswe.algo.cube.util.CubeUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

/**
 * Represents a side of a Rubik's cube
 */
public class Side {

    /**
     * The color of this Rubik's cube side (middle block)
     */
    @JsonProperty
    private final CubeColor color;

    /**
     * The values of this Rubik's cube side
     */
    @Getter
    @JsonProperty
    private CubeColor[][] values;

    public Side(final int size, final CubeColor color) {
        this.color = color;

        values = new CubeColor[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                values[row][col] = color;
            }
        }
    }

    @JsonCreator
    public Side(@JsonProperty("color") CubeColor color,
                @JsonProperty("values") CubeColor[][] values) {
        this.color = color;
        this.values = values;
    }

    /**
     * retrieves the values of this side as a string array
     *
     * @return the values of this side as a string array
     */
    @JsonIgnore
    public String[] getValuesAsStringArray() {
        var rows = new String[values.length];
        for (int row = 0; row < values.length; row++) {
            var sb = new StringBuilder();
            for (CubeColor col : values[row]) {
                sb.append(col).append("   ");
                sb.append(CubeColor.RESET).append("  ");
            }
            rows[row] = sb.toString();
        }
        return rows;
    }

    @JsonIgnore
    public boolean isSolved() {
        // check if all values match color
        return Arrays.stream(values)
                .flatMap(Arrays::stream)
                .allMatch(color::equals);
    }

    public CubeColor[] getRow(int row) {
        return values[row];
    }

    public CubeColor[] getColumn(int col) {
        CubeColor[] column = new CubeColor[values.length];
        for (int row = 0; row < values.length; row++) {
            column[row] = values[row][col];
        }
        return column;
    }

    public void setRow(int row, CubeColor[] newRow) {
        values[row] = newRow;
    }

    public void setColumn(int col, CubeColor[] newColumn) {
        for (int row = 0; row < values.length; row++) {
            values[row][col] = newColumn[row];
        }
    }

    /**
     * Rotates this side clockwise
     *
     * @return the rotated side
     */
    public Side rotateClockwise() {
        values = CubeUtils.rotateArrayClockwise(values);
        return this;
    }

    /**
     * Rotates this side counter clockwise
     *
     * @return the rotated side
     */
    public Side rotateCounterClockwise() {
        values = CubeUtils.rotateArrayCounterClockwise(values);
        return this;
    }

}
