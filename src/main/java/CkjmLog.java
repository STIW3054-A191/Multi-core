import java.io.IOException;

public class CkjmLog {
static int num =1;
    public static void createLog(String gtclass, String matricNum,String mainPath) throws IOException {
        String ckjmLog = "cmd /c   cd C:\\Users\\Raven\\Desktop && java -jar C:\\Users\\Raven\\IdeaProjects\\Multi-Core\\ckjm-1.9.jar "+gtclass+"\\*class > "+mainPath+"\\CkjmLog\\"+matricNum+"-log.txt";
        Process a = Runtime.getRuntime().exec(ckjmLog);
        num++;
    }
}
