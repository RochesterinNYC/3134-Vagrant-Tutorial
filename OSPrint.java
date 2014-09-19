/**
 * <b>OSPrint class</b>
 * <p>
 * This class prints the system properties of the machine that runs this program.
 * <p>
 * Used for Vagrant demonstration/tutorial.
 */

public class OSPrint {
    public static void main(String[] args){
        System.out.println("This machine is running with the following properties: ");
        System.out.println("Operating System: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Architecture: " + System.getProperty("os.arch"));
        System.out.println("User: " + System.getProperty("user.name"));
        System.out.println("Java Home Directory: " + System.getProperty("java.home"));
    }
}

