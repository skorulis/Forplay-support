package com.skorulis.forplay.util;

import org.jbox2d.common.Vec2;

import forplay.core.Keyboard;
import forplay.core.Layer;
import forplay.core.Pointer;
import forplay.core.Transform;
import static forplay.core.ForPlay.*;

public class InputState implements Keyboard.Listener,Pointer.Listener{

  private boolean[] keys; 
  private Vec2 pointer;
  private boolean pointerDown;
  private float scale;
  private Layer clickLayer;
  
  public InputState(int numKeys) {
    keys = new boolean[numKeys];
    scale = 1.0f;
    pointer = new Vec2();
  }
  
  /**
   * Reset keyboard and pointer states
   */
  public void reset() {
    keys = new boolean[keys.length];
    pointerDown = false;
  }


  @Override
  public void onKeyDown(int keyCode) {
    keys[keyCode] = true; 
  }

  @Override
  public void onKeyUp(int keyCode) {
    keys[keyCode] = false;    
  }
  
  /**
   * Checks if a given key is pressed
   * @param keyCode key to check
   * @return true if the key is currently down
   */
  public boolean keyDown(int keyCode) {
    return keys[keyCode];
  }

  @Override
  public void onPointerStart(float x, float y) {
    pointerDown = true;
    pointer.x = (x-clickLayerTransX())*scale; 
    pointer.y = (y-clickLayerTransY())*scale;
  }

  @Override
  public void onPointerEnd(float x, float y) {
    pointerDown = false;
    pointer.x = (x-clickLayerTransX())*scale; 
    pointer.y = (y-clickLayerTransY())*scale;
  }

  @Override
  public void onPointerDrag(float x, float y) {
    pointer.x = (x-clickLayerTransX())*scale; 
    pointer.y = (y-clickLayerTransY())*scale;
  }
  
  private float clickLayerTransX() {
    if(clickLayer==null) {
      return 0;
    }
    return clickLayer.transform().tx();
  }
  
  private float clickLayerTransY() {
    if(clickLayer==null) {
      return 0;
    }
    return clickLayer.transform().ty();
  }
  
  /**
   * @return True if the mouse button is currently pressed
   */
  public boolean mouseDown() {
    return pointerDown;
  }
  
  /**
   * @return The currrent position of the pointer
   */
  public Vec2 pointer() {
    return pointer;
  }
  
  /**
   * Conveniance method to calculate the mouse direction in relation to a point
   * @param from 
   * @return Normalized vector towards the pointer
   */
  public Vec2 mouseDir(Vec2 from) {
    Vec2 ret = pointer.sub(from);ret.normalize();
    return ret;
  }
  
  /**
   * @return the current scale
   */
  public float scale() {
    return scale;
  }
  
  /**
   * Set this scale when the main layer is scaled so that input events
   * translate correctly
   * @param scale New scale
   */
  public void setScale(float scale) {
    this.scale = scale;
  }
  
  
  public void setClickLayer(Layer layer) {
    this.clickLayer = layer;
  }
  
}
