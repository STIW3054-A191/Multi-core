import java.io.File;
import java.io.IOException;

public class RecursiveSearch {
static int count =1;
static String resultPath,jarpath;
    public static void search(File dir,String suffix) throws IOException, InterruptedException {

        File [] sub = dir.listFiles();
        locateJar(dir,suffix);

        for(File folder : sub ){
       if(folder.isDirectory() )
            search(folder,suffix);
       if(dir.getAbsolutePath().contains("target")&&suffix.equalsIgnoreCase("jar")) {
         break;
       } }
    }

    static void locateJar(File dir,String suf) {
        File[] files = dir.listFiles();
        for (File file:files){
            String Find = file.getName();
            if (Find.endsWith(suf)&&Find.contains("jar")&& !Find.contains("original")&& dir.getAbsolutePath().contains("target")){
               // System.out.println(dir.getAbsolutePath()+"\\" + Find);
                resultPath=dir.getAbsolutePath()+"\\" + Find;
                jarpath = dir.getAbsolutePath();

                //System.out.println(count);
                count++;
                break;
            }
            if(Find.endsWith(suf)&&Find.contains("class") ){
                //System.out.println("CLASS : "+dir.getAbsolutePath());
                resultPath=dir.getAbsolutePath();
                count++;
                //System.out.println(count);
                break;
            }
        }
    }
}
