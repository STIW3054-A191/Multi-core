import java.io.IOException;

public class CkjmLog {
static int num =1;
    public static void createLog(String gtclass, String matricNum,String mainPath,String ckjmpath) throws IOException {
        String ckjmLog = "cmd /c java -jar "+ ckjmpath+ " " +gtclass+ "\\*class > "+mainPath+"\\CkjmLog\\"+matricNum+"-log.txt";
        Process a = Runtime.getRuntime().exec(ckjmLog);
        num++;
    }
}
