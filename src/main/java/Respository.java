import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Respository implements Runnable{
private  CountDownLatch latch;
private String path;
    Respository(CountDownLatch latch,String path){
        this.latch = latch;
        this.path = path;
    }


    public synchronized void run() {
        Grabber gl = new Grabber();
        List<String> urlList = new ArrayList<String>();
        urlList.addAll(gl.getLinks());
        for (int i = 0; i < urlList.size(); i++) {
            try {
                String full_command;
                String command = "git clone {}";
                full_command = command.replace("{}", urlList.get(i));
                System.out.println("Clone : "+urlList.get(i));

                Process p = Runtime.getRuntime().exec(full_command, null, new File(path));
                p.waitFor();

            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Respository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        latch.countDown();

    }


//    class Task implements Callable<Integer> {
//        public Integer call() throws Exception {
//            System.out.println("The Main Thread Start");
//            Thread.sleep(3000);
//            int resNumber = 0;
//            Grabber gl = new Grabber();
//            for (int i = 0; i < gl.getLinks().size(); i++)
//                resNumber += 1;
//            return resNumber;
//        }
//    }

}