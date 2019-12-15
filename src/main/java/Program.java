import dto.InputDto;
import services.FileWorkerService;

public class Program {
    public static void main(final String[] args) {
        FileWorkerService fileService = new FileWorkerService();
        InputDto inputDto = fileService.getInputDto(args[0]);

        System.out.println(inputDto);
    }
}
