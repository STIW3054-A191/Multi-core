import java.io.File;
import java.util.concurrent.CountDownLatch;

public class MainDirectory implements Runnable{
    public static String mainPath;
    private CountDownLatch latch;
    static String[] dirList = {"\\myData","\\myData\\CkjmLog","\\myData\\compileLog","\\myData\\out","\\myData\\mvnLog"};

    MainDirectory(String getPath, CountDownLatch latch){
        mainPath =getPath;
        this.latch = latch;
    }

    @Override
    public synchronized void run() {
        for(int i = 0; i<5;i++){
            mkDirectory(mainPath+dirList[i]);
        }
        latch.countDown();
    }

    public static String getMainPath(){
        return mainPath + dirList[0];
    }
    public static String getCompilePath(){
        return mainPath + dirList[2];
    }
    public static String getMvnInstallPath(){
        return mainPath + dirList[4];
    }
    public static String getRunPath(){
        return mainPath + dirList[3];
    }
    public static String getCkjmPath(){
        return mainPath + dirList[1];
    }

    public static boolean mkDirectory(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                return file.mkdirs();
            } else {
                return false;
            }
        } catch (Exception e) {
        } finally {
            file = null;
        }
        return false;
    }





}
