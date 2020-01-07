import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Compiler {

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
    public void CompileProgram(String path) throws InterruptedException{

        //try {
            System.out.println("--"+path);
            String matricNum = path.substring(12, 18);
            //String Command = "cmd /c   cd "+path+"&& mvn compile > D:\\myData\\log\\"+matricNum+".log.txt";
            //Process a = Runtime.getRuntime().exec(Command);
            //Creating LOG FILE USING COMMAND
           // a.waitFor();
           //a.destroy();
       // } catch (IOException ex) {
         //   ex.printStackTrace();
           // Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    public void compile(){
        String path = "D:\\myData";
        List<String> pomPathList=new ArrayList<String>();
        for(int i =0;i < getFolder(path).size();i++){
            String folderPath = getFolder(path).get(i);
            Locator ff = new Locator();
            ff.pomPath(folderPath);
            System.out.println( "This is what "+ff.pomPath(folderPath));
            if(!"".equals(ff.pomPath(folderPath))){
                int begin=ff.pomPath(folderPath).indexOf("D:");
                int last=ff.pomPath(folderPath).indexOf("\\pom");
                if(begin == -1 || last == -1){
                 System.out.println("NOT INDEX");
                }else {
                    System.out.println(begin + " FIRST LAST" + last);
                     pomPathList.add(ff.pomPath(folderPath).substring(begin,last));
                }
            }else{
                System.out.println("Please upload the full file");
            }
        }


        for(int i = 0;i < pomPathList.size();i++){
            System.out.println(pomPathList.get(i) +"This is Compiler POM PATH");
            try {
                CompileProgram(pomPathList.get(i));
            } catch (InterruptedException e) {
                System.out.println("Ignore this Error");
            }
        }
    }
}
