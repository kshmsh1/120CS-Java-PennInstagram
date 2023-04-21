package org.cis1200;

public class SimpleManipulations {

    /**
     * Rotate a picture 90 degrees clockwise.
     * For example, consider this bitmap, where each pixel is labeled by its
     * coordinates:
     * (0, 0) (0, 1) (0, 2)
     * (1, 0) (1, 1) (1, 2)
     *
     * Rotating this bitmap CW will produce the following bitmap, with relabeled
     * coordinates:
     * (1, 0) (0, 0)
     * (1, 1) (0, 1)
     * (1, 2) (0, 2)
     *
     * This method implements this "relabeling," copying pixels from their
     * old coordinates to their new coordinates.
     * 
     * @param pic The original picture to rotate.
     * @return The rotated picture.
     */
    public static PixelPicture rotateCW(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] src = pic.getBitmap();
        Pixel[][] tgt = new Pixel[w][h]; // swap coordinates

        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                tgt[col][h - row - 1] = src[row][col]; // swap coordinates
            }
        }

        return new PixelPicture(tgt);
    }

    /**
     * Rotate a picture 90 degrees counter-clockwise.
     * For example, consider this bitmap, where each pixel is labeled by its
     * coordinates:
     * (0, 0) (0, 1) (0, 2)
     * (1, 0) (1, 1) (1, 2)
     *
     * Rotating this bitmap CCW will produce the following bitmap, with
     * relabeled coordinates:
     * (0, 2) (1, 2)
     * (0, 1) (1, 1)
     * (0, 0) (1, 0)
     * w = 3, h = 2
     *
     * Implement this "relabeling," copying pixels from their old coordinates
     * to their new coordinates.
     * 
     * @param pic The original picture to rotate.
     * @return The rotated picture.
     */
    public static PixelPicture rotateCCW(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] src = pic.getBitmap();
        Pixel[][] tgt = new Pixel[w][h];

        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                tgt[w - col - 1][row] = src[row][col];
            }
        }

        return new PixelPicture(tgt);
    }

    /**
     * Create a new image by adding a border to a specified image.
     * 
     * @param pic         the original picture
     * @param borderWidth number of pixels in the border
     * @param borderColor color of the border.
     * @return a copy of the input picture with a border
     */
    public static PixelPicture border(PixelPicture pic, int borderWidth, Pixel borderColor) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] src = pic.getBitmap();
        Pixel[][] tgt = new Pixel[h + borderWidth * 2][w + borderWidth * 2];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                tgt[i + borderWidth][j + borderWidth] = src[i][j];
            }
        }

        // top
        for (int i = 0; i < borderWidth; i++) {
            for (int j = 0; j < (w + borderWidth * 2); j++) {
                tgt[i][j] = borderColor;
            }
        }

        // bottom
        for (int i = (h + borderWidth); i < (h + borderWidth * 2); i++) {
            for (int j = 0; j < (w + borderWidth * 2); j++) {
                tgt[i][j] = borderColor;
            }
        }

        // left
        for (int i = 0; i < (h + borderWidth * 2); i++) {
            for (int j = 0; j < borderWidth; j++) {
                tgt[i][j] = borderColor;
            }
        }

        // right
        for (int i = 0; i < (h + borderWidth * 2); i++) {
            for (int j = (w + borderWidth); j < (w + borderWidth * 2); j++) {
                tgt[i][j] = borderColor;
            }
        }

        return new PixelPicture(tgt);
    }

    /**
     * Transforms a picture to its GrayScale equivalent using the luminosity
     * algorithm.
     * Luminosity is a weighted average that adjusts the GrayScale value based on
     * human perception. We are more sensitive to green, so it is weighted more
     * heavily.
     * Different image manipulation programs use different values. We will
     * use the one Photoshop uses: round(0.299*R + 0.587*G + 0.114*B).
     *
     * Use Math.round at the very end of your calculation before casting to an int.
     * After computing the weighted average, create a new pixel with this average
     * as its component values. For example, the reddish color (180, 100, 80)
     * becomes (122, 122, 122).
     * 
     * @param pic the original picture
     * @return a new picture that is the GrayScale equivalent of the original
     *         picture
     */
    public static PixelPicture grayScaleLuminosity(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Pixel p = bmp[j][i];
                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();
                int avg = (int) Math.round(0.299 * r + 0.587 * g + 0.114 * b);
                bmp[j][i] = new Pixel(avg, avg, avg);
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Creates a new image by inverting the color of each pixel.
     * To do this, simply create a new PixelPicture where each color component
     * of each pixel is the inverse of the original value. To invert a color
     * component, subtract it from 255.
     * 
     * @param pic the picture to be inverted
     * @return new picture with inverted colors
     */
    public static PixelPicture invertColors(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Pixel p = bmp[i][j];

                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();

                bmp[i][j] = new Pixel(255 - r, 255 - g, 255 - b);
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Transforms a colored picture to its grayscale equivalent using an averaging
     * algorithm.
     * To do this, find the average of the color components of each pixel in
     * question, and create a new pixel with this average as its component values.
     * For example, the reddish color (180, 100, 80) becomes (120, 120, 120).
     *
     * That is, the formula is (R + G + B) / 3.0
     * Note: / in the formula above is a double operation.
     *
     * Use Math.round at the very end of your calculation before casting to an int.
     * 
     * @param pic the original picture
     * @return new grayscale image
     */

    public static PixelPicture grayScaleAverage(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Pixel p = bmp[i][j];

                int average = (int) (Math.round((p.getRed() + p.getGreen() + p.getBlue()) / 3.0));

                bmp[i][j] = new Pixel(average, average, average);
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Scales the color components of a picture.
     * To do this, simply replace each pixel with a new one where each color
     * component is the original value multiplied by the scaling factor for that
     * color. Note that each component of the resulting pixel must have values
     * in the range {@code 0 <= color <= 255}.
     * Use Math.round before converting double values to ints.
     * 
     * @param pic     original image
     * @param rfactor red factor
     * @param gfactor green factor
     * @param bfactor blue factor
     * @return new image with scaled colors
     */
    public static PixelPicture scaleColors(
            PixelPicture pic, double rfactor, double gfactor, double bfactor
    ) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Pixel p = bmp[i][j];

                int r = (int) (Math.round(p.getRed() * rfactor));
                int g = (int) (Math.round(p.getGreen() * gfactor));
                int b = (int) (Math.round(p.getBlue() * bfactor));

                bmp[i][j] = new Pixel(r, g, b);
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Computes the weighted average of two integers.
     * If alpha is 0.5, the weightedAverage is the same as the average
     * of the two numbers. If alpha is 1.0, then x is
     * returned. Likewise, if it is 0.0, than the result is y.
     * 
     * @param alpha weight
     * @param x     first integer
     * @param y     second integer
     * @return weighted average of x and y
     */
    public static int weightedAverage(double alpha, int x, int y) {
        return (int) Math.round(x * alpha + y * (1 - alpha));
    }

    /**
     * Blends two pictures together by taking a weighted average of each pixel.
     *
     * The weighted average of two int values is given by the static method
     * above. This transformation applies this formula to each color in the
     * corresponding pixels of the two images.
     *
     * The two images must be exactly the same size for this transformation to
     * apply. If the dimensions of the second image do not match those of the
     * first, then the first image is returned unchanged.
     * 
     * @param alpha weight
     * @param pic   first picture
     * @param f     second picture
     * @return the blended image
     */
    public static PixelPicture alphaBlend(double alpha, PixelPicture pic, PixelPicture f) {
        int picW = pic.getWidth();
        int picH = pic.getHeight();
        int fW = f.getWidth();
        int fH = f.getHeight();

        if ((picW == fW) && (picH == fH)) {
            Pixel[][] picBMP = pic.getBitmap();
            Pixel[][] fBMP = f.getBitmap();

            for (int i = 0; i < picH; i++) {
                for (int j = 0; j < picW; j++) {
                    Pixel picP = picBMP[i][j];
                    Pixel fP = fBMP[i][j];

                    int r = weightedAverage(alpha, picP.getRed(), fP.getRed());
                    int g = weightedAverage(alpha, picP.getGreen(), fP.getGreen());
                    int b = weightedAverage(alpha, picP.getBlue(), fP.getBlue());

                    picBMP[i][j] = new Pixel(r, g, b);
                }
            }
            return new PixelPicture(picBMP);
        } else {
            return pic;
        }
    }

    /**
     * Adds dark edges to an image to draw interest to the center.
     * We'll do this by calculating the distance from each pixel to the
     * center of the image, and then multiplying the color values of that
     * pixel by one minus the square of that distance. In other words,
     *
     * 1. Find the col,row coordinates of the center pixels of the image,
     * cx and cy. For example, if the image has width 5 and height 3,
     * then the center pixel is located at (2,1). We'll use doubles
     * to represent this value, in order to account for cases where
     * the picture has an even number of pixels. For example, if the
     * width is 4 and the height 6, then the center is at (1.5, 2.5).
     *
     * 2. For each pixel in the image, find its (proportional) distance
     * d from the center. This is the distance from the center in the
     * col and row directions, divided by the total distance from the center
     * to any corner.
     *
     * d = Math.sqrt (dx * dx + dy * dy) / Math.sqrt (cx * cx + cy * cy)
     *
     * 3. Compute the multiplicative factor f from the distance. This factor
     * should be 1.0 at the center of the picture (i.e. d=0) and then
     * decrease to 0.0 near the edges.
     *
     * f = 1.0 - (d * d)
     *
     * 4. Multiply each component of the pixel by the factor f. Use Math.round
     * to convert the result to an integer value before casting to an int.
     *
     * 5. There is one special case. If the distance from the center to any
     * corner is zero (i.e. if the picture contains a single pixel) this method
     * should just return the original input.
     *
     * @param pic original image
     * @return new image with with dark edges
     */
    public static PixelPicture vignette(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();
        double cx = (w - 1) / 2.0;
        double cy = (h - 1) / 2.0; // cx, cy is center pixel in the image

        Pixel[][] bmp = pic.getBitmap();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                double dx = (double) (col - cx);
                double dy = (double) (row - cy);

                double r = Math.sqrt(cx * cx + cy * cy);

                // check for division by zero
                if (r == 0) {
                    return pic;
                }

                double d = Math.sqrt((dx * dx) + (dy * dy)) / r;
                double factor = 1.0 - d * d;

                bmp[row][col] = new Pixel(
                        (int) Math.round(bmp[row][col].getRed() * factor),
                        (int) Math.round(bmp[row][col].getGreen() * factor),
                        (int) Math.round(bmp[row][col].getBlue() * factor)
                );

            }
        }
        return new PixelPicture(bmp);
    }
}