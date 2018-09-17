
/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RunnerWithScanner
{
    
    public static void main(String[] args) {
        Rover r1 = new Rover("HOMIE");
        SimpleScanner scanner = new SimpleScanner();
        
        print(r1.toString());
        print("All possible commands include:\n\nmove\nmoveto\nface\nrotate\npicture\ntransmit\ncharge\ncome home\ninfo\n\n");
        
        boolean isRunning = true;
        while (isRunning) {
            print("Enter a command:\n");
            String input = scanner.readString();
            switch (input) {
              case "move":
                    print("Enter distance:");
                    int far = scanner.readInt();
                    r1.move(far);
                    break;
              case "moveto":
                    print("Enter x-coordinate:");
                    int xc = scanner.readInt();
                    print("Enter y-coordinate:");
                    int yc = scanner.readInt();
                    r1.moveTo(xc,yc);
                    break;
              case "face":
                    print("Enter direction (0-3):");
                    int d = scanner.readInt();
                    r1.face(d);
                    break;
              case "rotate":
                    print("Enter number of turns (clockwise):");
                    int t = scanner.readInt();
                    r1.rotate(t);
                    break;
              case "picture":
                    r1.takePicture();
                    break;
              case "transmit":
                    r1.transmitPictures();
                    break;
              case "charge":
                    r1.charge();
                    break;
              case "come home":
                    r1.goBackToEarth();
                    isRunning = false;
                    break;
              case "info":
                    print(r1.toString());
                    break;
              case "quit":
                    isRunning = false;
                    break;
              default:
                  
                    break;
            }
        }
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
    
    public static void print(String s) {
        System.out.println(s);
    }
}
