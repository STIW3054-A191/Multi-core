import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String []args) throws InterruptedException, IOException {
        ProcessTime.start();
        int core = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(core);
        Scanner scan = new Scanner(System.in);
        System.out.println("Please set Directory : ");
        String gtpath = scan.nextLine();

        CountDownLatch buildFolder = new CountDownLatch(1);
        Thread buildFol = new Thread(new MainDirectory(gtpath,buildFolder));
        buildFol.start();
        buildFolder.await();

        System.out.println("Building Download Folder...");
        System.out.println("Cloning...");
        Grabber co = new Grabber();

        CountDownLatch repoCount = new CountDownLatch(1);

        Thread threadCloneRepo = new Thread(new Respository(repoCount,MainDirectory.getMainPath()));
        threadCloneRepo.start();
        repoCount.await();
        System.out.println("Cloning Completed....");
        System.out.println("Saved files into : "+MainDirectory.getMainPath());


        System.out.println("Compiling process....");
        CountDownLatch comCount = new CountDownLatch(1);
        Thread comThread = new Thread(new Compiler(comCount,MainDirectory.getMainPath()));
        comThread.start();
        comCount.await();
        System.out.println("Compiling is Done....");

        CountDownLatch runLatch = new CountDownLatch(1);
         ControlUnit r = new ControlUnit(runLatch,MainDirectory.getMainPath());
         Thread runJar = new Thread(r);
         runJar.start();
         runLatch.await();

         System.out.println("Printing CKJM metrics & Exporting to Excel...");
         r.metricsca();
         System.out.println("Done Extracting to Excel !");
         ProcessTime.end();


    }
}
