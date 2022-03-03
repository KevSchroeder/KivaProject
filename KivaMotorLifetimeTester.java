
/**
 * Write a description of KivaMotorLifetimeTester here.
 * 
 * Kevin Schroeder
 * 10.16.21
 */
public class KivaMotorLifetimeTester {

    String defaultLayout = ""
                           + "-----"
                           + "|K D|"
                           + "| P |"
                           + "|* *|"
                           + "-----";
                           
    FloorMap defaultMap = new FloorMap(defaultLayout);
    Kiva kiva = new Kiva(defaultMap); 
    
    
    public void motorLifetimeTester(){
        System.out.println(kiva.getMotorLifetime());
        kiva.move(KivaCommand.TURN_RIGHT);
        //repeat print line
        kiva.getMotorLifetime();
        kiva.move(KivaCommand.FORWARD);
        kiva.getMotorLifetime();
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.getMotorLifetime();
        kiva.move(KivaCommand.FORWARD);
        kiva.getMotorLifetime();
        kiva.move(KivaCommand.TAKE);
        kiva.getMotorLifetime();
        
    }
    
    
}
