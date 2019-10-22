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
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import java.util.Random;

/**
 *
 * @author Bob
 */
public class TreeManager extends AbstractAppState {
 
  public Node               treeNode;
  public Node               rootNode;
  public SimpleApplication  app;
  public AssetManager       assetManager;
  public AppStateManager    stateManager;
  public Player             player;
  public Material           leafMat;
  public Material           woodMat;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app            = (SimpleApplication) app;
    this.rootNode       = this.app.getRootNode();
    this.stateManager   = this.app.getStateManager();
    this.assetManager   = this.app.getAssetManager();
    this.player         = this.stateManager.getState(PlayerManager.class).currentPlayer;
    treeNode = new Node("TreeNode");
    rootNode.attachChild(treeNode);
    System.out.println("Trees Inatializing");
    initMaterials();
    createTree();
    }
  
  public void initMaterials(){
    woodMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    woodMat.setColor("Color", ColorRGBA.Brown);
    
    leafMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey leafKey = new TextureKey("Textures/grass.jpg");
    Texture leafTex = assetManager.loadTexture(leafKey);
    leafMat.setTexture("ColorMap", leafTex);
    }
  
  public void createTree() {
    
    Box trunkBox = new Box(1, 5f, 1);
    Geometry treeTrunk = new Geometry("Tree", trunkBox);
    treeTrunk.setMaterial(woodMat);
    
    Box box = new Box(3, 3, 3);
    Geometry treeLeaf = new Geometry("Leaves", box);
    treeLeaf.setMaterial(leafMat);
    
    Tree tree = new Tree();
    tree.model = new Node();
    tree.model.attachChild(treeTrunk);
    tree.model.attachChild(treeLeaf);
    treeLeaf.setLocalTranslation(0, 4, 0);
    tree.attachChild(tree.model);
    treeNode.attachChild(tree);
    placeTree(tree);
    }
  
  public void placeTree(Tree tree) {
    Random rand = new Random();
    float xSpot = rand.nextInt(180) + 30;
    float ySpot = rand.nextInt(300) - 150;
    tree.setLocalTranslation(xSpot, 1, ySpot);
    }
  
  @Override
  public void update(float tpf){
    if (!player.isDead)
    for (int i = 0; i < treeNode.getChildren().size(); i++) {

      Tree currentTree = (Tree) treeNode.getChild(i);
      float speed = -player.score/3;
      if (speed > -10)
      speed = -10;
      if(speed < -100)
      speed = -100;
      Vector3f moveDir = new Vector3f(speed, 0, player.turn);

      currentTree.setLocalTranslation(currentTree.getLocalTranslation().addLocal(moveDir.mult(tpf)));
      
      if (currentTree.getLocalTranslation().x < -5) {
      currentTree.removeFromParent();
      player.score++;
      }
      
      int maxTrees = (int) player.score/3;
      if(maxTrees < 20)
      maxTrees = 20;
      if (maxTrees > 50)
      maxTrees = 50;
      if (treeNode.getChildren().size() < maxTrees)
      createTree();
      
      }
    }
  }
