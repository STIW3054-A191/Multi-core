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

                Process p = Runtime.getRuntime().exec(full_command, null, new File(path));
                p.waitFor();
//                try {
//                   // Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Respository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        latch.countDown();

    }

    public void Clone() throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();
        try {
            Thread.sleep(1000);
            //Respository a = new Respository();
            //a.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The main thread is performing tasks");

        try {
            System.out.println("There are " + result.get() + " files need to be downloaded for this task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("All repositories have been successfully cloned ");
    }

    class Task implements Callable<Integer> {
        public Integer call() throws Exception {
            System.out.println("The Main Thread Start");
            Thread.sleep(3000);
            String mkDirectoryPath = "D:\\myData";
            //Respository cr = new Respository();
//            if (cr.mkDirectory(mkDirectoryPath)) {
//                System.out.println(mkDirectoryPath + " The Direcory have benn sucessfully created");
//            } else {
//                System.out.println(mkDirectoryPath + " The Directory created failed!The Directory have been already existed");
//            }
            int resNumber = 0;
            Grabber gl = new Grabber();
            for (int i = 0; i < gl.getLinks().size(); i++)
                resNumber += 1;
            return resNumber;
        }


    }

}