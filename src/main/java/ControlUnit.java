import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ControlUnit {
    int[] total =new int[25];
    ObjectMetrics[][] met = new ObjectMetrics[25][20];
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


    public void runJar(String path){
        try{
            String matricNum = null;
            RecursiveSearch.search(new File(path),"jar");
            String jarCommand =RecursiveSearch.resultPath;
            String jarPath =RecursiveSearch.jarpath;
//          System.out.println("To Be Tst"+RecursiveSearch.jarpath);
            matricNum = path.substring(10,16);

               String Command = "cmd /c  cd "+jarPath+" && java -jar "+jarCommand+">D:\\myData\\out\\"+matricNum+".out.txt";
            Process a1 = Runtime.getRuntime().exec(Command);


            RecursiveSearch.search(new File(path),"class");
            String gtclass = RecursiveSearch.resultPath;
            String ckjm = "cmd /c   cd C:\\Users\\Raven\\Desktop && java -jar C:\\Users\\Raven\\IdeaProjects\\Multi-Core\\ckjm-1.9.jar "+gtclass+"\\*class";
            Process a = Runtime.getRuntime().exec(ckjm);

            BufferedReader reader = new BufferedReader(new InputStreamReader(a.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
               // System.out.println(num+" - LINE BY LINE METRICS | PART : " +i+" : "+line);
                met[num][i] = new ObjectMetrics(matricNum,line);
                i++;
            }
            i=0;
            matArr.add(num,matricNum);
            num++;

            CkjmLog.createLog(gtclass,matricNum);

        }catch (IOException | InterruptedException ex){

        }

    }
    public void runProgram(){
         String path = "D:\\myData";
        List<String> pomPathList=new ArrayList<String>();
        for(int i =0;i < getFolder(path).size();i++){
            String folderPath = getFolder(path).get(i);
            Locator ff = new Locator();
            ff.pomPath(folderPath);
            if(!"".equals(ff.pomPath(folderPath))){
                int begin=ff.pomPath(folderPath).indexOf("D:");
                int last=ff.pomPath(folderPath).indexOf("\\pom");
                if(begin == -1 || last == -1){
                    System.out.println("NOT INDEX AND NO POM FILE FOUND");
                }else {
                    pomPathList.add(ff.pomPath(folderPath).substring(begin, last));
                }
            }else{
                System.out.println("Please upload the full file");
            }
        }


//        for(int i = 0;i < pomPathList.size();i++){
//            Thread mvnInst = new Thread(new mvnInstall());
//            mvnInstall.getPath(pomPathList.get(i));
//            mvnInst.start();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//            }
//        }

        for(int i = 0;i < pomPathList.size();i++){
            runJar(pomPathList.get(i));
        }
    }


    ObjectMetrics mt;
    public void metricsca() throws IOException {
            for(int a=0;a<25;a++){
            for(int b=0;b<20;b++) {
                try {
                    met[a][b].ObjectMetrics(a,b);
            } catch (NullPointerException e) {}
            }
        }
       ObjectMetrics.cal(matArr);

}}


