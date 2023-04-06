package calculator;

/**
 * Polar expression of a complex number
 */
public class MyPolar {

    private final int r;

    private final int theta;

    /**
     * @return the radius
     */
    public int getR() {
        return r;
    }

    /**
     * @return the angle
     */
    public int getTheta() {
        return theta;
    }

    /**
     * polar expression of a complex number
     * @param r radius
     * @param theta angle
     */
    public MyPolar(int r, int theta) {
        this.r = r;
        this.theta = theta;
    }


}
