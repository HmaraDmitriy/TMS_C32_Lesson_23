package books;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;

/*Используя любой парсер(DOM,SAX,StAX) распарсите данные в
  Java приложении и выведите в консоль информацию о книге с наибольшим количеством страниц.*/

public class BookStAX {
    public static void main(String[] args) {
        String xmlFilePath = "D:\\Java\\TMS_C32_Lesson_23\\books.xml";;

        String maxPagesBookTitle = "";
        String maxPagesBookAuthor = "";
        int maxPages = 0;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new File(xmlFilePath).toURI().toString(), new java.io.FileInputStream(xmlFilePath));

            String currentElement = "";
            String currentTitle = "";
            String currentAuthor = "";
            int currentPages = 0;

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    currentElement = reader.getLocalName();
                } else if (event == XMLStreamConstants.CHARACTERS) {
                    String text = reader.getText().trim();
                    if (!text.isEmpty()) {
                        switch (currentElement) {
                            case "title" -> currentTitle = text;
                            case "author" -> currentAuthor = text;
                            case "pages" -> currentPages = Integer.parseInt(text);
                        }
                    }
                } else if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("book")) {
                    if (currentPages > maxPages) {
                        maxPages = currentPages;
                        maxPagesBookTitle = currentTitle;
                        maxPagesBookAuthor = currentAuthor;
                    }
                }
            }

            System.out.println("Book:" + "Title: " + maxPagesBookTitle +
                               "Author: " + maxPagesBookAuthor + "Pages: " + maxPages);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
