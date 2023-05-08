package dk.a2mate.palletizing.api.internal;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.model.PalletLayer;
import dk.a2mate.palletizing.api.model.PalletizingPattern;

public class PatternFactoryTest {
	
	  Pallet pallet;
	  Item item;

	    @BeforeEach                                         
	    void setUp() {
	        pallet = new Pallet();
	        item = new Item();
	    }

	    @Test                                               
	    @DisplayName("Null converts to BLOCK")   
	    void testNull() {
	    	Stacking stacking = PatternFactory.getPattern(null, pallet, item);
	        assertTrue(stacking instanceof BlockStacking);  
	    }
	    @Test                                               
	    @DisplayName("Invalid pattern sconverts to BLOCK")   
	    void testInvalid() {
	    	Stacking stacking = PatternFactory.getPattern(PalletizingPattern.BLOCK, pallet, item);
	        assertTrue(stacking instanceof BlockStacking);  
	    }

	    @Test                                               
	    @DisplayName("BLOCK should work")   
	    void testBlock() {
	    	Stacking stacking = PatternFactory.getPattern(PalletizingPattern.BLOCK, pallet, item);
	        assertTrue(stacking instanceof BlockStacking);  
	    }


}
