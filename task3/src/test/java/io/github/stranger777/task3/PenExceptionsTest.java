package io.github.stranger777.task3;

import org.junit.*;
import org.junit.jupiter.params.ParameterizedTest;

public class PenExceptionsTest {

    static final int NEGATIVE_INK = -1;
    static final double NEGATIVE_SIZE = -1.0;
    static final double ZERO_SIZE = 0.0;
    static final String NOTHING_COLOR = "";

    @Test(expected = IllegalArgumentException.class)
   public void pen_inkNegative_throwIllArgException(){
        Pen illegalPen = new Pen(NEGATIVE_INK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pen_sizeNegative_throwIllArgException(){
        Pen illegalPen = new Pen( PenTest.PEN_DEFAULT_INK, NEGATIVE_SIZE );
    }

    @Test(expected = IllegalArgumentException.class)
    public void pen_nothingColor_throwIllArgException(){
        Pen illegalPen = new Pen( PenTest.PEN_DEFAULT_INK, PenTest.PEN_DEFAULT_SIZE_LETTER, NOTHING_COLOR );
    }

    @Test(expected = IllegalArgumentException.class)
    public void pen_zeroLetterSize_throwIllArgException(){
        Pen illegalPen = new Pen( PenTest.PEN_DEFAULT_INK, ZERO_SIZE, PenTest.PEN_DEFAULT_COLOR );
    }




}