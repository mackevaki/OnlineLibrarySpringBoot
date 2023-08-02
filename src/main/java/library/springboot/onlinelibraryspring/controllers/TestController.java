package library.springboot.onlinelibraryspring.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    @GetMapping
    public HttpEntity<String> test() {
        return new HttpEntity<>("test 1111111");
    }
/*    @RequestMapping(value = "", method = RequestMethod.GET)
    public String baseUrlRedirect(HttpServletRequest request, HttpServletResponse httpServletResponse) {

        Page<Author> authors = authorService.getAll(0, 10, "fio", Sort.Direction.DESC);

        Author a = new Author();
        a.setFio("тестовый автор");
        a.setBirthday(new Date(1454284800000L));


        Author newAuthor = authorService.save(a);

        return "ok";
    }*/
}
