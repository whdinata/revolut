package com.revolut.rate.common.util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringUtilTest {

    @Test
    public void givenPositiveDecimalLessThanOneShouldReturnWithOneZeroBeforeDot() {
        // given
        String objectUnderTest = "0.25";

        // when
        int actual = StringUtil.getFirstDigitIndexBeforeDot(objectUnderTest, 1);
        int actual2 = StringUtil.getFirstDigitIndexBeforeDot(objectUnderTest, 0);

        //then
        int expected = 0;
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, actual2);
    }

    @Test
    public void givenPositiveDecimalMoreThanOneShouldReturnWithoutLeadingZeroes() {
        // given
        String objectUnderTest = "01.25";
        String objectUnderTest2 = "001.25";
        String objectUnderTest3 = "1.25";

        // when
        String actual = StringUtil.getValueWithoutLeadingZeroes(objectUnderTest);
        String actual2 = StringUtil.getValueWithoutLeadingZeroes(objectUnderTest2);
        String actual3 = StringUtil.getValueWithoutLeadingZeroes(objectUnderTest3);

        //then
        String expected = "1.25";
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, actual2);
        Assert.assertEquals(expected, actual3);
    }
}