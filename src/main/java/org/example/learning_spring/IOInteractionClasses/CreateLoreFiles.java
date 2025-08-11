package org.example.learning_spring.IOInteractionClasses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MapperFeature;
import org.example.learning_spring.TableClasses.Wuwa.WuwaChar;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateLoreFiles {
    public static void createLoreFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        List<WuwaChar> allWuwaChars = mapper.readValue(new File("src/main/resources/JSONFolder/wuwaChars.json"), new TypeReference<>() {
        });
        for (WuwaChar wuwaChar : allWuwaChars) {
            File charFile = new File("src/main/resources/CharacterLore/" + wuwaChar.getName() + ".txt");
            boolean success = charFile.createNewFile();
        }
    }

    public static void main(String[] args) throws IOException {
        createLoreFile();
    }
}
