package books.book;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "books")
public class BookList {
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }

    public BookList(List<Book> books) {
        this.books = books;
    }

    @XmlElement(name = "book")
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
