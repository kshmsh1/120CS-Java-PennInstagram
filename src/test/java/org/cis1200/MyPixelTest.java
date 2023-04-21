package org.cis1200;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Use this file to test implementation of Pixel.
 */

public class MyPixelTest {

    @Test
    public void testConstructInBounds() {
        Pixel p = new Pixel(40, 50, 60);
        assertEquals(40, p.getRed());
        assertEquals(50, p.getGreen());
        assertEquals(60, p.getBlue());
    }

    @Test
    public void testConstructArrayLongerThan3() {
        int[] arr = { 10, 20, 30, 40 };
        Pixel p = new Pixel(arr);
        assertEquals(10, p.getRed());
        assertEquals(20, p.getGreen());
        assertEquals(30, p.getBlue());
    }

    @Test
    public void testConstructArrayShorterThan3() {
        int[] arr = { 10, 20 };
        Pixel p = new Pixel(arr);
        assertEquals(10, p.getRed());
        assertEquals(20, p.getGreen());
        assertEquals(0, p.getBlue());
    }

    @Test
    public void testConstructArrayNull() {
        int[] arr = new int[3];
        Pixel p = new Pixel(arr);
        assertEquals(0, p.getRed());
        assertEquals(0, p.getGreen());
        assertEquals(0, p.getBlue());
    }

    @Test
    public void testConstructGreaterThan255() {
        Pixel p = new Pixel(700, 60, 255);
        assertEquals(255, p.getRed());
        assertEquals(60, p.getGreen());
        assertEquals(255, p.getBlue());
    }

    @Test
    public void testConstructLessThan0() {
        Pixel p = new Pixel(-700, 60, 0);
        assertEquals(0, p.getRed());
        assertEquals(60, p.getGreen());
        assertEquals(0, p.getBlue());
    }

    @Test
    public void testSameDistance() {
        Pixel p1 = new Pixel(10, 20, 30);
        Pixel p2 = new Pixel(10, 20, 30);
        assertEquals(0, p1.distance(p2));
    }

    @Test
    public void testDifferentDistance() {
        Pixel p1 = new Pixel(10, 20, 30);
        Pixel p2 = new Pixel(40, 50, 60);
        assertEquals(90, p1.distance(p2));
    }

    @Test
    public void testSamePixel() {
        Pixel p1 = new Pixel(10, 20, 30);
        Pixel p2 = new Pixel(10, 20, 30);
        assertArrayEquals(p1.getComponents(), p2.getComponents());
    }

    @Test
    public void testDifferentPixel() {
        Pixel p1 = new Pixel(10, 20, 30);
        Pixel p2 = new Pixel(40, 50, 60);
        assertFalse(p1.equals(p2));
    }

    @Test
    public void testDistancePixelAndNull() {
        Pixel p = new Pixel(10, 20, 30);
        assertEquals(-1, p.distance(null));
    }

    @Test
    public void testToString() {
        int[] arr = { 10, 20, 30 };
        Pixel p = new Pixel(arr);
        assertEquals("(10, 20, 30)", p.toString());
    }
}