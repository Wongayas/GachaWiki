package org.example.learning_spring.iointeractionclasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import org.example.learning_spring.dto.WuwaCharLoreDTO;
import org.example.learning_spring.entity.Wuwa.WuwaCharLore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVToJSON {

    public static void converter(String path) {
        File file = new File(path);
        try{
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            File JSONLore = new File("src/main/resources/JSONFolder/LoreJSON.json");
            if (!JSONLore.exists()) {
                JSONLore.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new  CSVReader(fileReader);
            List<WuwaCharLoreDTO> wuwaCharLoreDTOList = new ArrayList<>();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                WuwaCharLoreDTO wuwaCharLoreDTO = new WuwaCharLoreDTO();
                wuwaCharLoreDTO.setWuwaCharLoreObject(new WuwaCharLore(line[1], "src/main/resources/CharacterLore/"+line[0]+".txt"));
                wuwaCharLoreDTO.setName(line[0]);
                wuwaCharLoreDTOList.add(wuwaCharLoreDTO);
            }
            try(FileWriter fileWriter = new FileWriter("src/main/resources/JSONFolder/LoreJSON.json");){
                gson.toJson(wuwaCharLoreDTOList,fileWriter);
            }catch (IOException e){
                e.printStackTrace();
            }
        } catch (Exception IOException){
            IOException.printStackTrace();
        }
    }

    public static  void main(String[] args) {
        converter("src/main/resources/CSV/CharLore.csv");
    }
}
