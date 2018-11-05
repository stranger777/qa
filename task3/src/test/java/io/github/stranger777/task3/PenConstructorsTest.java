package io.github.stranger777.task3;

import  org.apache.commons.lang.reflect.*;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PenConstructorsTest {
    static final String FIELD_INK = "inkContainerValue";
    static final  String FIELD_SIZE_LETTER = "sizeLetter";
    static final  String FIELD_COLOR = "color";

    @Test
    public void ConstructPenFromInk() throws IllegalAccessException {
       Pen pen = new Pen( PenTest.PEN_DEFAULT_INK );
       String expected = String.valueOf( PenTest.PEN_DEFAULT_INK );
        String actual = FieldUtils.readDeclaredField(pen, FIELD_INK, true).toString();
        assertEquals(  expected, actual );
    }

    @Test
    public void ConstructPenFromInkAndSizeLetter() throws IllegalAccessException {
        Pen pen = new Pen( PenTest.PEN_DEFAULT_INK, PenTest.PEN_DEFAULT_SIZE_LETTER );
        String ink = FieldUtils.readDeclaredField(pen, FIELD_INK, true).toString();
        String sizeLetter = FieldUtils.readDeclaredField(pen, FIELD_SIZE_LETTER, true).toString();

        ArrayList<String> actual = new ArrayList<String>();
        actual.add(ink);
        actual.add(sizeLetter);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add(String.valueOf(PenTest.PEN_DEFAULT_INK));
        expected.add(String.valueOf(PenTest.PEN_DEFAULT_SIZE_LETTER));

        assertEquals(  expected, actual );
    }

    @Test
    public void ConstructPenFromInkAndSizeLetterAndColor() throws IllegalAccessException {
        Pen pen = new Pen(
                PenTest.PEN_DEFAULT_INK,
                PenTest.PEN_DEFAULT_SIZE_LETTER,
                PenTest.PEN_DEFAULT_COLOR
        );
        String ink = FieldUtils.readDeclaredField(pen, FIELD_INK, true).toString();
        String sizeLetter = FieldUtils.readDeclaredField(pen, FIELD_SIZE_LETTER, true).toString();
        String color = FieldUtils.readDeclaredField(pen, FIELD_COLOR, true).toString();


        ArrayList<String> actual = new ArrayList<String>();
        actual.add(ink);
        actual.add(sizeLetter);
        actual.add(color);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add(String.valueOf(PenTest.PEN_DEFAULT_INK));
        expected.add(String.valueOf(PenTest.PEN_DEFAULT_SIZE_LETTER));
        expected.add(color);

        assertEquals(  expected, actual );
    }


}


