
/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Runner
{
    
    public static void main(String[] args) {
        Rover r1 = new Rover("HOMIE");
        wait(1000);
        r1.moveTo(30,30);
        wait(1000);
        r1.takePicture();
        wait(1000);
        r1.goHome();
        wait(1000);
        r1.transmitPictures();
        wait(1000);
        
        r1.goBackToEarth(); //ends program
    }
    
    public static void wait(int ms) {
        try        
        {
            Thread.sleep(ms);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
    }
}
