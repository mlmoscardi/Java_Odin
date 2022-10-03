package copyDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyDir {

    public void CopyDirectory(String src, String dst) throws IOException {
        
        Files.walk(Paths.get(src))
        .forEach(source -> {
            Path destination = Paths.get(dst, source.toString()
                .substring(src.length()));
            try {
                Files.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    } // CopyDirectory    
} // class
