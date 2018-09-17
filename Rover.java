import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;
/**
 * Write a description of class rover here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rover
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private int dir;
    private String name;
    private int numPics = 0;
    private int hp = 100;
    private boolean isAlive = true;

    private HashMap<Integer, String> directions = new HashMap<Integer, String>();

    /**
     * Constructor for objects of class rover
     */

    public Rover(String name)
    {
        // initialise instance variables
        this.x = 0;
        this.y = 0;
        this.name = name;
        this.hp = 100;
        initDir();
        System.out.println("New Rover named " + name + ".");

    }

    private void initDir() {
        directions.put(0,"NORTH");
        directions.put(1,"EAST");
        directions.put(2,"SOUTH");
        directions.put(3,"WEST");
    }

    public String toString() {
        return name + " is facing " + currentDirectionName() + " at [" + Integer.toString(x) + "," + Integer.toString(y) + "] and has taken " + Integer.toString(numPics) + " pictures. Energy="+ Integer.toString(hp); 
    }

    //Moving
    public void rotate(int turnClockwise) {
        if (deductEnergy(0)) {  //dont change energy, just prevent dir from being changed
            dir += turnClockwise;
            face(dir);
        }
    }

    public void face(int direction)  //ALL turns call this
    {
        // put your code here
        if (deductEnergy(1)) {
            dir = getValidDir(direction);
            //System.out.println(name + " is facing " + currentDirectionName() + ".");
        }
    }

    public void move(int dist) {  //ALL moves call this

        if (!deductEnergy(dist)) { 
            charge();
        }
        if (dir == 0) {
            y += dist;
        } else if (dir == 1) {
            x += dist;
        } else if (dir == 2) {
            y -= dist;
        } else {
            x -= dist;
        }
        System.out.println(name + " moved to [" + Integer.toString(x) + "," + Integer.toString(y) + "].");

    }

    public void moveTo(int x1,int y1) {
   
        int xdiff = x1 - this.x;
        int ydiff = y1 - this.y;
        // while (this.x != x1 && this.y != y1) {
        face(1);
        move(xdiff);
        face(0);
        move(ydiff);
        // }
    }

    public void teleport(int x1,int y1) {
        moveTo(x1,y1);
    }

    public void goHome() {
        moveTo(0,0);
    }
    
    
    public void takePicture() {takePicture(false);}
    public void takePicture(boolean fromSky) {

        if (numPics >= 5) {
            System.out.println(name + "'s memory must be cleared before it can take another picture.");
            return;
        } else {
            if (deductEnergy(3)) {
                if (fromSky) {
                    System.out.println(name + " took one last picture of Mars.");
                } else {
                    System.out.println(name + " took a picture at [" + Integer.toString(x) + "," + Integer.toString(y) + "] facing " + currentDirectionName() + ".");
                    
                }
                numPics++;
            }
        }
    }

    public void transmitPictures() {
        if (deductEnergy(8)) {
            System.out.println(name + " transmitted all pictures back to Earth.");
            numPics = 0;
        }
    }

    //directions

    private int getValidDir(int d) {
        while (d > 3) {
            d -= 4;
        }
        while (d < 0) {
            d += 4;
        }
        return d;
    }

    public String currentDirectionName() {
        return directions.get(dir); //current dir
    }

    public String getDirectionName(int d) {
        return directions.get(d);
    }

    //energy levels 
    public void die() { //?
    }

    private boolean deductEnergy(int nrg) {

        if (hp <= 0) {
            System.out.println(name + " ain't got no battery left. Please charge it.");
        } else {
            hp -= Math.abs(nrg);
            hp = Math.max(hp,0);
        }
        return  hp > 0;
    }

    public void charge() {charge(100);}
    public void charge(int upTo) { 
        System.out.println("\nGotta charge.");
        while (hp < upTo) {

            System.out.println("Charging..." + Integer.toString(hp) + "%");
            hp += 29;
            wait(200);
        }
        hp = upTo;
        System.out.println("Charged to " + Integer.toString(upTo) + "%.\n");
    }

    public void goBackToEarth(){
        System.out.println("\n============================\n============================\nStarting return to Earth.\nMoving to launch pad.");
        charge();
        moveTo(30,30);
        charge();
        int progress =  0;
        System.out.println("============================\nLaunching from Mars.");
        while (progress < 100) { 
            progress += 6;
            progress = Math.min(progress,100);
            wait (150);
            hp -= 6;
            System.out.println("Returning to Earth..." + Integer.toString(progress) + "%, charge=" + Integer.toString(hp) + "%");
            if (progress == 6) { //picture from rover as it leaves the surface
                takePicture(true);
                charge();
            }
        }
        System.out.println("Journey to Earth complete.\n\n\n\n\n");
        isAlive = false;
        
        System.exit(0);
    }

    private void wait(int ms) {
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
