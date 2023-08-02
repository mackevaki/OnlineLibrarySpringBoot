package library.springboot.onlinelibraryspring.controllers;

/*
import library.springboot.onlinelibraryspring.dao.impl.AuthorService;
*/
import library.springboot.onlinelibraryspring.dao.impl.AuthorService;
import library.springboot.onlinelibraryspring.models.Author;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping(path = "/authors")
public class AuthorsController {
    private final AuthorService authorService;

    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path = "/{id}", produces = "text/plain")
    public HttpEntity<String> getAuthor(@PathVariable(name = "id") long id) {
        Author author = authorService.get(id);
        return new HttpEntity<>(author.toString());
    }

}
