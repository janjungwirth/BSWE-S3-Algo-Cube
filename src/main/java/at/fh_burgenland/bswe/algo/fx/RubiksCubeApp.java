package at.fh_burgenland.bswe.algo.fx;

import at.fh_burgenland.bswe.algo.cube.Cube;
import at.fh_burgenland.bswe.algo.cube.Moves;
import at.fh_burgenland.bswe.algo.cube.Side;
import at.fh_burgenland.bswe.algo.cube.util.CubeUtils;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.input.InputModifier;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

@Log4j2
public class RubiksCubeApp extends GameApplication implements CubeChangeHandler {

    static Cube cube;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1000);
        settings.setHeight(780);
        settings.setTitle("Rubik's Cube");
        settings.setVersion("1.0");
    }

    @Override
    protected void initGame() {
        cube = CubeUtils.readCubeFromFile("src/main/resources/3x3cube-superFlip.json");
        cube.setCubeChangeHandler(this);

        drawScene();
    }

    @Override
    public void drawScene() {
        getGameScene().clearGameViews();
        getGameScene().clearUINodes();
        createBackground();
        createSidesView();
        create3DCube();
        createMenu();
    }

    private void createBackground() {
        getGameScene().setBackgroundColor(Color.DARKGRAY);
        AmbientLight light = new AmbientLight();
        light.setColor(Color.WHITE);
        entityBuilder().view(light).buildAndAttach();
    }

    private void createSidesView() {
        final double size = 100d;
        int margin = 15;
        int spacer = 120;
        addSideView(cube.getTop(), "Top", margin + spacer, margin, size);
        addSideView(cube.getLeft(), "Left", margin, margin + spacer, size);
        addSideView(cube.getFront(), "Front", margin + spacer, margin + spacer, size);
        addSideView(cube.getRight(), "Right", margin + 2 * spacer, margin + spacer, size);
        addSideView(cube.getBack(), "Back", margin + 3 * spacer, margin + spacer, size);
        addSideView(cube.getBottom(), "Bottom", margin + spacer, margin + 2 * spacer, size);
    }

    private void addSideView(Side side, String name, double x, double y, double size) {
        Node text = getUIFactoryService().newText(name, Color.BLACK, 10);
        text.setLayoutX(x);
        text.setLayoutY(y);
        addUINode(text);
        entityBuilder().at(x, y + 5).view(getSide(side, size)).buildAndAttach();
    }

    private Node getSide(Side side, double size) {
        Group group = new Group();
        Rectangle bg = new Rectangle(size, size, Paint.valueOf("BLACK"));
        group.getChildren().add(bg);
        int cubeSize = cube.getSize();
        int pixel = (int)(size / cubeSize);
        for (int i = 0; i < cubeSize; i++) {
            for (int j = 0; j < cubeSize; j++) {
                Rectangle r = new Rectangle(pixel-1, pixel-1, side.getValues()[i][j].getRgb());
                r.setX(1 + j * pixel);
                r.setY(1 + i * pixel);
                group.getChildren().add(r);
            }
        }

        return group;
    }

    private void create3DCube() {
        entityBuilder().at(500, 300).view(getTop3D()).buildAndAttach();
        entityBuilder().at(288, 406).view(getFront3D()).buildAndAttach();
        entityBuilder().at(500, 512).view(getRight3D()).buildAndAttach();
    }

    private Node getTop3D() {
        Node top3D = getSide(cube.getTop(), 300d);

        top3D.getTransforms().add(new Rotate(-60, Rotate.X_AXIS));
        top3D.getTransforms().add(new Rotate(45, Rotate.Z_AXIS));

        return top3D;
    }

    private Node getFront3D() {
        Node front3D = getSide(cube.getFront(), 300d);
        front3D.getTransforms().add(new Rotate(30, Rotate.X_AXIS));
        front3D.getTransforms().add(new Rotate(45, Rotate.Y_AXIS));

        return front3D;
    }

    private Node getRight3D() {
        Node right3D = getSide(cube.getRight(), 300d);
        right3D.getTransforms().add(new Rotate(-30, Rotate.X_AXIS));
        right3D.getTransforms().add(new Rotate(45, Rotate.Y_AXIS));

        return right3D;
    }

    private void createMenu() {
        List<Moves> topRow = List.of(Moves.L, Moves.R, Moves.U, Moves.D, Moves.F, Moves.B, Moves.X, Moves.Y, Moves.Z);
        List<Moves> middleRow = List.of(Moves.Li, Moves.Ri, Moves.Ui, Moves.Di, Moves.Fi, Moves.Bi, Moves.Xi, Moves.Yi, Moves.Zi);
        List<Moves> bottomRow = List.of(Moves.L2, Moves.R2, Moves.U2, Moves.D2, Moves.F2, Moves.B2, Moves.X2, Moves.Y2, Moves.Z2);

        for (int i = 0; i < topRow.size(); i++) {
            Node top = createButtonRow(topRow, i);
            top.setTranslateX(800);
            addUINode(top);

            Node middle = createButtonRow(middleRow, i);
            middle.setTranslateX(850);
            addUINode(middle);

            Node bottom = createButtonRow(bottomRow, i);
            bottom.setTranslateX(900);
            addUINode(bottom);
        }
    }

    private Node createButtonRow(List<Moves> topRow, final int i) {
        Button b = new Button(topRow.get(i).name());
        b.setFont(Font.font(15));
        double px = 40;
        b.setMinSize(px, px);
        b.setMaxSize(px, px);
        b.setOnAction(event -> cube.rotate(topRow.get(i)));
        b.setTranslateY(20 + i * (px + 10));
        return b;
    }

    @Override
    protected void initInput() {
        onKeyBuilder(KeyCode.L).onActionEnd(() -> cube.rotate(Moves.L));
        onKeyBuilder(KeyCode.L, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Li));
        onKeyBuilder(KeyCode.L, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.L2));
        onKeyBuilder(KeyCode.R).onActionEnd(() -> cube.rotate(Moves.R));
        onKeyBuilder(KeyCode.R, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Ri));
        onKeyBuilder(KeyCode.R, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.R2));
        onKeyBuilder(KeyCode.U).onActionEnd(() -> cube.rotate(Moves.U));
        onKeyBuilder(KeyCode.U, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Ui));
        onKeyBuilder(KeyCode.U, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.U2));
        onKeyBuilder(KeyCode.D).onActionEnd(() -> cube.rotate(Moves.D));
        onKeyBuilder(KeyCode.D, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Di));
        onKeyBuilder(KeyCode.D, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.D2));
        onKeyBuilder(KeyCode.F).onActionEnd(() -> cube.rotate(Moves.F));
        onKeyBuilder(KeyCode.F, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Fi));
        onKeyBuilder(KeyCode.F, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.F2));
        onKeyBuilder(KeyCode.B).onActionEnd(() -> cube.rotate(Moves.B));
        onKeyBuilder(KeyCode.B, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Bi));
        onKeyBuilder(KeyCode.B, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.B2));
        onKeyBuilder(KeyCode.X).onActionEnd(() -> cube.rotate(Moves.X));
        onKeyBuilder(KeyCode.X, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Xi));
        onKeyBuilder(KeyCode.X, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.X2));
        onKeyBuilder(KeyCode.Y).onActionEnd(() -> cube.rotate(Moves.Y));
        onKeyBuilder(KeyCode.Y, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Yi));
        onKeyBuilder(KeyCode.Y, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.Y2));
        onKeyBuilder(KeyCode.Z).onActionEnd(() -> cube.rotate(Moves.Z));
        onKeyBuilder(KeyCode.Z, InputModifier.SHIFT).onActionEnd(() -> cube.rotate(Moves.Zi));
        onKeyBuilder(KeyCode.Z, InputModifier.CTRL).onActionEnd(() -> cube.rotate(Moves.Z2));
    }

    public static void main(String[] args) {
        launch(args);
    }
}