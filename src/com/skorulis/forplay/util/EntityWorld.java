package com.skorulis.forplay.util;

import static forplay.core.ForPlay.graphics;

import java.util.Iterator;
import java.util.LinkedList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import com.skorulis.forplay.entities.Entity;

import forplay.core.DebugDrawBox2D;
import forplay.core.GroupLayer;

public class EntityWorld<T extends Entity> {
  
  protected World world;
  protected LinkedList<T> entities,newEntities;
  protected GroupLayer entityLayer;
  protected float width,height;
  private boolean showDebugDraw;
  private DebugDrawBox2D debugDraw;
  
  public EntityWorld(float width,float height,Vec2 gravity,float physScale,boolean showDebugDraw) {
    this.showDebugDraw = showDebugDraw;
    entities = new LinkedList<T>();
    newEntities = new LinkedList<T>();
    this.width = width*physScale;
    this.height = height*physScale;
    world = new World(gravity, true);
    
    entityLayer = graphics().createGroupLayer();
    entityLayer.setScale(1f / physScale);
    graphics().rootLayer().add(entityLayer);
    
    WorldUtil.buildBounds(this.width, this.height,world);
    
    if(showDebugDraw) {
      debugDraw = WorldUtil.showDebugDraw(this.width, this.height, physScale, world);
    }
  }
  
  public World world() {
    return world;
  }
  
  public void addEntity(T e) {
    newEntities.add(e);
    entityLayer.add(e.layer());
  }
  
  public GroupLayer entityLayer() {
    return entityLayer;
  }
  
  public void paint(float alpha) {
    Iterator<T> it = entities.iterator();
    Entity e;
    while(it.hasNext()) {
      e = it.next();
      e.paint(alpha);
    }
    
    if (showDebugDraw) {
      debugDraw.getCanvas().canvas().clear();
      world.drawDebugData();
    }
    
  }
  
}
