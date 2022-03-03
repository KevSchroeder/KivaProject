import edu.duke.FileResource;

/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 * This is starter code that may or may not work. You will need to update the code to
 * complete the project.
 */
public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);

        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        KivaCommand[] remoteRun = convertToKivaCommands(directions);
        Kiva kiva = new Kiva(floorMap);
        System.out.println(String.format("The Kiva is at %s, and is facing %s", kiva.getCurrentLocation(), kiva.getDirectionFacing()));
        //loop over KivaCommand array
        for (KivaCommand command : remoteRun){
        kiva.move(command); 
       }
        //for each element call kiva.move
       if(!kiva.isCarryingPod()){
         System.out.println("I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place.");  
        }
      
        if (kiva.isSuccessfullyDropped()){
         System.out.println("Successfully picked up the pod and dropped it off. Thank you!");  
       }
      
    }
    
   

    public KivaCommand[] convertToKivaCommands(String commands){
        char[] direction = commands.toUpperCase().toCharArray();
         if(direction[direction.length -1] != 'D'){
         System.out.println("");
        }
        KivaCommand[] kivaCommandArray = new KivaCommand[direction.length];
        for(int i = 0; i <direction.length; i++){
            for(KivaCommand kivaCommand : KivaCommand.values()){
               if(kivaCommand.getDirectionKey() == direction[i]){
                   kivaCommandArray[i] = kivaCommand;
                }
            }
        }      
       //return array of kivacommands
     return kivaCommandArray;
    }
  }
     



    
    

