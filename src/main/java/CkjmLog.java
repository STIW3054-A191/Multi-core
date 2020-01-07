import java.io.IOException;

public class CkjmLog {

    public static void createLog(String gtclass, String matricNum) throws IOException {
        String ckjmLog = "cmd /c   cd C:\\Users\\Raven\\Desktop && java -jar C:\\Users\\Raven\\IdeaProjects\\Multi-Core\\ckjm-1.9.jar "+gtclass+"\\*class>D:\\myData\\CkjmLog\\"+matricNum+".log.txt";
        Process a = Runtime.getRuntime().exec(ckjmLog);
    }
}
