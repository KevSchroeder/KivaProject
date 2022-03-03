
/**
 * Write a description of Kiva here.
 * 
 * Kevin Schroeder
 * 10.16.21
 */

import edu.duke.Point;
/**
 * Represents the actions of Kiva
 * KivaCommand has the methods for how the Kiva moves
 * @return getCurrentLocation with this. for the method
 * @return true for Boolean carryingPod and isSuccessfullyDropped for action items that Kiva performs
 */
  public class Kiva {
  private Point currentLocation;
  private FacingDirection directionFacing;
  private FloorMap map;
  private boolean carryingPod;
  private boolean successfullyDropped;
  private long motorLifetime;

  public Kiva (FloorMap map){
      this.map = map;
      this.currentLocation = map.getInitialKivaLocation();
      directionFacing = FacingDirection.UP;
      motorLifetime = 0;
      carryingPod = false;
      successfullyDropped = false;
    
  }

  public Kiva (FloorMap map, Point currentLocation){
      this.map = map;
      this.currentLocation = currentLocation;
      directionFacing = FacingDirection.UP;
      motorLifetime = 0;
      carryingPod = false;
      successfullyDropped = false;

  }
  
  
  public void move (KivaCommand command){
      if(command == KivaCommand.FORWARD){
          //move forward
        if(command==KivaCommand.FORWARD){
          Point expectedLocation;
          if(FacingDirection.UP == getDirectionFacing()){ //Turn Left is getting stuck on UP
                int y = getCurrentLocation().getY() -1;
                if (y <= map.getMinRowNum()){
                    throw new IllegalMoveException(String.format
                    ("Moved outside FloorMap. Location is %s which is outside map", 
                    currentLocation));
                }
                expectedLocation = new Point(getCurrentLocation().getX(), y);
                podCollision(expectedLocation);
                this.currentLocation = expectedLocation;
          }else if (FacingDirection.DOWN == getDirectionFacing()){ //Turn Left is getting stuck on UP
                int y = getCurrentLocation().getY() + 1;
                if (y >= map.getMaxRowNum()){
                    throw new IllegalMoveException(String.format
                    ("Moved outside FloorMap. Location is %s which is outside map", 
                    currentLocation));
                }
                expectedLocation = new Point(getCurrentLocation().getX(), y);
                podCollision(expectedLocation);
                this.currentLocation = expectedLocation;
          }else if(getDirectionFacing() == FacingDirection.LEFT){   //move left
                int x = getCurrentLocation().getX() -1;
                if (x <= map.getMinColNum()){
                    throw new IllegalMoveException(String.format
                    ("Moved outside FloorMap. Location is %s which is outside map", 
                    currentLocation));
                }
                expectedLocation = new Point(x, getCurrentLocation().getY());
                podCollision(expectedLocation);
                this.currentLocation = expectedLocation;
          }else{
                int x = getCurrentLocation().getX() + 1;
                if (x >= map.getMaxColNum()){
                    throw new IllegalMoveException(String.format
                    ("Moved outside FloorMap. Location is %s which is outside map", 
                    currentLocation));
                }
                expectedLocation = new Point(x, getCurrentLocation().getY());
                podCollision(expectedLocation);
                this.currentLocation = expectedLocation;
          }
        }
    
          //obstacle check
          if(map.getObjectAtLocation(getCurrentLocation()) == FloorMapObject.OBSTACLE){
              throw new IllegalMoveException("Obstacle in the way");
          }
          
            incrementMotorLifetime();
      }else if(command == KivaCommand.TURN_LEFT){
          if(getDirectionFacing() == FacingDirection.UP){
            directionFacing = FacingDirection.LEFT;
          }else if(FacingDirection.LEFT == getDirectionFacing()){
            directionFacing = FacingDirection.DOWN;
          }else if(FacingDirection.DOWN == getDirectionFacing()){
            directionFacing = FacingDirection.RIGHT;
          }else{
            directionFacing = FacingDirection.UP;
          }
      }
      else if(command == KivaCommand.TURN_RIGHT){
         if(FacingDirection.UP == getDirectionFacing()){
            directionFacing = FacingDirection.RIGHT;
          }else if(FacingDirection.RIGHT == getDirectionFacing()){
            directionFacing = FacingDirection.DOWN;
          }else if(FacingDirection.DOWN == getDirectionFacing()){
            directionFacing = FacingDirection.LEFT;
          }else {
            directionFacing = FacingDirection.UP;
          } //DO THIS FOR THE OTHER DIRECTIONS
      }
      
      else if(command == KivaCommand.TAKE){
          if(map.getObjectAtLocation(getCurrentLocation()) != FloorMapObject.POD){
              throw new NoPodException(String.format("No POD present, location is %s", 
              currentLocation));
          }else{
              carryingPod = true;
          } 
      }
      
      else{
          if(map.getObjectAtLocation(getCurrentLocation()) != FloorMapObject.DROP_ZONE){
              throw new IllegalDropZoneException(String.format("Location is not a drop zone, Kiva is at location %s",
              currentLocation));
          }else if(!carryingPod){
              throw new IllegalMoveException("Kiva is not carrying a POD");
          }else{
              successfullyDropped = true;
          } 
      }
  }

  public void podCollision(Point expectedLocation){
      if(isCarryingPod() && map.getObjectAtLocation(expectedLocation) == FloorMapObject.POD){
              throw new IllegalMoveException("POD in location");
          }
  }
     
  
  public Point getCurrentLocation(){
      return this.currentLocation;
  }
  
  public FacingDirection getDirectionFacing(){
      return directionFacing;
  }

  public boolean isCarryingPod(){
    return carryingPod;
  }

  public boolean isSuccessfullyDropped(){
    return successfullyDropped;
  }  
  
  //MotorLifetime
  public long getMotorLifetime(){
      return motorLifetime;
  }
  
  public void incrementMotorLifetime(){
     motorLifetime += 1000;
  }
}


