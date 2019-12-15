package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.InputDto;

import java.io.File;
import java.net.URL;
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

    private File getFileFromResources(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            System.out.println("Файл отсутсвует!\nБудет создан новый файл!");
            return new File(Paths.get("src", "main","resources").toString() + "/" + fileName);
        } else {
            return new File(resource.getFile());
        }
    }
}
