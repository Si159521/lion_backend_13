package day12.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class TextFileManager {
    private String filePath;

    public TextFileManager(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(List<String> lines) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for (String line : lines) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public List<String> readFromFile() throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line = bufferedReader.readLine();
        while (line != null) {
            lines.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return lines;
    }
}

class UserInputHandler {
    public List<String> getUserInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();
        System.out.println("텍스트를 입력하세요 (종료하려면 'exit' 입력):");

        while (true) {
            String input = reader.readLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            lines.add(input);
        }
        return lines;
    }
}

public class Application {
    public static void main(String[] args) {
        String filePath = "texts.txt";
        TextFileManager fileManager = new TextFileManager(filePath);
        UserInputHandler inputHandler = new UserInputHandler();

        try {
            List<String> userInput = inputHandler.getUserInput();
            fileManager.writeToFile(userInput);

            List<String> fileContent = fileManager.readFromFile();
            System.out.println("파일의 내용:");
            fileContent.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("오류가 발생했습니다: " + e.getMessage());
        }
    }
}
