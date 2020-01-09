import java.io.FileNotFoundException;
import java.util.List;

//    WMC: Weighted methods per class
//    DIT: Depth of Inheritance Tree
//    NOC: Number of Children
//    CBO: Coupling between object classes
//    RFC: Response for a Class
//    LCOM: Lack of cohesion in methods
//    Ca: Afferent coupling (not a C&K metric)
//    NPM: Number of Public Methods for a class (not a C&K metric)
public class ObjectMetrics {
String line;
int[][][] metric = new int[26][20][10];
static int[][] total = new int[26][10];
String matric1;

public ObjectMetrics(String mat,String getLine){
  line=getLine;
  matric1 =mat;
}


  public int ObjectMetrics(int ar1,int ar2) {
    int result = 0;
    int j = 0;
    String[] split = new String[10];
    for (int i = 0; i < split.length; i++) {
      split = line.split(" ");
      if (split[i].matches("\\d+")) {
        result = Integer.parseInt(split[i]);
        j++;
        metric[ar1][ar2][i]= result;
        if(ar2 != 0) {
          total[ar1][i] = total[ar1][i] + metric[ar1][ar2][i] + metric[ar1][ar2 - 1][i];
        }else{
          total[ar1][i] = metric[ar1][ar2][i];
        }
        try {}catch(NullPointerException e){} //SKIP THE NULL VALUES
        }
    }
    return result;
  }
  static String[] arr = new String[25];

public static void cal(List<String> matric) throws FileNotFoundException {
String mat=null;

     for(int i=0;i<25;i++){
      mat=matric.get(i);

      int[] bitar = new int[10];
      int a=0;
    for(int u=0;u<10;u++){
      if(u !=0 && u !=9)
      bitar[a] = total[i][u];
      a++;
    }
    System.out.println("I : "+i);
      arr[i] = bitar[1]+ " "+ bitar[2]+"  "+bitar[3]+"  "+bitar[4]+"  "+bitar[5]+"  "+bitar[6]+"  "+bitar[7]+"  "+bitar[8]+" ";
      int h=i+1;
      //Start Excel Extractor
      ExcelExtractor exc = new ExcelExtractor(mat,bitar[1],bitar[2],bitar[3],bitar[4],bitar[5],bitar[6],bitar[7],bitar[8],h);
        exc.write();
      exc.printthis1();
    bitar = null; a =0;
  }
// for(int i=0;i<25;i++){
//    System.out.println("Checking for " +i + " : " + arr[i]);
// }

}


}



