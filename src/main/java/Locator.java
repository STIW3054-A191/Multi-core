import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Locator {

    public String pomPath(String path) {
        Locator finder = new Locator();
        List<String> filenameList = new ArrayList<String>();
        finder.findFiles("pom.xml" , path, filenameList);
        String pomPath="";
        for (String filename : filenameList) {
            pomPath = filename;
        }
        return pomPath;
    }


    public void findFiles(String filenameSuffix, String currentDirUsed,
       List<String> currentFilenameList) {
        File dir = new File(currentDirUsed);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                findFiles(filenameSuffix,file.getAbsolutePath(), currentFilenameList);
            } else {
                if (file.getAbsolutePath().endsWith(filenameSuffix)) {
                    currentFilenameList.add(file.getAbsolutePath());
                }
            }
        }
    }
}
