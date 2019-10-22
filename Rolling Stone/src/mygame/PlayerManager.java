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
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

/**
 *
 * @author Bob
 */
public class PlayerManager extends AbstractAppState {
  
  public Player             currentPlayer;
  public SimpleApplication  app;
  public AppStateManager    stateManager;
  public Node               rootNode;
  public AssetManager       assetManager;

  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app            = (SimpleApplication) app;
    this.rootNode       = this.app.getRootNode();
    this.stateManager   = this.app.getStateManager();
    this.assetManager   = this.app.getAssetManager();
    createPlayer();
    }
  
  public void createPlayer()  {
    Material stone_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key = new TextureKey("Textures/Rock.png");
    Texture tex = assetManager.loadTexture(key);
    stone_mat.setTexture("ColorMap", tex);
    Sphere sphere = new Sphere(32, 32, 0.4f, true, false);
    Geometry rockGeo = new Geometry("Rock", sphere);
    rockGeo.setMaterial(stone_mat);
    
    Player player = new Player();
    player.model = new Node();
    player.score = 0;
    player.isDead = false;
    currentPlayer = player;
    player.model.attachChild(rockGeo);
    player.attachChild(player.model);
    rootNode.attachChild(player);
    player.setLocalScale(2f);
    player.setLocalTranslation(0, -.1f, 0);
    }
  
    @Override
    public void update(float tpf){
      float speed = currentPlayer.score/20;
      
      if (speed < 1)
      speed = 1;
      if (speed > 15)
      speed = 15;
      
      if (!currentPlayer.isDead)
      currentPlayer.rotate(currentPlayer.turn, 0, speed);

      CollisionResults results = new CollisionResults();
      rootNode.getChild("TreeNode").collideWith(currentPlayer.model.getWorldBound(), results);
      if (results.size() > 0)
      currentPlayer.isDead = true;
      }
    
    }
