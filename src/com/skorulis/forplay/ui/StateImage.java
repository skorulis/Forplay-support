package com.skorulis.forplay.ui;

import java.util.ArrayList;
import java.util.List;

import forplay.core.Image;

/**
 * Represents a list of states that map to Images. A state is considered to match if all the true flags in the combo are on in the supplied flag
 * @author Alex
 */
public class StateImage {

	private List<StateImageCombo> combos;
	
	/**
	 * Create a state with a single combo
	 * @param state state to match
	 * @param image image to return
	 */
	public StateImage(int state,Image image) {
		this(new int[]{state},new Image[]{image});
	}
	
	public StateImage(int[] states,Image[] images) {
		if(states.length!=images.length) {
			throw new IllegalArgumentException("number of states and images must match");
		}
		combos = new ArrayList<StateImageCombo>();
		for(int i=0;i < states.length; ++i) {
			combos.add(new StateImageCombo(states[i],images[i]));
		}
	}
	
	public Image getImage(int state) {
		for(StateImageCombo combo: combos) {
			if( (combo.state & state) == combo.state) {
				return combo.image;
			}
		}
		return null;
	}
	
	private static class StateImageCombo {
		
		public StateImageCombo(int i, Image image) {
			this.state = i;
			this.image = image;
		}
		private int state;
		private Image image;
		
	}
	
}
