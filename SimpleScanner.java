/**
 * A simple way to get string and numeric input.
 * 
 * @author Jon Cooper
 * @version 9/13/2018
 */

import java.util.Scanner;

public class SimpleScanner
{
    Scanner input;
    boolean showExceptions;
    
    public SimpleScanner() {
        this.input = new Scanner(System.in);
        this.showExceptions = false;
    }
    
    public SimpleScanner(boolean showExceptions) {
        this.input = new Scanner(System.in);
        this.showExceptions = showExceptions;
    }
    
    public String readString() {
        return input.nextLine();
    }
    
    public int readInt() {
        while (true) {
            try {
                String numStr = input.nextLine();
                return Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                if (showExceptions) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("You must enter an integer. Try again.");
        }
    }
    
    public int readInt(int min, int max) {
        while (true) {
            try {
                String numStr = input.nextLine();
                int n = Integer.parseInt(numStr);
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException e) {
                if (showExceptions) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("You must enter an integer in the range " + min + " to " + max + ". Try again.");
        }
    }
    
    public double readDouble() {
        while (true) {
            try {
                String numStr = input.nextLine();
                return Double.parseDouble(numStr);
            } catch (NumberFormatException e) {
                if (showExceptions) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("You must enter a double. Try again.");
        }
    }
    
    public double readDouble(double min, double max) {
        while (true) {
            try {
                String numStr = input.nextLine();
                double n = Double.parseDouble(numStr);
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException e) {
                if (showExceptions) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("You must enter a double in the range " + min + " to " + max + ". Try again.");
        }
    }
}