import java.io.File;
import java.io.IOException;

public class RunTester {
    public static void main(String []args){
       CreateLog();
    }

    //To DO Create Folder Path in Java
    //Grab Info

    public static void CreateLog(){
        try {
            String filename = "Log.txt";
            String filename1 = "Error_log.txt";
            String workingDirectory = System.getProperty("user.dir");

            String absoluteFilePath = "";
            String absoluteFilePath1 ="";
            absoluteFilePath = workingDirectory + File.separator + filename;
            absoluteFilePath1 = workingDirectory + File.separator + filename1;
            System.out.println("Final filepath : " + absoluteFilePath);
            System.out.println("Final filepath : "+ absoluteFilePath1);

            File file = new File(absoluteFilePath);
            File file1 = new File(absoluteFilePath1);

            if (file.createNewFile() && file1.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File is already existed!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
