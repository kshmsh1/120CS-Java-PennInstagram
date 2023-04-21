package org.cis1200;

/**
 * A point of color.
 * <p>
 * Pixels are represented as three integral color components (red, green, and
 * blue) in the inclusive range [0, 255]. Lower values mean less color; higher
 * mean more. For example, {@code new Pixel(255,255,255)} represents white,
 * {@code new Pixel(0,0,0)} represents black, and {@code new Pixel(0,255,0)}
 * represents green.
 * <p>
 * This data structure is immutable. Once a {@code Pixel} is created, it cannot
 * be modified.
 */
public class Pixel implements Comparable<Pixel> {

    /** The {@code Pixel} representing the RGB color black. */
    public static final Pixel BLACK = new Pixel(0, 0, 0);

    /** The {@code Pixel} representing the RGB color blue. */
    public static final Pixel BLUE = new Pixel(0, 0, 255);

    /** The {@code Pixel} representing the RGB color red. */
    public static final Pixel RED = new Pixel(255, 0, 0);

    /** The {@code Pixel} representing the RGB color green. */
    public static final Pixel GREEN = new Pixel(0, 255, 0);

    /** The {@code Pixel} representing the RGB color white. */
    public static final Pixel WHITE = new Pixel(255, 255, 255);

    private int[] pixelArray = new int[3];

    /**
     * Create a new pixel with the provided color components.
     * <p>
     * If the provided components are not between 0 and 255,
     * they are clipped. Negative components are set to 0,
     * and components greater than 255 are set to 255.
     * 
     * @param r the red component of the pixel
     * @param g the green component of the pixel
     * @param b the blue component of the pixel
     */

    public Pixel(int r, int g, int b) {
        if (r < 0) {
            r = 0;
        } else if (r > 255) {
            r = 255;
        }

        if (g < 0) {
            g = 0;
        } else if (g > 255) {
            g = 255;
        }

        if (b < 0) {
            b = 0;
        } else if (b > 255) {
            b = 255;
        }

        pixelArray[0] = r;
        pixelArray[1] = g;
        pixelArray[2] = b;
    }

    /**
     * Create a new pixel with the provided color components, specified as an
     * array. The index {@code c[0]} corresponds to {@code Pixel}'s red
     * component; {@code c[1]} its green component, and {@code c[2]} its blue
     * component.
     * <p>
     * If {@code c} is null or has fewer than 3 entries, the missing
     * components are set to 0. If {@code c} has more than 3 entries, the extra
     * entries are ignored. If the provided components are not between 0 and
     * 255, they are clipped. Negative components are set to 0, and
     * components greater than 255 are set to 255.
     * <p>
     * This constructor must not throw any exceptions.
     * 
     * @param c the array of components
     */
    public Pixel(int[] c) {
        int[] a1 = new int[3];

        if (c != null && c.length < 3) {
            for (int i = 0; i < c.length; i++) {
                a1[i] = c[i];
            }
        } else if (c != null) {
            for (int j = 0; j < 3; j++) {
                a1[j] = c[j];
            }
        }

        if (a1[0] < 0) {
            a1[0] = 0;
        } else if (a1[0] > 255) {
            a1[0] = 255;
        }

        if (a1[1] < 0) {
            a1[1] = 0;
        } else if (a1[1] > 255) {
            a1[1] = 255;
        }

        if (a1[2] < 0) {
            a1[2] = 0;
        } else if (a1[2] > 255) {
            a1[2] = 255;
        }

        pixelArray[0] = a1[0];
        pixelArray[1] = a1[1];
        pixelArray[2] = a1[2];
    }

    /**
     * Accessor for the red component of the pixel.
     * 
     * @return the int value of the red component
     */
    public int getRed() {
        return pixelArray[0]; // Implement this method
    }

    /**
     * Accessor for the green component of the pixel.
     * 
     * @return the int value of the green component
     */
    public int getGreen() {
        return pixelArray[1]; // Implement this method
    }

    /**
     * Accessor for the blue component of the pixel.
     * 
     * @return the int value of the blue component
     */
    public int getBlue() {
        return pixelArray[2]; // Implement this method
    }

    /**
     * Accessor for the pixel's components as an array of 3 integers, where
     * index 0 is red, index 1 is green, and index 2 is blue.
     * Note that this method should not break encapsulation.
     * 
     * @return an int array representing the pixel's components
     */
    public int[] getComponents() {
        int[] pixelArray2 = new int[3];
        pixelArray2[0] = pixelArray[0];
        pixelArray2[1] = pixelArray[1];
        pixelArray2[2] = pixelArray[2];
        return pixelArray2;
    }

    /**
     * Determines the level of similarity between this pixel and another by
     * summing the absolute values of the differences between corresponding
     * components of the two pixels. Distance to a null pixel is defined as -1.
     * 
     * @param px the other pixel with which to compare
     * @return the sum of the differences in each of the color components
     */
    public int distance(Pixel px) {
        if (px == null) {
            return -1;
        }
        return (Math.abs(pixelArray[0] - px.pixelArray[0])) +
                (Math.abs(pixelArray[1] - px.pixelArray[1])) +
                (Math.abs(pixelArray[2] - px.pixelArray[2]));
    }

    /**
     * Returns a string representation of this pixel. The string should
     * comma separate the rgb values and surround them with parentheses.
     * <p>
     * For example, {@code RED.toString()} is {@code "(255, 0, 0)"}
     * <p>
     * Note: This function will allow you to print pixels in a readable format.
     * This can be very helpful while debugging, and we highly encourage you to
     * use print statements to aid your debugging throughout this assignment.
     * 
     * @return a string representation of this pixel
     */
    public String toString() {
        return "(" + pixelArray[0] + ", " + pixelArray[1] + ", " + pixelArray[2] + ")";
    }

    /**
     * Compares the RGB values of the current Pixel with another to check if
     * they are the same (and thus whether the two Pixels equal each other)
     * 
     * @param px The pixel being compared with this
     * @return whether the two pixels contain the same components
     */
    public boolean sameRGB(Pixel px) {
        return ((px.getRed() == pixelArray[0]) &&
                (px.getGreen() == pixelArray[1]) &&
                (px.getBlue() == pixelArray[2]));
    }

    /**
     * Checks whether this pixel has the same components as the given Object.
     * If the other object is not a Pixel, then the method returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (getClass() == other.getClass()) {
            return this.sameRGB((Pixel) other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 0;
        int[] components = getComponents();

        for (int k = 0; k < components.length; k++) {
            h += k * 255 + components[k];
        }
        return h;
    }

    @Override
    public int compareTo(Pixel o) {
        int rc = getRed() - o.getRed();
        int gc = getGreen() - o.getGreen();
        int bc = getBlue() - o.getBlue();

        if (rc != 0) {
            return rc;
        } else if (gc != 0) {
            return gc;
        } else {
            return bc;
        }
    }
}