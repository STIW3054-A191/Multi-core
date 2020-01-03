import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String []args) throws InterruptedException, IOException {

        int core = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(core);
//     Respository r1 = new Respository();
//     service.submit(r1);

//     Compiler cp = new Compiler();
//        cp.compile();

        ControlUnit r = new ControlUnit();
         r.runProgram();
r.metricsca();


    }



}
