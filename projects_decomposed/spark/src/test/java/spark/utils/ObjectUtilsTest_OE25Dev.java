package spark.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectUtilsTest_OE25Dev {

    @Test
    public void testIsEmpty_whenArrayIsEmpty() throws Exception {

        assertTrue("Should return false because array is empty", ObjectUtils.isEmpty(new Object[]{}));

    }

    @Test
    public void testIsEmpty_whenArrayIsNotEmpty() throws Exception {

        assertFalse("Should return false because array is not empty", ObjectUtils.isEmpty(new Integer[]{1,2}));

    }

    @Test
    public void testIsEmpty_whenArrayIsEmpty_1_oe() throws Exception {

        assertTrue("Should return false because array is empty", ObjectUtils.isEmpty(new Object[]{}));
    }

    @Test
    public void testIsEmpty_whenArrayIsNotEmpty_1_oe() throws Exception {

        assertFalse("Should return false because array is not empty", ObjectUtils.isEmpty(new Integer[]{1,2}));
    }

}