package org.example.learning_spring.iointeractionclasses;

import java.io.File;
import java.util.Objects;

public class FileCombiner {

    public static int combine(String path, String target, int totalFiles){
        File[] files = new File(path).listFiles();
        assert files != null;
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                totalFiles= combine(file.getPath(), target, totalFiles);
                System.out.println(file.getPath());
            } else{
                String fileName = String.format("frame_%03d.png", totalFiles);
                file.renameTo(new File(target +File.separator +fileName));
                totalFiles++;
            }
        }
        return totalFiles;
    }

    public static void main(String[] args) {
        int totalFiles =combine("C:\\Users\\Yasin\\Downloads\\diana","C:\\Users\\Yasin\\Downloads\\diana",0);
        System.out.println(totalFiles);
    }
}
