package com.skorulis.forplay.entities;

import java.util.ArrayList;

import org.jbox2d.dynamics.Body;

import com.skorulis.forplay.util.InputState;

import forplay.core.Layer;

public interface Entity {

  public void paint(float alpha);
  public ArrayList<Event> update(float delta,InputState input);
  public Layer layer();
  public float width();
  public float height();
  public boolean alive();
  public Body body();
}
