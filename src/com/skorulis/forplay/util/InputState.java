package com.skorulis.forplay.util;

import org.jbox2d.common.Vec2;

import forplay.core.Keyboard;
import forplay.core.Pointer;
import static forplay.core.ForPlay.*;

public class InputState implements Keyboard.Listener,Pointer.Listener{

  private boolean[] keys;
  private Vec2 pointer;
  private boolean pointerDown;
  
  public InputState(int numKeys) {
    keys = new boolean[numKeys];
    pointer = new Vec2();
  }
  
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
  
  public boolean keyDown(int keyCode) {
    return keys[keyCode];
  }

  @Override
  public void onPointerStart(float x, float y) {
    pointerDown = true;
    pointer.x = x; pointer.y = y;
  }

  @Override
  public void onPointerEnd(float x, float y) {
    pointerDown = false;
    pointer.x = x; pointer.y = y;
  }

  @Override
  public void onPointerDrag(float x, float y) {
    pointer.x = x; pointer.y = y;
  }
  
  public boolean mouseDown() {
    return pointerDown;
  }
  
  public Vec2 pointer() {
    return pointer;
  }
  
  public Vec2 mouseDir(Vec2 from) {
    Vec2 ret = pointer.sub(from);ret.normalize();
    return ret;
  }
  
}
