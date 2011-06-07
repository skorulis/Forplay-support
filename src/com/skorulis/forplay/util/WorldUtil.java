package com.skorulis.forplay.util;

import static forplay.core.ForPlay.graphics;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;


import forplay.core.CanvasLayer;
import forplay.core.DebugDrawBox2D;

public class WorldUtil {

  public static DebugDrawBox2D showDebugDraw(float width,float height,float physScale,World world) {
    int wid = (int) (width / physScale);
    int hgt = (int) (height / physScale);
    CanvasLayer canvasLayer =
        graphics().createCanvasLayer(wid,hgt);
    graphics().rootLayer().add(canvasLayer);
    DebugDrawBox2D debugDraw = new DebugDrawBox2D();
    debugDraw.setCanvas(canvasLayer);
    debugDraw.setFlipY(false);
    debugDraw.setStrokeAlpha(150);
    debugDraw.setFillAlpha(75);
    debugDraw.setStrokeWidth(2.0f);
    debugDraw.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
    debugDraw.setCamera(0, 0, 1f / physScale);
    world.setDebugDraw(debugDraw);
    return debugDraw;
  }
  
  public static void buildBounds(float width,float height,World world) {
    
    Body ground = world.createBody(new BodyDef());
    PolygonShape groundShape = new PolygonShape();
    groundShape.setAsEdge(new Vec2(0, height), new Vec2(width, height));
    ground.createFixture(groundShape, 0.0f);
    
    Body right = world.createBody(new BodyDef());
    PolygonShape rightShape = new PolygonShape();
    rightShape.setAsEdge(new Vec2(width, 0), new Vec2(width, height));
    right.createFixture(rightShape, 0.0f);
    
    Body left = world.createBody(new BodyDef());
    PolygonShape leftShape = new PolygonShape();
    leftShape.setAsEdge(new Vec2(0, 0), new Vec2(0, height));
    left.createFixture(leftShape, 0.0f);
    
    Body roof = world.createBody(new BodyDef());
    PolygonShape roofShape = new PolygonShape();
    roofShape.setAsEdge(new Vec2(0, 0), new Vec2(width, 0));
    roof.createFixture(roofShape, 0.0f);
    
  }
  
}
