import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Compiler implements Runnable {
private CountDownLatch latch1;
private static String mainPath;

    Compiler(CountDownLatch latch1,String mainPath){
        this.latch1=latch1; this.mainPath=mainPath;
    }

    public List<String> getFolder(String path){
        File file = new File(path);
        File[] array = file.listFiles();
        List<String> folderPathList=new ArrayList<String>();
        for(int i =0;i < array.length;i++){
            if(array[i].isDirectory()){
                String folderPath = array[i].getPath();
                folderPathList.add(folderPath);
            }
        }
        return folderPathList;
    }
    int num =1;
    public synchronized void CompileProgram(String path) throws InterruptedException{

        try {

            String matric = path.replaceAll("[^0-9]" , "").substring(0,6);
            String Command = "cmd /c cd "+path+" && mvn compile > "+mainPath+"\\compileLog\\"+matric+"-mvn_Compile.txt";
            Process a = Runtime.getRuntime().exec(Command);
            num++;
            a.waitFor();
            System.out.println("Compiling : " + path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void run(){
        String path = mainPath;
        List<String> pomPathList=new ArrayList<String>();
        for(int i =0;i < getFolder(path).size();i++){
            String folderPath = getFolder(path).get(i);
            Locator ff = new Locator();
            ff.pomPath(folderPath);
            if(!"".equals(ff.pomPath(folderPath))){
                int last=ff.pomPath(folderPath).indexOf("\\pom");

                if( last == -1){
                 System.out.println("No Pom file found");
                }else {
                    pomPathList.add(ff.pomPath(folderPath).substring(0,last));
                }
            }else{
                System.out.println("No pom file found");
            }
        }

        for(int i = 0;i < pomPathList.size();i++){
            try {
                CompileProgram(pomPathList.get(i));

            } catch (InterruptedException e) {
            }
        }
        latch1.countDown();
    }
}


