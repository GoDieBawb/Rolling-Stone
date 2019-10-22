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
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;

/**
 *
 * @author Bob
 */
public class GUIManager extends AbstractAppState {

  public    SimpleApplication      app;
  public    AppStateManager        stateManager;
  public    AssetManager           assetManager;
  public    Player                 player;

  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app            = (SimpleApplication) app;
    this.stateManager   = this.app.getStateManager();
    this.assetManager   = this.app.getAssetManager();
    this.player         = this.stateManager.getState(PlayerManager.class).currentPlayer;
    System.out.println("GUI Inatializing");
    }
  
  @Override
    public void update(float tpf){
        BitmapFont guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        String bla = String.valueOf("Trees Passed: " + (int) player.score);
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText(bla);
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        app.getGuiNode().detachAllChildren();
        app.getGuiNode().attachChild(helloText);
      }
}
