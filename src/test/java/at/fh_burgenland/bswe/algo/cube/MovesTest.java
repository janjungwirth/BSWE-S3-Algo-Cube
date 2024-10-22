package at.fh_burgenland.bswe.algo.cube;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MovesTest {

    @Test
    public void getMoveFromNotation() {
        Moves m = Moves.getRotation("F");
        Assertions.assertEquals(Moves.F, m);
    }

    @Test
    public void getInverse() {
        Moves fInverse = Moves.F.getInverse();
        Assertions.assertEquals(Moves.Fi, fInverse);
    }

}