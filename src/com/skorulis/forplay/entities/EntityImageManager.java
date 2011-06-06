package com.skorulis.forplay.entities;


import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.ResourceCallback;
import static forplay.core.ForPlay.*;

public class EntityImageManager implements ResourceCallback<Image>{

  private Entity entity;
  private ImageLayer tmpLayer;
  
  public EntityImageManager(Entity entity) {
    this.entity = entity;
  }

  @Override
  public void done(Image image) {
    
    ImageLayer layer = (ImageLayer) entity.layer();
    if(layer==null) {
      layer = tmpLayer;
    }
    layer.setWidth(image.width());
    layer.setHeight(image.height());
    layer.setOrigin(image.width() / 2f, image.height() / 2f);
    layer.setScale(entity.width() / image.width(), entity.height() / image.height());
  }

  @Override
  public void error(Throwable err) {
    log().debug("ERROR " + err);
  }
  
  public ImageLayer makeImageLayer(String path) {
    Image image = assetManager().getImage(path);
    tmpLayer = graphics().createImageLayer(image);
    image.addCallback(this);
    return tmpLayer;
  }
  
}
