package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
	

	// Testing getLength()
    @Test
    public void getLength_set_DifferentLowerBoundUpperBound_then_ReturnLength() {
    	Range range = new Range(-1, 1);
    	assertEquals("The range value between -1 and 1 should be 2", 2, range.getLength(),.000000001d);

    }
    
    @Test
    public void getLength_set_SameLowerBoundUpperBound_then_ReturnLength() {
    	Range range = new Range(1, 1);
    	assertEquals("The range value between 1 and 1 should be 0", 0, range.getLength(),.000000001d);

    }
    
    
    // Testing getCentralValue()
    @Test
    public void getCentralValue_set_DifferentLowerBoundUpperBound_then_ReturnCentralValue() {
    	Range range = new Range(-1, 1);
    	assertEquals("The central value between -1 and 1 should be 0", 0, range.getCentralValue(),.000000001d);
    }
    
    @Test
    public void getCentralValue_set_SameLowerBoundUpperBound_then_ReturnCentralValue() {
    	Range range = new Range(1, 1);
    	assertEquals("The central value between 1 and 1 should be 1", 1, range.getCentralValue(),.000000001d);
    }
    
    // Testing contains()
    @Test
    public void contains_given_ValueOfWithinRange_then_ReturnTrue() {
    	Range range = new Range(-1, 1);
    	assertTrue("The range between -1 and 1 contains zero, so it returns True", range.contains(0));
    }
    
    @Test
    public void contains_given_ValueOfLowerBound_then_ReturnTrue() {
    	Range range = new Range(-1, 1);
    	assertTrue("The range between -1 and 1 contains minus one, so it returns True", range.contains(-1));
    }
    
    @Test
    public void contains_given_ValueOfUpperBound_then_ReturnTrue() {
    	Range range = new Range(-1, 1);
    	assertTrue("The range between -1 and 1 contains one, so it returns True", range.contains(1));
    }
    
    @Test
    public void contains_given_ValueOfAboveUpperBound_then_ReturnFalse() {
    	Range range = new Range(-1, 1);
    	assertFalse("The range between -1 and 1 does not contain two, so it returns False", range.contains(2));
    }
    
    @Test
    public void contains_given_ValueOfBeneathUpperBound_then_ReturnFalse() {
    	Range range = new Range(-1, 1);
    	assertFalse("The range between -1 and 1 does not contain minus two, so it returns False", range.contains(-2));
    }
    
    // Testing getLowerBound()
    @Test
    public void getLowerBound_set_LowerBoundNegativeUpperBoundPositive_then_ReturnLowerBound() {
    	Range range = new Range(-1, 1);
    	assertEquals("The lower bound of range -1 and 1, should be -1", -1, range.getLowerBound(), .000000001d);
    }
    
    // Testing getUpperBound()
    @Test
    public void getUpperBound_set_LowerBoundNegativeUpperBoundPositive_then_ReturnUpperBound() {
    	Range range = new Range(-1, 1);
    	assertEquals("The upper bound of range -1 and 1, should be 1", 1, range.getUpperBound(), .000000001d);
    }
    
    //Assignment3: new test cases
    
    //constructor
    @Test
    public void consructor_set_LowerGraterThanUpper_get_exception() {
    	try {
    	Range range = new Range(10, 1);
    	fail("IllegalArgumentException expected to be thrown");
    	}
    	catch(IllegalArgumentException ex) {
    		assertEquals("The message should be different.", "Range(double, double): require lower (10.0) <= upper (1.0).", ex.getMessage());
    	}
    	catch(Exception ex) {
    		fail("IllegalArgumentException expected to be thrown");
    	}
    }
    
    //intersects(double b0, double b1)
    @Test
    public void intersects_set_insideTheRange_get_true() {
    	Range range = new Range(-10, 10);
    	assertTrue(range.intersects(0,1));
    }
    
    @Test
    public void intersects_set_belowLowerBound_get_false() {
    	Range range = new Range(-10, 10);
    	assertFalse(range.intersects(-20,-15));
    }
    
    @Test
    public void intersects_set_boveUpperBound_get_false() {
    	Range range = new Range(-10, 10);
    	assertFalse(range.intersects(15, 20));
    }
    
    //intersects(Range range)
    @Test
    public void intersectsRange_set_insideTheRange_get_true() {
    	Range range = new Range(-10, 10);
    	assertTrue(range.intersects(new Range(0,1)));
    }
    
    @Test
    public void intersectsRange_set_belowLowerBound_get_false() {
    	Range range = new Range(-10, 10);
    	assertFalse(range.intersects(new Range(-20,-15)));
    }
    
    @Test
    public void intersectsRange_set_boveUpperBound_get_false() {
    	Range range = new Range(-10, 10);
    	assertFalse(range.intersects(new Range(15, 20)));
    }
    
    //constrain
    @Test
    public void constrain_set_insideTheRange_get_inputValue() {
    	Range range = new Range(-10, 10);
    	assertEquals("The input value should be return", 1, range.constrain(1), .000000001d);
    }
    
    @Test
    public void constrain_set_belowLowerBound_get_lowerBound() {
    	Range range = new Range(-10, 10);
    	assertEquals("The lower bound value should be return", -10,range.constrain(-20), .000000001d);
    }
    
    @Test
    public void constrain_set_boveUpperBound_get_upperBound() {
    	Range range = new Range(-10, 10);
    	assertEquals("The upper bound value should be return", 10, range.constrain(15), .000000001d);
    } 
    
    //combine
    @Test
    public void combine_set_firstRangeNull_get_secondRange() {
    	Range range1 = null;
    	Range range2 = new Range(-10, 10);
    	assertEquals("The second range should be return", range2, Range.combine(range1, range2));
    }
    
    @Test
    public void combine_set_secondRangeNull_get_firstRange() {
    	Range range1 = null;
    	Range range2 = new Range(-10, 10);
    	assertEquals("The second range should be return", range2, Range.combine(range2, range1));
    }
    
    @Test
    public void combine_set_normalValues_get_result() {
    	Range range1 =  new Range(-1, 10);
    	Range range2 = new Range(-10, 1);
    	assertEquals("The combined range should be return", new Range(-10, 10), Range.combine(range2, range1));
    }
    
    //combineIgnoringNaN
    @Test
    public void combineIgnoringNaN_set_firstNullsecondNormal_get_secondRange() {
    	Range range1 = null;
    	Range range2 = new Range(-10, 10);
    	assertEquals("The second range should be return", range2, Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void combineIgnoringNaN_set_firstNormalsecondNull_get_firstRange() {
    	Range range1 = null;
    	Range range2 = new Range(-10, 10);
    	assertEquals("The first range should be return", range2, Range.combineIgnoringNaN(range2, range1));
    }
    
    @Test
    public void combineIgnoringNaN_set_normalValues_get_result() {
    	Range range1 =  new Range(-1, 10);
    	Range range2 = new Range(-10, 1);
    	assertEquals("The combined range should be return", new Range(-10, 10), Range.combineIgnoringNaN(range2, range1));
    }
    
    @Test
    public void combineIgnoringNaN_set_firstNullsecondNaN_get_null() {
    	Range range1 = null;
    	Range range2 = new Range(Double.NaN, Double.NaN);
    	assertNull("Null should be return", Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void combineIgnoringNaN_set_firstNaNsecondNull_get_null() {
    	Range range1 = null;
    	Range range2 = new Range(Double.NaN, Double.NaN);
    	assertNull("Null range should be return", Range.combineIgnoringNaN(range2, range1));
    }
    
    @Test
    public void combineIgnoringNaN_set_firstNormalsecondLowerBoundNaN_get_null() {
    	Range range1 = new Range(Double.NaN, 10);
    	Range range2 = new Range(-1, Double.NaN);
    	assertEquals("The combined range should be return", new Range(-1, 10), Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void combineIgnoringNaN_set_firstNormalsecondUpperBoundNaN_get_null() {
    	Range range1 = new Range(-1, 10);
    	Range range2 = new Range(Double.NaN, 20);
    	assertEquals("The combined range should be return", new Range(-1, 20), Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void combineIgnoringNaN_set_firstLowerBoundNaNsecondNormal_get_null() {
    	Range range1 = new Range(-1, 10);
    	Range range2 = new Range(Double.NaN, 10);
    	assertEquals("The combined range should be return", new Range(-1, 10), Range.combineIgnoringNaN(range2, range1));
    }
    
    @Test
    public void combineIgnoringNaN_set_firstUpperBoundNaNsecondNormal_get_null() {
    	Range range1 = new Range(-1, 10);
    	Range range2 = new Range(-10, Double.NaN);
    	assertEquals("The combined range should be return", new Range(-10, 10), Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void combineIgnoringNaN_set_bothNull_get_null() {
    	Range range1 = null;
    	Range range2 = null;
    	assertNull("This should return Null", Range.combineIgnoringNaN(range2, range1));
    }
    
    
    //expandToInclude
    @Test
    public void expandToInclude_set_nullRange_get_sameInputValueRange() {
    	Range range = null;
    	assertEquals("The same input value range should be return", new Range(0,0), Range.expandToInclude(range, 0));
    }
    
    @Test
    public void expandToInclude_set_insideRange_get_inputRange() {
    	Range range = new Range(-10, 10);
    	assertEquals("The input range should be return", range, Range.expandToInclude(range, 0));
    }
    
    @Test
    public void expandToInclude_set_belowLowerBound_get_extendedRange() {
    	Range range = new Range(-10, 10);
    	assertEquals("The extended range should be return", new Range(-20, 10), Range.expandToInclude(range, -20));
    }
   
    @Test
    public void expandToInclude_set_aboveUpperBound_get_extendedRange() {
    	Range range = new Range(-10, 10);
    	assertEquals("The extended range should be return", new Range(-10, 20), Range.expandToInclude(range, 20));
    }
    
    //expand
    @Test
    public void expand_set_nullRange_get_invalidParameterException() {
    	Range range = null;
    	try {
    	Range.expand(range, 0.25, 0.25);
    	fail("IllegalArgumentException shoulb be thrown.");
    	}
    	catch(IllegalArgumentException ex) {
    		
    	}
    	catch(Exception ex) {
    		fail("IllegalArgumentException shoulb be thrown.");
    	}
    }
    
    @Test
    public void expand_set_normalValues_get_extendedRange() {
    	Range range = new Range(-10, 10);
    	assertEquals("The extended range should be return", new Range(-30, 50), Range.expand(range, 1, 2));
    }
    
    @Test
    public void expand_set_lowerBecomeGraterThanUpper_get_extendedRange() {
    	Range range = new Range(1, 10);
    	assertEquals("The extended range should be return", new Range(-269, 28), Range.expand(range, 30, 2));
    }
    
    //shift
    @Test
    public void shift_set_nullRange_get_invalidParameterException() {
    	Range range = null;
    	try {
    	Range.shift(range, 0.25);
    	fail("IllegalArgumentException shoulb be thrown.");
    	}
    	catch(IllegalArgumentException ex) {
    		
    	}
    	catch(Exception ex) {
    		fail("IllegalArgumentException shoulb be thrown.");
    	}    
    }
    
    @Test
    public void shift_set_normalRangeTrueAllowZeroCrossing_get_expectedRange() {
    	Range range = new Range(1, 10);
    	assertEquals("The extended range should be return", new Range(6, 15), Range.shift(range, 5, true));
    }
    
    @Test
    public void shift_set_normalRangeFalseAllowZeroCrossing1_get_expectedRange() {
    	Range range = new Range(1, 10);
    	assertEquals("The extended range should be return", new Range(6, 15), Range.shift(range, 5));
    }
    
    @Test
    public void shift_set_normalRangeFalseAllowZeroCrossing2_get_expectedRange() {
    	Range range = new Range(-1, 10);
    	assertEquals("The extended range should be return", new Range(0, 15), Range.shift(range, 5));
    }
    
    @Test
    public void shift_set_normalRangeFalseAllowZeroCrossing3_get_expectedRange() {
    	Range range = new Range(0, 10);
    	assertEquals("The extended range should be return", new Range(5, 15), Range.shift(range, 5));
    }
    
    //scale
    @Test
    public void scale_set_nullRange_get_invalidParameterException() {
    	Range range = null;
    	try {
    	Range.scale(range, 0.25);
    	fail("IllegalArgumentException shoulb be thrown.");
    	}
    	catch(IllegalArgumentException ex) {
    		
    	}
    	catch(Exception ex) {
    		fail("IllegalArgumentException shoulb be thrown.");
    	}    
    }  
    
    @Test
    public void scale_set_normalRangeNegetiveFactor_get_illegalArgumentException() {
    	Range range = null;
    	try {
    	Range.scale(range, -1);
    	fail("IllegalArgumentException shoulb be thrown.");
    	}
    	catch(IllegalArgumentException ex) {
    		
    	}
    	catch(Exception ex) {
    		fail("IllegalArgumentException shoulb be thrown.");
    	}    
    } 
    
    @Test
    public void shift_set_normalValues_get_expectedRange() {
    	Range range = new Range(-10, 10);
    	assertEquals("The input range should be return", range, Range.scale(range, 1));
    }
    
    //equals
    @Test
    public void equals_set_invalidObject_get_expectedRange() {
    	Range range = new Range(-10, 10);
    	assertFalse("False should be return", range.equals(1));
    }
    
    @Test
    public void equals_set_sameLowerBound_get_expectedRange() {
    	Range range = new Range(-10, 10);
    	assertFalse("False should be return", range.equals(new Range(-10, 20)));
    }
    
    //hashCode
    @Test
    public void hashCode_set_normalValues_get_expectedRange() {
    	Range range = new Range(-10, 10);
    	assertNotNull("The hashCode should be return", range.hashCode());
    }  
    
    //contains
    @Test
    public void contains_set_sameUpperAndLower_get_expectedRange() {
    	Range range = new Range(10, 10);
    	assertTrue("This should return True", range.contains(10));
    }  
    
  //Assignment4: new test cases
    
    //getCentralValue
    @Test
    public void getCentralValue_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.getCentralValue();
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    //contains
    @Test
    public void contains_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.contains(5);
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    //intersects
    @Test
    public void intersects_negLowerBound() {
    	Range range = new Range(-1, 10);
    	assertTrue("The result should be true", range.intersects(0, 11));
    }
    
    @Test
    public void intersects_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.intersects(2, 11);
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    @Test
    public void intersects_bothOnUpperBound() {
    	Range range = new Range(1, 10);
    	assertFalse("The result should be false", range.intersects(10, 10));
    }
    
    
    @Test
    public void intersects_onUpperBound() {
    	Range range = new Range(1, 10);
    	assertFalse("The result should be false", range.intersects(10, 11));
    }
    
    @Test
    public void intersects_oneLowerThanUpperBound() {
    	Range range = new Range(1, 10);
    	assertTrue("The result should be true", range.intersects(9, 11));
    }
    
    @Test
    public void intersects_onLowerBound() {
    	Range range = new Range(1, 10);
    	assertFalse("The result should be false", range.intersects(-5, 1));
    }
    
    @Test
    public void intersects_oneGreaterThanLowerBound() {
    	Range range = new Range(1, 10);
    	assertTrue("The result should be true", range.intersects(-5, 2));
    }
    
    //constrain
    @Test
    public void constrain_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.constrain(0);
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    @Test
    public void constrain_containValueAndNegatedValue() {
    	Range range = new Range(-10, 10);
    	assertEquals("The result should be 5", 5, range.constrain(5), 0.0);
    }
    
    //scale
    @Test
    public void scale_set_zeroFactor() {
    	Range range = new Range(-10, 10);
    	assertEquals("The (0,0) range should be return", new Range(0,0), Range.scale(range, 0));
    }
    
    //equals
    @Test
    public void equals_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.equals(range);
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    
    //isNaNRange
    @Test
    public void isNaNRange_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.isNaNRange();
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    @Test
    public void isNaNRange_NaNLowerBound() {
    	Range range = new Range(Double.NaN, 10);
    	assertFalse("The should return false", range.isNaNRange());
    }
    
    @Test
    public void isNaNRange_NaNUpperBound() {
    	Range range = new Range(1, Double.NaN);
    	assertFalse("The should return false", range.isNaNRange());
    }
    
    @Test
    public void isNaNRange_NaNLowerAndUpperBound() {
    	Range range = new Range(Double.NaN, Double.NaN);
    	assertTrue("The should return true", range.isNaNRange());
    }
    
    @Test
    public void isNaNRange_normalRange() {
    	Range range = new Range(1, 10);
    	assertFalse("The should return false", range.isNaNRange());
    }
    
    //hashCode
    @Test
    public void hashCode_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.hashCode();
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    @Test
    public void hashCode_normalRange_then_result() {
    	Range range = new Range(1, 10);
    	assertEquals("The hash code should be return.", 2.11943424E9, range.hashCode(), 0.00001d);
    }
   
    
    //toString
    @Test
    public void toString_checkLowerAndUpper() {
    	Range range = new Range(1, 10);
    	range.toString();
    	assertEquals("The lower bound should be the same after creation", 1.0, range.getLowerBound(), 0.0);
    	assertEquals("The upper bound should be the same after creation", 10.0, range.getUpperBound(), 0.0);
    }
    
    //combineIgnoringNaN
    @Test
    public void combineIgnoringNaN_set_allNaN_get_null() {
    	Range range1 = new Range(Double.NaN, Double.NaN);
    	Range range2 = new Range(Double.NaN, Double.NaN);
    	assertNull("The should be null.", Range.combineIgnoringNaN(range1, range2));
    }
    
}