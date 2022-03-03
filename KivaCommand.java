
/**
 * Write a description of KivaCommand here.
 * 
 * Kevin Schroeder
 * 10.16.21
 */

/**
 * Public enum for the commmands
 * Designates actions for the Kiva
 */
public enum KivaCommand {
    FORWARD ('F'),
    TURN_LEFT('L'), 
    TURN_RIGHT('R'), 
    TAKE('T'),
    DROP('D');

    /**
     * Pivate char for direction key passing this.Keyword to initialize the direction
     */
    private char directionKey;
    
    private KivaCommand(char directionKey){
        this.directionKey = directionKey;
    }
    public char getDirectionKey(){
        return this.directionKey;
    }

    }
    
    

