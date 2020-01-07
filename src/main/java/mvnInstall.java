import java.io.IOException;

class mvnInstall implements Runnable{
    static String path;
    public static void getPath(String path1){
        path = path1;
    }

    @Override
    public synchronized void run() {
        try {
            String matricNum = path.substring(12,18);
            String Command = "cmd /c mvn clean install -f "+path+"\\pom.xml > D:\\myData\\mvnLog\\"+matricNum+".mvninstall.txt";
            Process a = Runtime.getRuntime().exec(Command);
        } catch (IOException ex) {

        }
    }

}
