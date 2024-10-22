package at.fh_burgenland.bswe.algo.cube;

import javafx.scene.paint.Color;

/**
 * Possible colors of the cube
 */
public enum CubeColor {
    RESET("\033[0m", Color.rgb(0, 0, 0)),

    RED("\033[48;2;199;8;5m", Color.rgb(199, 8, 5)),
    GREEN("\033[48;2;21;251;0m", Color.rgb(21, 251, 0)),
    YELLOW("\033[48;2;246;251;0m", Color.rgb(246, 251, 0)),
    BLUE("\033[48;2;31;1;255m", Color.rgb(31, 1, 255)),
    ORANGE("\033[48;2;255;119;0m", Color.rgb(255, 119, 0)),
    WHITE("\033[48;2;226;221;221m", Color.rgb(226, 221, 221));

    private final String code;
    private final Color rgb;

    CubeColor(String code, Color rgb) {
        this.code = code;
        this.rgb = rgb;
    }

    @Override
    public String toString() {
        return code;
    }

    public Color getRgb() {
        return rgb;
    }

}
