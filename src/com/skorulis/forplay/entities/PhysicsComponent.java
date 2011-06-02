package com.skorulis.forplay.entities;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsComponent {

  private Body body;
  private BodyDef bodyDef;
  private FixtureDef fixtureDef;
  private float physScale;
 
  public PhysicsComponent() {}
  public PhysicsComponent(BodyType bodyType,float physScale) {
    bodyDef = new BodyDef();
    bodyDef.type = bodyType;
    bodyDef.position = new Vec2(0, 0);
    this.physScale = physScale;
  }

  public static Shape getRect(float left,float top,float width,float height) {
	  PolygonShape polygonShape = new PolygonShape();
	  Vec2[] polygon = new Vec2[4];
	  polygon[0] = new Vec2(left, top);
	  polygon[1] = new Vec2(left+width, top);
	  polygon[2] = new Vec2(left+width, top+height);
	  polygon[3] = new Vec2(left, top+height);
	  polygonShape.set(polygon, polygon.length);
	  return polygonShape;
  }
  
  public void createBody(World world) {
    body = world.createBody(bodyDef);
    body.createFixture(fixtureDef);
  }
  
  public Body body() {
    return body;
  }
  
  public void setBody(Body body) {
    this.body = body;
  }
  
  public BodyDef bodyDef() {
    return bodyDef;
  }
  
  public void setBodyDef(BodyDef def) {
    this.bodyDef = def;
  }
  
  public FixtureDef fixtureDef() {
    return fixtureDef;
  }
  
  public void setFixtureDef(FixtureDef fixtureDef) {
    this.fixtureDef = fixtureDef; 
  }
  
  public void move(Vec2 force) {
    body.applyLinearImpulse(force, new Vec2(0,0));
  }
  
  public float x() {
    return body.getPosition().x;
  }
  
  public float y() {
    return body.getPosition().y;
  }
  
  public float scaleX() {
    return x()/physScale;
  }
  
  public float scaleY() {
    return y()/physScale;
  }
  
  /**
   * Set the position of the object in screen co-ordinates
   * @param x
   * @param y
   */
  public void setScreenPosition(float x,float y) {
    body().setTransform(new Vec2(x*physScale,y*physScale), body().getAngle());
  }
  
  public void setPhysScale(float scale) {
    this.physScale = scale;
  }
  
  public float physScale() {
    return physScale;
  }
  
  
}