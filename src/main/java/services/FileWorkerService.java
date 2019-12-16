package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.InputDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWorkerService {
    public InputDto getInputDto(String fileName){
        File jsonFile = getFileFromResources(fileName);
        return getInputDtoFromJson(jsonFile);
    }

    private InputDto getInputDtoFromJson(File jsonFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonFile, InputDto.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new InputDto();
    }

    public boolean saveExpression(String str, String fileName) {
        File file = getFileFromResources(fileName);
        try(FileWriter fileWriter = new FileWriter(file.getPath(), false)) {
            fileWriter.write(String.format("`%s`", str));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    private File getFileFromResources(String fileName){
        return new File(Paths.get("src", "main","resources").toString() + "/" + fileName);
    }
}
