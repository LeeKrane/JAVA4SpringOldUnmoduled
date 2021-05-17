package labor14_num1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping(path="/books")
    public List<Book> getAllBooks () {
        return bookService.getBooks();
    }

    @GetMapping(path="/books/{pattern}")
    public List<Book> getBooksByPattern (@PathVariable String pattern) {
        return bookService.getBooksByPattern(pattern);
    }
}
