package com.jihaoxian.study.chapter4junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import java.util.Vector;

/**
 *
 * https://www.cnblogs.com/growing/archive/2010/10/14/1851273.html
 *
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2020/02/03 9:38 PM
 */
public class VectorTest extends TestCase {
    protected Vector fEmpty;
    protected Vector fFull;

    /**
     * 1. junit.textui.TestRunner.run(Test test)
     * 2. Test test = new TestSuite(VectorTest.class)
     *
     * @param args
     */

    public static void main(String[] args) {
        TestResult testResult =  junit.textui.TestRunner.run(suite());

        System.out.println(testResult.errorCount());
    }

    protected void setUp() {
        fEmpty = new Vector();
        fFull = new Vector();
        fFull.addElement(new Integer(1));
        fFull.addElement(new Integer(2));
        fFull.addElement(new Integer(3));
    }

    public static Test suite() {
        return new TestSuite(VectorTest.class);
    }




    public void testCapacity() {
        int size = fFull.size();
        for (int i = 0; i < 100; i++) { fFull.addElement(new Integer(i)); }
        assertTrue(fFull.size() == 100 + size);
    }

    public void testClone() {
        Vector clone = (Vector)fFull.clone();
        assertTrue(clone.size() == fFull.size());
        assertTrue(clone.contains(new Integer(1)));
    }

    public void testContains() {
        assertTrue(fFull.contains(new Integer(1)));
        assertTrue(!fEmpty.contains(new Integer(1)));
    }

    public void testElementAt() {
        Integer i = (Integer)fFull.elementAt(0);
        assertTrue(i.intValue() == 1);
        try {
            fFull.elementAt(fFull.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        fail("Should raise an ArrayIndexOutOfBoundsException");
    }

    public void testRemoveAll() {
        fFull.removeAllElements();
        fEmpty.removeAllElements();
        assertTrue(fFull.isEmpty());
        assertTrue(fEmpty.isEmpty());
    }

    public void testRemoveElement() {
        fFull.removeElement(new Integer(3));
        assertTrue(!fFull.contains(new Integer(3)));
    }
}
