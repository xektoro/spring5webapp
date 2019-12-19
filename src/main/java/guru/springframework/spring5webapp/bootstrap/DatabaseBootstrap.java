package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// that class populates data in out database table

// ContextRefreshedEvent is the Event, we want to implement
@Component // it makes it a Spring Bean, so now it is wired up by the Spring Context
public class DatabaseBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    // on run time we are going to get an impelementation of the 2 repositories from Spring Data JPA
    public DatabaseBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Publisher publisher1 = new Publisher("Ivan Ivanov", "Mlakdost 2");
        Book ddd = new Book("Domain Driven Design", "1234", publisher1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(publisher1);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Publisher publisher2 = new Publisher("Asen", "Pleven");
        Book noEJB = new Book("J2EE Development without EJB", "555", publisher2);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(publisher2);
        bookRepository.save(noEJB);
    }
}
