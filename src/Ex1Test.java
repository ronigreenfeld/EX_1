import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * * Introduction to Computer Science 2026, Ariel University,
 * * Ex1: arrays, static functions and JUnit
 * <p>
 * This JUnit class represents a JUnit (unit testing) for Ex1-
 * It contains few testing functions for the polynomial functions as define in Ex1.
 * Note: you should add additional JUnit testing functions to this class.
 *
 * @author boaz.ben-moshe
 */

class Ex1Test {
    static final double[] P1 = {2, 0, 3, -1, 0}, P2 = {0.1, 0, 1, 0.1, 3};
    static double[] po1 = {2, 2}, po2 = {-3, 0.61, 0.2};
    ;
    static double[] po3 = {2, 1, -0.7, -0.02, 0.02};
    static double[] po4 = {-3, 0.61, 0.2};

    @Test
    /**
     * Tests that f(x) == poly(x).
     */
    void testF() {
        double fx0 = Ex1.f(po1, 0);
        double fx1 = Ex1.f(po1, 1);
        double fx2 = Ex1.f(po1, 2);
        assertEquals(fx0, 2, Ex1.EPS);
        assertEquals(fx1, 4, Ex1.EPS);
        assertEquals(fx2, 6, Ex1.EPS);
    }

    @Test
    /**
     * Tests that p1(x) + p2(x) == (p1+p2)(x)
     */
    void testF2() {
        double x = Math.PI;
        double[] po12 = Ex1.add(po1, po2);
        double f1x = Ex1.f(po1, x);
        double f2x = Ex1.f(po2, x);
        double f12x = Ex1.f(po12, x);
        assertEquals(f1x + f2x, f12x, Ex1.EPS);
    }

    @Test
    /**
     * Tests that p1+p2+ (-1*p2) == p1
     */
    void testAdd() {
        double[] p12 = Ex1.add(po1, po2);
        double[] minus1 = {-1};
        double[] pp2 = Ex1.mul(po2, minus1);
        double[] p1 = Ex1.add(p12, pp2);
        assertTrue(Ex1.equals(p1, po1));
    }

    @Test
    /**
     * Tests that p1+p2 == p2+p1
     */
    void testAdd2() {
        double[] p12 = Ex1.add(po1, po2);
        double[] p21 = Ex1.add(po2, po1);
        assertTrue(Ex1.equals(p12, p21));
    }

    @Test
    /**
     * Tests that p1+0 == p1
     */
    void testAdd3() {
        double[] p1 = Ex1.add(po1, Ex1.ZERO);
        assertTrue(Ex1.equals(p1, po1));
    }

    @Test
    /**
     * Tests that p1*0 == 0
     */
    void testMul1() {
        double[] p1 = Ex1.mul(po1, Ex1.ZERO);
        assertTrue(Ex1.equals(p1, Ex1.ZERO));
    }

    @Test
    /**
     * Tests that p1*p2 == p2*p1
     */
    void testMul2() {
        double[] p12 = Ex1.mul(po1, po2);
        double[] p21 = Ex1.mul(po2, po1);
        assertTrue(Ex1.equals(p12, p21));
    }

    @Test
    /**
     * Tests that p1(x) * p2(x) = (p1*p2)(x),
     */
    void testMulDoubleArrayDoubleArray() {
        double[] xx = {0, 1, 2, 3, 4.1, -15.2222};
        double[] p12 = Ex1.mul(po1, po2);
        for (int i = 0; i < xx.length; i = i + 1) {
            double x = xx[i];
            double f1x = Ex1.f(po1, x);
            double f2x = Ex1.f(po2, x);
            double f12x = Ex1.f(p12, x);
            assertEquals(f12x, f1x * f2x, Ex1.EPS);
        }
    }

    @Test
    /**
     * Tests a simple derivative examples - till ZERO.
     */
    void testDerivativeArrayDoubleArray() {
        double[] p = {1, 2, 3}; // 3X^2+2x+1
        double[] pt = {2, 6}; // 6x+2
        double[] dp1 = Ex1.derivative(p); // 2x + 6
        double[] dp2 = Ex1.derivative(dp1); // 2
        double[] dp3 = Ex1.derivative(dp2); // 0
        double[] dp4 = Ex1.derivative(dp3); // 0
        assertTrue(Ex1.equals(dp1, pt));
        assertTrue(Ex1.equals(Ex1.ZERO, dp3));
        assertTrue(Ex1.equals(dp4, dp3));
    }

    @Test
    /**
     * Tests the parsing of a polynom in a String like form.
     */
    public void testFromString() {
        double[] p = {-1.1, 2.3, 3.1}; // 3.1X^2+ 2.3x -1.1
        String sp2 = "3.1x^2 +2.3x -1.1";
        String sp = Ex1.poly(p);
        double[] p1 = Ex1.getPolynomFromString(sp);
        double[] p2 = Ex1.getPolynomFromString(sp2);
        boolean isSame1 = Ex1.equals(p1, p);
        boolean isSame2 = Ex1.equals(p2, p);
        if (!isSame1) {
            fail();
        }
        if (!isSame2) {
            fail();
        }
        assertEquals(sp, Ex1.poly(p1));
    }

    @Test
    /**
     * Tests the equality of pairs of arrays.
     */
    public void testEquals() {
        double[][] d1 = {{0}, {1}, {1, 2, 0, 0}};
        double[][] d2 = {Ex1.ZERO, {1 + Ex1.EPS / 2}, {1, 2}};
        double[][] xx = {{-2 * Ex1.EPS}, {1 + Ex1.EPS * 1.2}, {1, 2, Ex1.EPS / 2}};
        for (int i = 0; i < d1.length; i = i + 1) {
            assertTrue(Ex1.equals(d1[i], d2[i]));
        }
        for (int i = 0; i < d1.length; i = i + 1) {
            assertFalse(Ex1.equals(d1[i], xx[i]));
        }
    }

    @Test
    /**
     * Tests is the sameValue function is symmetric.
     */
    public void testSameValue2() {
        double x1 = -4, x2 = 0;
        double rs1 = Ex1.sameValue(po1, po2, x1, x2, Ex1.EPS);
        double rs2 = Ex1.sameValue(po2, po1, x1, x2, Ex1.EPS);
        assertEquals(rs1, rs2, Ex1.EPS);
    }

    @Test
    /**
     * Test the area function - it should be symmetric.
     */
    public void testArea() {
        double x1 = -4, x2 = 0;
        double a1 = Ex1.area(po1, po2, x1, x2, 100);
        double a2 = Ex1.area(po2, po1, x1, x2, 100);
        assertEquals(a1, a2, Ex1.EPS);
    }

    @Test
    /**
     * Test the area f1(x)=0, f2(x)=x;
     */
    public void testArea2() {
        double[] po_a = Ex1.ZERO;
        double[] po_b = {0, 1};
        double x1 = -1;
        double x2 = 2;
        double a1 = Ex1.area(po_a, po_b, x1, x2, 1);
        double a2 = Ex1.area(po_a, po_b, x1, x2, 2);
        double a3 = Ex1.area(po_a, po_b, x1, x2, 3);
        double a100 = Ex1.area(po_a, po_b, x1, x2, 100);
        double area = 2.5;
        assertEquals(a1, area, Ex1.EPS);
        assertEquals(a2, area, Ex1.EPS);
        assertEquals(a3, area, Ex1.EPS);
        assertEquals(a100, area, Ex1.EPS);
    }

    @Test
    /**
     * Test the area function.
     */
    public void testArea3() {
        double[] po_a = {2, 1, -0.7, -0.02, 0.02};
        double[] po_b = {6, 0.1, -0.2};
        double x1 = Ex1.sameValue(po_a, po_b, -10, -5, Ex1.EPS);
        double a1 = Ex1.area(po_a, po_b, x1, 6, 8);
        double area = 58.5658;
        assertEquals(a1, area, Ex1.EPS);
    }

    @Test
    /**
     * Test the derivative function.
     */
    public void testDerivative() {
        double[] p1 = {8, 2, 6, 4};
        double[] der1 = {2, 12, 12};
        double[] der = Ex1.derivative(p1);
        if (der[0] != der1[0]) {
            fail();
        }
        if (der[1] != der1[1]) {
            fail();
        }
        if (der[2] != der1[2]) {
            fail();
        }
    }

    @Test
    /**
     * Test the poly function.
     */
    public void testPoly1() {
        double[] p1 = {4.6};
        double[] p2 = {2, 3.0};
        double[] p3 = {1.0, 1, 5, 2.1};
        String s1 = Ex1.poly(p1);
        String s2 = Ex1.poly(p2);
        String s3 = Ex1.poly(p3);
        assertTrue(s1.equals("+4.6"));
        assertTrue(s2.equals("+3.0X +2.0"));
        assertTrue(s3.equals("+2.1X^3 +5.0X^2 +1.0X +1.0"));
    }

    @Test
    public void testPoly2() {
        double[] p1 = {0};
        double[] p2 = {0, 4.5};
        double[] p3 = {0, 3.0, 0, 5.1};
        String s1 = Ex1.poly(p1);
        String s2 = Ex1.poly(p2);
        String s3 = Ex1.poly(p3);
        assertTrue(s1.equals("0"));
        assertTrue(s2.equals("+4.5X"));
        assertTrue(s3.equals("+5.1X^3 +3.0X"));
    }

    @Test
    public void testPoly3() {
        double[] p1 = {0, -24.1, -3, 6.1};
        String s1 = Ex1.poly(p1);
        assertTrue(s1.equals("+6.1X^3 -3.0X^2 -24.1X"));
    }

    @Test
    public void testAddSing() {
        double num1 = 35;
        double num2 = -2.3;
        double num3 = 0.0;
        assertEquals("+35.0", Ex1.addSign(num1));
        assertEquals("-2.3", Ex1.addSign(num2));
        assertEquals("+0.0", Ex1.addSign(num3));
    }

    @Test
    /**
     * Test the copyArray function.
     * A test that checks whether array1, after copying, has the same size as array2 (the array with the required size).
     */
    public void testCopyArray() {
        double[] p1 = po1;
        double[] p2 = po2;
        double[] p3 = Ex1.copyArray(p2, p1.length);
        assertTrue(p3.length == p2.length);
    }

    @Test
    /**
     * Test the copyArray function.
     * A test on the zero polynomial
     */
    public void testCopyArray2() {
        double[] p1 = Ex1.ZERO;
        int length = 4;
        double[] ans = {0.0, 0.0, 0.0};
        assertTrue(Ex1.copyArray(p1, length)[2] == ans[2]);
    }

    @Test
    /**
     * Test the copyArray function.
     * Checking the case where the original array is larger than the required length — the function will return the original array without any changes.
     */
    public void testCopyArray3() {
        double[] p1 = {2, 4, 7, 1.2};
        int length = 3;
        assertEquals(p1, Ex1.copyArray(p1, length));
    }


    @Test
    public void testMaxLength() {
        double[] p1 = po1;
        double[] p2 = po2;
        assertTrue(Ex1.maxLength(p1, p2).length == p2.length);
    }

    @Test
    public void testMinLength() {
        double[] p1 = po1;
        double[] p2 = po2;
        assertTrue(Ex1.minLength(p1, p2).length == p1.length);
    }

    @Test
    public void testSegmentLength() {
        double x1 = 0;
        double x2 = 4;
        double y1 = 0;
        double y2 = 3;
        assertEquals(5.0, Ex1.segmentLength(x1, x2, y1, y2));
    }

    @Test
    public void testSegmentLength1() {
        double x1 = 3;
        double x2 = -1;
        double y1 = 2;
        double y2 = -1;
        assertEquals(5.0, Ex1.segmentLength(x1, x2, y1, y2));
    }
    @Test
    public void testPointsOnPolynom (){


    }

    @Test
    public void testLength(){
        double[] p1 = {2};
        double x1 = 0;
        double x2 = 8;
        int n1 = 3;
        int n2 = 0;
        int n3 = -4;
        assertEquals(8.0,Ex1.length(p1,x1,x2,n1));
        assertEquals(-1,Ex1.length(p1,x1,x2,n2));
        assertEquals(-1,Ex1.length(p1,x1,x2,n3));
    }


    @Test
    public void testLength2(){
        double[] p1 = {2,2};
        double x1 = -3;
        double x2 = 5;
        int n = 2;
        assertEquals(8*Math.sqrt(5),Ex1.length(p1,x1,x2,n));
    }

    @Test
    public void testLength3(){
        double[] p1 = {0,0,0};
        double x1 = -3;
        double x2 = 5;
        int n = 2;
        assertEquals(0,Ex1.length(p1,x1,x2,n));
    }


    @Test
    public void testTrimArray(){
        double[] p1 = {7.5,2.1,3.0};
        assertTrue(Ex1.trimArray(p1).length == p1.length);
        assertTrue(Ex1.equals(Ex1.trimArray(p1), p1));
    }
    @Test
    public void testTrimArray2(){
        double[] p1 = {7.5,2.1,3.0,0.0,0.0};
        double [] expected = {7.5,2.1,3.0};
        assertTrue(Ex1.equals(Ex1.trimArray(p1), expected));
    }
    @Test
    public void testTrimArray3(){
        double[] p1 = {7.5,2.1,0.0,3.0,0.0};
        double [] expected = {7.5,2.1,0.0,3.0};
        assertTrue(Ex1.equals(Ex1.trimArray(p1), expected));
    }
    @Test
    public void testTrimArray4(){
        double[] p1 = {0.0,0.0,0.0,0.0,0.0};
        double [] expected = Ex1.ZERO;
        assertTrue(Ex1.equals(Ex1.trimArray(p1), expected));
    }
    @Test
    public void testTrimArray5(){
        double[] p1 = {0.0};
        double [] p2 = {10.0};
        assertTrue(Ex1.equals(Ex1.trimArray(p1), Ex1.ZERO));
        assertTrue(Ex1.equals(Ex1.trimArray(p2), p2));
    }




    @Test
    public void testisIntersectionRoot(){
    }
}