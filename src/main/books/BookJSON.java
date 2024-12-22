package books;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


/*1. Используя библиотеку Jackson, напишите Java-программу, которая вычитывает JSON-файл(books.json)
     и сохранит данные в коллекцию Java.*/

public class BookJSON {
    public static void main(String[] args) {

            String filePath = "D:\\Java\\TMS_C32_Lesson_23\\books.json";

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                JsonNode rootNode = objectMapper.readTree(new File(filePath));

                if (rootNode.isArray()) {
                    rootNode.forEach(item -> {
                        System.out.println("Array item: " + item);
                    });
                }

            } catch (IOException e) {
                System.err.println("Error" + e.getMessage());
            }
    }
}

