/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author Bob
 */
public class CameraManager extends AbstractAppState {

  public    SimpleApplication      app;
  public    AppStateManager        stateManager;
  public    Player                 player;
  public    FlyByCamera            flyCam;
  public    ChaseCamera            chaseCam;
  public    Camera                 cam;
  public    InputManager           inputManager;
    
  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app            = (SimpleApplication) app;
    this.stateManager   = this.app.getStateManager();
    this.cam            = this.app.getCamera();
    this.inputManager   = this.app.getInputManager();
    this.player         = this.stateManager.getState(PlayerManager.class).currentPlayer;
    this.flyCam         = this.app.getFlyByCamera();
    System.out.println("Camera Inatializing");
    setUpCam();
    }
  
  public void setUpCam(){
    flyCam.setEnabled(false);
    chaseCam = new ChaseCamera(cam, player.model, inputManager);
    chaseCam.setMinDistance(7);
    chaseCam.setMaxDistance(7);
    chaseCam.setLookAtOffset(new Vector3f(0f, 1f, 0));
    chaseCam.setDefaultHorizontalRotation(-3f);
    chaseCam.setDefaultVerticalRotation(0);
    chaseCam.setUpVector(new Vector3f(0f, 1f, 0f));
    chaseCam.setDragToRotate(false);
    }
    
}
