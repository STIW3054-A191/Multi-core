import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class ControlUnit implements Runnable{
    private CountDownLatch latch;
    private String mainPath;
    ControlUnit(CountDownLatch latch,String mainPath){
        this.latch = latch;
        this.mainPath=mainPath;
        }

    int[] total =new int[28];
    ObjectMetrics[][] met = new ObjectMetrics[28][24];
    List<String> matArr = new LinkedList<>();
    public static List<String> getFolder(String path){
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
    static int num =0;
    int i =0;

int num1=1;

    public void runJar(String path,String ckjmpath){
        try{
            RecursiveSearch.search(new File(path),"jar");
            String jarCommand =RecursiveSearch.resultPath;
            String jarPath =RecursiveSearch.jarpath;
            String matric = path.replaceAll("[^0-9]" , "").substring(0,6);

            String Command = "cmd /c  cd "+jarPath+" && java -jar "+jarCommand+" > "+mainPath+"\\out\\"+matric+"_out.txt";
            Process a1 = Runtime.getRuntime().exec(Command);
            a1.waitFor();
            num1++;

            RecursiveSearch.search(new File(path),"class");
            String gtclass = RecursiveSearch.resultPath;
//            String ckjm = "cmd /c java -jar C:\\Users\\Raven\\IdeaProjects\\Multi-Core\\ckjm-1.9.jar "+gtclass+"\\*class";
            String ckjm = "cmd /c java -jar "+ ckjmpath+" "+gtclass+"\\*class";
            Process a = Runtime.getRuntime().exec(ckjm);
            System.out.println("CKJM metrics " +gtclass);
            BufferedReader reader = new BufferedReader(new InputStreamReader(a.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                met[num][i] = new ObjectMetrics(matric,line);
                i++;
            }
            i=0;
            matArr.add(num,matric);
            num++;

            CkjmLog.createLog(gtclass,matric,mainPath);

        }catch (IOException | InterruptedException ex){

        }

    }
    public synchronized void run(){
         String path = mainPath;
        List<String> pomPathList=new ArrayList<String>();
        for(int i =0;i < getFolder(path).size();i++){
            String folderPath = getFolder(path).get(i);
            Locator ff = new Locator();
            ff.pomPath(folderPath);
            if(!"".equals(ff.pomPath(folderPath))){
//                int begin=ff.pomPath(folderPath).indexOf("D:");
                int last=ff.pomPath(folderPath).indexOf("\\pom");
                if( last == -1){
                    System.out.println("No Pom File Found");
                }else {
                    pomPathList.add(ff.pomPath(folderPath).substring(0, last));
                }
            }else{
                System.out.println("No Pom File Found");
            }
        }


        for(int i = 0;i < pomPathList.size();i++){
            mvnInstall.getPath(pomPathList.get(i),mainPath);
            mvnInstall.run();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the CKJM Path : ");
        String Ckjmpath = scan.nextLine();

        for(int i = 0;i < pomPathList.size();i++){
            runJar(pomPathList.get(i),Ckjmpath);
            System.out.println("Creating log files for RUN jar and CKJM");
        }
        latch.countDown();
    }


    ObjectMetrics mt;
    public void metricsca() throws IOException {
            for(int a=0;a<25;a++){
            for(int b=0;b<23;b++) {
                try {
                    met[a][b].ObjectMetrics(a,b);
            } catch (NullPointerException e) {}
            }
        }
       ObjectMetrics.cal(matArr);

}}


