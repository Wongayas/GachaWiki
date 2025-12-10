package org.example.learning_spring.iointeractionclasses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MovingFolders {

    public static void moveFolders(String path){
        File targetDir = new File(path,"Characters");
        File[] folders = new File(path).listFiles();
        assert folders != null;
        try{
            for(File folder : folders){
                if(folder.getName().equals("Animations") || folder.getName().equals("Characters")){
                    continue;
                }
                Files.move(folder.toPath(), new File(targetDir,folder.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        moveFolders("src/main/resources/static/GachaGameImages/BrownDust2");
    }
}
