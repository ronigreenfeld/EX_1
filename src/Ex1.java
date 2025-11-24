/**
 * Introduction to Computer Science 2026, Ariel University,
 * Ex1: arrays, static functions and JUnit
 * https://docs.google.com/document/d/1GcNQht9rsVVSt153Y8pFPqXJVju56CY4/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 * <p>
 * This class represents a set of static methods on a polynomial functions - represented as an array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynomial function: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code below")
 *
 * @author boaz.benmoshe
 */
public class Ex1 {
    /**
     * Epsilon value for numerical computation, it serves as a "close enough" threshold.
     */
    public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
    /**
     * The zero polynomial function is represented as an array with a single (0) entry.
     */
    public static final double[] ZERO = {0};

    /**
     * Computes the f(x) value of the polynomial function at x.
     *
     * @param poly - polynomial function
     * @param x
     * @return f(x) - the polynomial function value at x.
     */
    public static double f(double[] poly, double x) {
        double ans = 0;
        for (int i = 0; i < poly.length; i++) {
            double c = Math.pow(x, i);
            ans += c * poly[i];
        }
        return ans;
    }

    /**
     * Given a polynomial function (p), a range [x1,x2] and an epsilon eps.
     * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
     * assuming p(x1)*p(x2) <= 0.
     * This function should be implemented recursively.
     *
     * @param p   - the polynomial function
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
     */
    public static double root_rec(double[] p, double x1, double x2, double eps) {
        double f1 = f(p, x1);
        double x12 = (x1 + x2) / 2;
        double f12 = f(p, x12);
        if (Math.abs(f12) < eps) {
            return x12;
        }
        if (f12 * f1 <= 0) {
            return root_rec(p, x1, x12, eps);
        } else {
            return root_rec(p, x12, x2, eps);
        }
    }

    /**
     * This function computes a polynomial representation from a set of 2D points on the polynom.
     * The solution is based on: //	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
     * Note: this function only works for a set of points containing up to 3 points, else returns null.
     *
     * @param xx
     * @param yy
     * @return an array of doubles representing the coefficients of the polynom.
     */
    public static double[] PolynomFromPoints(double[] xx, double[] yy) {
        double[] ans = null;
        int lx = xx.length;
        int ly = yy.length;
        if (xx != null && yy != null && lx == ly && lx > 1 && lx < 4) {
            /** add you code below

             /////////////////// */
        }
        return ans;
    }

    /**
     * Two polynomials functions are equal if and only if they have the same values f(x) for n+1 values of x,
     * where n is the max degree (over p1, p2) - up to an epsilon (aka EPS) value.
     *
     * @param p1 first polynomial function
     * @param p2 second polynomial function
     * @return true iff p1 represents the same polynomial function as p2.
     * <p>
     * <p>
     * boolean ans = true
     * if (p1.length != p2.length) {return false}               //If the lengths are different - return false.
     * for (int i = 0; i < p1.length; i = i + 1) {              //A loop that iterates over all the elements of the arrays and compares them.
     * if (Math.abs(p1[i] - p2[i]) >= EPS) {return false}       //If the difference (in absolute value) is greater than EPS - return false.
     * }
     * return ans;                                              //return answer
     *
     *
     */
    public static boolean equals(double[] p1, double[] p2) {
        boolean ans = true;
        if (p1.length != p2.length) {
            return false;
        }
        for (int i = 0; i < p1.length; i = i + 1) {
            if (Math.abs(p1[i] - p2[i]) >= EPS) {
                return false;
            }
        }
        return ans;
    }

    /**
     * Computes a String representing the polynomial function.
     * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
     *
     * @param poly the polynomial function represented as an array of doubles
     * @return String representing the polynomial function:
     * <p>
     * <p>
     * <p>
     * <p>
     * if (poly.length == 0) {ans = "0"}
     * else {
     * if (poly.length == 1) {
     * ans = poly[0]
     * }
     * if (poly.length == 2) {
     * ans = poly[1] + "X +" + poly[0];
     * }
     * if (poly.length >= 3) {
     * ans = poly[1] + "X +" + =poly[0]=;
     * for (int i = 2; i < poly.length; i = i + 1) {
     * ans = poly[i] + "X^" + i + " + " + ans;
     * }
     * }
     * }
     * return ans;
     *
     *
     *
     */
    public static String poly(double[] poly) {
        String ans = "";
        if (poly.length == 0) {
            ans = "0";
        } else {
            if (poly.length == 1) {
                ans = addSign(poly[0]);
            }
            if (poly.length == 2) {
                ans = addSign(poly[1]) + "X " + addSign(poly[0]);
            }
            if (poly.length >= 3) {
                ans = addSign(poly[1]) + "X " + addSign(poly[0]);
                for (int i = 2; i < poly.length; i = i + 1) {
                    ans = addSign(poly[i]) + "X^" + i + " " + ans;
                }
            }
        }
        return ans;
    }

    /**
     * This function receives a real number and returns the number with its sign (negative or positive).
     *
     * @param mekadem
     * @return a number with a positive or negative sign.
     *
     *
     */
    public static String addSign(double mekadem) {
        String sign;
        if (mekadem < 0) {
            sign = "-";
        } else {
            sign = "+";
        }
        return sign + Math.abs(mekadem);
    }

    /**
     * Given two polynomial functions (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
     * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
     *
     * @param p1  - first polynomial function
     * @param p2  - second polynomial function
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p1(x) - p2(x)| < eps.
     */
    public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
        double ans = x1;
        /** add you code below

         /////////////////// */
        return ans;
    }

    /**
     * Given a polynomial function (p), a range [x1,x2] and an integer with the number (n) of sample points.
     * This function computes an approximation of the length of the function between f(x1) and f(x2)
     * using n inner sample points and computing the segment-path between them.
     * assuming x1 < x2.
     * This function should be implemented iteratively (none recursive).
     *
     * @param p                - the polynomial function
     * @param x1               - minimal value of the range
     * @param x2               - maximal value of the range
     * @param numberOfSegments - (A positive integer value (1,2,...).
     * @return the length approximation of the function between f(x1) and f(x2).
     */
    public static double length(double[] p, double x1, double x2, int numberOfSegments) {
        double ans = x1;
        /** add you code below

         /////////////////// */
        return ans;
    }

    /**
     * Given two polynomial functions (p1,p2), a range [x1,x2] and an integer representing the number of Trapezoids between the functions (number of samples in on each polynom).
     * This function computes an approximation of the area between the polynomial functions within the x-range.
     * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
     *
     * @param p1                - first polynomial function
     * @param p2                - second polynomial function
     * @param x1                - minimal value of the range
     * @param x2                - maximal value of the range
     * @param numberOfTrapezoid - a natural number representing the number of Trapezoids between x1 and x2.
     * @return the approximated area between the two polynomial functions within the [x1,x2] range.
     */
    public static double area(double[] p1, double[] p2, double x1, double x2, int numberOfTrapezoid) {
        double ans = 0;
        /** add you code below

         /////////////////// */
        return ans;
    }

    /**
     * This function computes the array representation of a polynomial function from a String
     * representation. Note:given a polynomial function represented as a double array,
     * getPolynomFromString(poly(p)) should return an array equals to p.
     *
     * @param p - a String representing polynomial function.
     * @return
     */
    public static double[] getPolynomFromString(String p) {
        double[] ans = ZERO;//  -1.0x^2 +3.0x +2.0
        /** add you code below

         /////////////////// */
        return ans;
    }

    /**
     * This function computes the polynomial function which is the sum of two polynomial functions (p1,p2)
     *
     * @param p1
     * @param p2
     * @return The function compares the lengths of the arrays P1 and P2, and finds the minimum value between them.
     *
     *
     * It creates an array based on the length of the shorter one, fills it with the values of the shorter array, and adds neutral values in the additional positions.
     * Finally, the function adds the two arrays together.
     *
     *
     *
     * input (p1,p2)
     * if (p1.length < p2.length) {
     * p1 = copyArray(p1, p2.length)                        //copying array p1 to an array with a length of p2. The values of p1 are preserved in the new array.
     * }
     * if (p2.length < p1.length) {
     * p2 = copyArray(p2, p1.length)                        //copying array p2 to an array with a length of p1. The values of p2 are preserved in the new array.
     * }
     * ans = new double[p1.length]                          //update the length array ans
     * for (int i = 0; i < p1.length; i = i + 1) {
     * ans[i] = p1[i] + p2[i];                              //adding P1 and P2
     * }
     * return ans                                           //return p1+p2
     *
     *
     *
     */
    public static double[] add(double[] p1, double[] p2) {
        double[] ans = ZERO;
        if (p1 == null && p2 == null) {
            return null;
        }
        if (p1.length < p2.length) {
            p1 = copyArray(p1, p2.length);
        }
        if (p2.length < p1.length) {
            p2 = copyArray(p2, p1.length);
        }
        ans = new double[p1.length];
        for (int i = 0; i < p1.length; i = i + 1) {
            ans[i] = p1[i] + p2[i];
        }
        return ans;
    }


    /**
     * The function copies a shorter array into a new longer array (adding 0.0 in the positions that were added).
     *
     * @param poly an array that represents a polynomial equation.
     * @param newLength expresses the desired length of the new array.
     * @return a longer array which represents the same polynomial equation.
     *
     *
     * input (poly[], int newLength)
     * double[] ans = new double[newLength]
     * if (poly == null) {return null}
     * else {
     * for (int i = 0; i < poly.length; i = i + 1) {
     * ans[i] = poly[i]
     * }
     * }
     * return ans;
     *
     */

    public static double[] copyArray(double[] poly, int newLength) {
        double[] ans = poly;
        if (poly.length < newLength) {
            ans = new double[newLength];
            if (poly == null) {
                return null;
            }
            for (int i = 0; i < poly.length; i = i + 1) {
                ans[i] = poly[i];
            }
        }
        return ans;
    }

    /**
     * This function computes the polynomial function which is the multiplication of two polynoms (p1,p2)
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double[] mul(double[] p1, double[] p2) {
        double[] ans = ZERO;//
        if (p1 == null && p2 == null) {
            return null;
        }
        if (p1.length < p2.length) {
            double[] temp = p2;
            p2 = p1;
            p1 = temp;
        } //now p1.length > p2.length
        double[] temp1 = new double[p1.length];
        if (p2 != ZERO) {
            for (int n = 0; n < temp1.length; n = n + 1) {
                temp1[n] = 1.0;
            }
        }
        ans = new double[p1.length];
        for (int i = 0; i < p1.length; i = i + 1) {
            while (i < p2.length) {
                temp1[i] = p2[i];
                break;
            }
            ans[i] = p1[i] * temp1[i];
        }
        return ans;
    }

    /**
     * This function computes the derivative of the p0 polynomial function.
     *
     * @param po
     * @return input (p0)
     * <p>
     * <p>
     * <p>
     * double [] ans = {0}
     * if (po[] = null){return ans;}
     * double [] p1 = new double [po.length-1]
     * for (double i=1; i < po.length; i=i+1){
     * p1[i-1] = i*po[i]                               // po{5,2,3} = p1{2,9}
     * ans = p1 []
     * }
     * return ans
     *
     */
    public static double[] derivative(double[] po) {
        double[] ans = ZERO;//
        if (po == null) {
            return null;
        }
        double[] p1 = new double[po.length - 1];
        for (int i = 1; i < po.length; i = i + 1) {
            p1[i - 1] = (double) i * po[i];                             // po{5,2,3} = p1{2,9}
            ans = p1;
        }
        return ans;
    }
}
