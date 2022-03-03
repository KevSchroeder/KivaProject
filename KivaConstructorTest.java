
/**
 * Write a description of KivaConstructorTest here.
 * 
 * Kevin Schroeder
 * 10.16.21
 */
import edu.duke.Point;

public class KivaConstructorTest { 
    String defaultLayout = "" + "-------------\n"
                            + "        P   *\n"
                            + "   **       *\n"
                            + "   **       *\n"
                            + "  K       D *\n"
                            + " * * * * * **\n"
                            + "-------------\n";
                 
    FloorMap defaultMap = new FloorMap(defaultLayout);

    public void testSingleArgumentConstructor() { 
        // GIVEN 
        // The default map we defined earlier 
        
        // WHEN 
        // We create a Kiva with the single-argument constructor         
        Kiva kiva = new Kiva(defaultMap); 

        // THEN 
        // The initial Kiva location is (2, 4) 
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(2, 4);
        if (sameLocation(initialLocation, expectedLocation)) { 
            System.out.println("testSingleArgumentConstructor SUCCESS"); 
        } else { 
            System.out.println(String.format( "testSingleArgumentConstructor FAIL: %s != (2,4)!", initialLocation)); 
        } 
    } 
    // For you: create a test for the constructor taking two arguments. 
    
    public void testTwoArgumentConstructor(){
        // GIVEN 
        // The default map we defined earlier 
        
        // WHEN 
        // We create a Kiva with the single-argument constructor         
        Point initialLocationPoint= new Point(5,6);
        Kiva kiva = new Kiva(defaultMap, initialLocationPoint); 

        // THEN 
        // The initial Kiva location is (5,6)
         
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(5, 6);
        if (sameLocation(initialLocation, expectedLocation)) { 
            System.out.println("testTwoArgumentConstructor SUCCESS"); 
        } else { 
            System.out.println(String.format( "testTwoArgumentConstructor FAIL: %s != (5,6)!", initialLocation)); 
        } 
    }
    
    private boolean sameLocation(Point a, Point b) { 
        return a.getX() == b.getX() && a.getY() == b.getY(); 
    } 
    
    
    }


