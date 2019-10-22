/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 *
 * @author Bob
 */
public class SceneManager extends AbstractAppState {

  public Node               rootNode;
  public SimpleApplication  app;
  public AssetManager       assetManager;
  public AppStateManager    stateManager;

  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app            = (SimpleApplication) app;
    this.rootNode       = this.app.getRootNode();
    this.stateManager   = this.app.getStateManager();
    this.assetManager   = this.app.getAssetManager();
    app.getViewPort().setBackgroundColor(ColorRGBA.Cyan);
    System.out.println("Scene Inatializing");
    initFloor();
    }
  
  public void initFloor(){
    Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey snowKey = new TextureKey("Textures/grass.jpg");
    Texture tex1 = assetManager.loadTexture(snowKey);
    mat.setTexture("ColorMap", tex1);
    Box box = new Box(150, .2f, 150);
    Geometry floor = new Geometry("the Floor", box);
    floor.setMaterial(mat);
    floor.setLocalTranslation(0, -1, -1);
    rootNode.attachChild(floor);
    }
    
}
