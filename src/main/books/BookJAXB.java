package books;

import books.book.Book;
import books.book.BookList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookJAXB {
    public static void main(String[] args) {
        String jsonFilePath = "D:\\Java\\TMS_C32_Lesson_23\\books.json";
        String xmlFilePath = "D:\\Java\\TMS_C32_Lesson_23\\books.xml";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));
            List<Book> books = new ArrayList<>();

            if (rootNode.isArray()) {
                rootNode.forEach(item -> {
                    String title = item.has("title") ? item.get("title").asText() : "Unknown Title";
                    String author = item.has("author") ? item.get("author").asText() : "Unknown Author";
                    int year = item.has("year") ? item.get("year").asInt() : 0;
                    int pages = item.has("pages") ? item.get("pages").asInt() : 0;
                    String genre = item.has("genre") ? item.get("genre").asText() : "Unknown Genre";

                    books.add(new Book(title, author, year, pages, genre));
                });
            }

            BookList bookList = new BookList(books);
            saveToXml(bookList, xmlFilePath);
            System.out.println("XML success: " + xmlFilePath);

        } catch (IOException | JAXBException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void saveToXml(BookList bookList, String xmlFilePath) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(BookList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(bookList, new File(xmlFilePath));
    }
}
