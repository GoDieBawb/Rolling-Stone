/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import tonegod.gui.core.Screen;

/**
 *
 * @author Bob
 */
public class InteractionManager extends AbstractAppState implements ActionListener{

  public    SimpleApplication      app;
  public    AppStateManager        stateManager;
  public    InputManager           inputManager;
  public    boolean                left = false;
  public    boolean                right = false;
  public    Player                 player;
  public    TreeManager            treeManager;
  public    Screen                 screen;

  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app            = (SimpleApplication) app;
    this.stateManager   = this.app.getStateManager();
    this.inputManager   = this.app.getInputManager();
    this.player         = this.stateManager.getState(PlayerManager.class).currentPlayer;
    this.treeManager    = this.stateManager.getState(TreeManager.class);
    screen = new Screen(app, "tonegod/gui/style/atlasdef/style_map.gui.xml");
    screen.setUseTextureAtlas(true,"tonegod/gui/style/atlasdef/atlas.png");
    screen.setUseMultiTouch(true);
    this.app.getGuiNode().addControl(screen);
    initKeys();
    }
  
  public void initKeys(){
    inputManager.setSimulateMouse(true);
    inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
    inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
    inputManager.addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    inputManager.addListener(this, "Left");
    inputManager.addListener(this, "Right");
    inputManager.addListener(this, "Click");
    }
  
  public void onAction(String binding, boolean isPressed, float tpf) {
          
    if(player.isDead){
      player.isDead = false;
      player.score = 0;
      treeManager.treeNode.detachAllChildren();
      treeManager.createTree();
      }
    
    if (binding.equals("Left")) {
      left = isPressed;
      if(left)
      player.turn = 10;
      else
      player.turn = 0;
      }
    
    if (binding.equals("Right")) {
      right = isPressed;
      if(right)
      player.turn = -10;
      else
      player.turn = 0;
      }

    if (binding.equals("Click")) {
      
      
      Vector2f clickSpot = inputManager.getCursorPosition();
      float xSpot = clickSpot.getX();
      float ySpot = clickSpot.getY();
         
      if (ySpot > screen.getHeight()/2 && xSpot > screen.getWidth()/2){
      left = isPressed;
      if(left)
      player.turn = 10;
      else
      player.turn = 0;
      }
      
      else if (ySpot < screen.getHeight()/2 && xSpot < screen.getWidth()/2){
      left = isPressed;
      if(left)
      player.turn = 10;
      else
      player.turn = 0;
      }
      
      else {
      right = isPressed;
      if(right)
      player.turn = -10;
      else
      player.turn = 0;
      }
      
      }
    }
  
    
}
