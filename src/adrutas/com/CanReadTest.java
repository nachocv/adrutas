package adrutas.com;

import java.io.File;

/**
 * @author Javin
 *
 */
public class CanReadTest {
       public static void main(String[] args) throws SecurityException {
        // Create a File object
        File myTestFile = new File("C:/eclipse/Rutas/nachocv/war/WEB-INF/lib/struts2-core-2.3.16.3.jar");
        //Tests whether the application can Read  the file
        if (myTestFile.canRead()) {
            System.out.println(myTestFile.getAbsolutePath() + "Can Read: ");
        } else {
            System.out.println(myTestFile.getAbsolutePath() + " Cannot Read: ");
        }
    }
}
