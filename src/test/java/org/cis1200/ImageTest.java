package org.cis1200;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This file tests the various image manipulations on the default
 * image (a view of Positano, Italy).
 */

public class ImageTest {

    static final String LOCATION = "images/";
    static final PixelPicture ITALY = new PixelPicture(LOCATION + "Italy.png");

    @Test
    public void testRotateCW() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyCW.png"),
                        SimpleManipulations.rotateCW(ITALY)
                ),
                "Rotate CW"
        );
    }

    @Test
    public void testRotateCCW() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyCCW.png"),
                        SimpleManipulations.rotateCCW(ITALY)
                ),
                "Rotate CCW"
        );
    }

    @Test
    public void testBorder() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyBorder.png"),
                        SimpleManipulations.border(ITALY, 10, Pixel.BLACK)
                ),
                "Border"
        );
    }

    @Test
    public void testColorInvert() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyColorInvert.png"),
                        SimpleManipulations.invertColors(ITALY)
                ),
                "ColorInversion"
        );
    }

    @Test
    public void testGrayScaleAverage() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyGrayScaleAverage.png"),
                        SimpleManipulations.grayScaleAverage(ITALY)
                ),
                "Gray Scale Average"
        );
    }

    @Test
    public void testColorScale() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyRedTint.png"),
                        SimpleManipulations.scaleColors(ITALY, 1.0, 0.5, 0.5)
                ),
                "Color Scale"
        );
    }

    @Test
    public void testAlphaBlend() {
        PixelPicture p = new PixelPicture(LOCATION + "ItalyGrayScaleAverage.png");
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyBlendGrayScaleAvg.png"),
                        SimpleManipulations.alphaBlend(0.3, ITALY, p)
                ),
                "alpha-Blend"
        );
    }

    @Test
    public void testContrast() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyContrast2.png"),
                        AdvancedManipulations.adjustContrast(ITALY, 2.0)
                ),
                "Contrast"
        );
    }

    @Test
    public void testReducePalette() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyRP512.png"),
                        AdvancedManipulations.reducePalette(ITALY, 512)
                ),
                "Reduce Palette 512"
        );
    }

    @Test
    public void testVignette() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyVignette.png"),
                        SimpleManipulations.vignette(ITALY)
                ),
                "Vignette"
        );
    }

    @Test
    public void testBlur() {
        assertEquals(
                0,
                PixelPicture.diff(
                        new PixelPicture(LOCATION + "ItalyBlur2.png"),
                        AdvancedManipulations.blur(ITALY, 2)
                ),
                "blur 2"
        );
    }

}