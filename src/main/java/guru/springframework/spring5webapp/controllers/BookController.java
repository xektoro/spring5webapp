package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Spring is going to detect this as a Spring Bean and create the class for us
// and wire it into the Spring context
@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Model - By pass as attribute the Model object as an interface,
    // so Spring will pass in its actual implementation at runtime, when this controller method is invoked
    @RequestMapping("/books")
    public String getBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());

        // associate this with a view called books
        return "books";
    }
}
