package dng.springframework.spring5webapp.bootstrap;

import dng.springframework.spring5webapp.domain.Author;
import dng.springframework.spring5webapp.domain.Book;
import dng.springframework.spring5webapp.domain.Publisher;
import dng.springframework.spring5webapp.repositories.AuthorRepository;
import dng.springframework.spring5webapp.repositories.BookRepository;
import dng.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher johnPub = new Publisher();
        johnPub.setName("John");
        johnPub.setCity("Blagoevgrad");
        publisherRepository.save(johnPub);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(johnPub);
        //save into the db
        authorRepository.save(eric);
        bookRepository.save(ddd);

        johnPub.getBooks().add(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book javaFunds = new Book("Java Funds", "33214");
        rod.getBooks().add(javaFunds);
        javaFunds.getAuthors().add(rod);
        javaFunds.setPublisher(johnPub);

        johnPub.getBooks().add(javaFunds);


        //save into the db
        authorRepository.save(rod);
        bookRepository.save(javaFunds);





        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books in johnPub: " + johnPub.getBooks().size());


    }
}
