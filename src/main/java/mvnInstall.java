import java.io.IOException;

class mvnInstall {
    static String path,mainPath;
    public static void getPath(String path1,String mainPath1){
        path = path1; mainPath =mainPath1;
    }
static int num =1;
    public synchronized static void run() {
        try {
            String matric = path.replaceAll("[^0-9]" , "").substring(0,6);
            String Command = "cmd /c mvn clean install -f "+path+"\\pom.xml > "+mainPath+ "\\mvnLog\\"+matric+"-mvn_install.txt";
            Process a = Runtime.getRuntime().exec(Command);
            a.waitFor();
            System.out.println("Installing Maven : " +path);
            num++;
        } catch (IOException | InterruptedException ex) {

        }
    }

}
