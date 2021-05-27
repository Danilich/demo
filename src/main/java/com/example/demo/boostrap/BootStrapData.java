package com.example.demo.boostrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
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

        Publisher dc = new Publisher("DC","45 Ocean Drive str . Gotham City");
        dc.setCity("Gotham");
        dc.setState("NY");
        dc.setName("DC");
        dc.setAddressLine1("Ocean Drive str. 34");


        publisherRepository.save(dc);
        System.out.println(publisherRepository.count());


        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Drivan Design","1234");
        eric.getBooks().add(ddd);

        ddd.setPublisher(dc);
        dc.getBooks().add(ddd);



        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(dc);

        Author rod = new Author("Rod","Jonson");
        Book noEJB = new Book("J2EE DEvelopment without EJB","88r23432049032");
        rod.getBooks().add(noEJB);

        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(dc);
        dc.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(dc);

        System.out.println("Started in Bootstrap");
        System.out.println("Number books " + bookRepository.count());

        System.out.println("Publisher Nums of Books "+dc.getBooks().size());




    }
}
