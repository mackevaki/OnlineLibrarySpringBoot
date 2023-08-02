package library.springboot.onlinelibraryspring.dao.impl;

import library.springboot.onlinelibraryspring.dao.BookDao;
import library.springboot.onlinelibraryspring.models.Book;
import library.springboot.onlinelibraryspring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService implements BookDao {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<Book> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return bookRepository.findAllWithoutContent(PageRequest.of(pageNumber, pageSize, sortDirection, sortField));
    }

    @Override
    public List<Book> search(String... searchString) {
        return null;
    }

    @Override
    public Page<Book> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        // чтобы название метода не было слишком длинным - можно использовать @Query c HQL (если больше 2-х переменных)
        return bookRepository.findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCaseOrderByName(searchString[0], searchString[0], PageRequest.of(pageNumber, pageSize, sortDirection, sortField));
    }

    @Override
    @Transactional
    public Book save(Book book) {
        // отдельно сохраняем данные книги
        bookRepository.save(book);

        if (book.getContent()!=null) {
            // отдельно сохраняем контент
            bookRepository.updateContent(book.getContent(), book.getId());
        }

        return book;
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book get(long id) {
        Optional<Book> bookmark = bookRepository.findById(id);
        return bookmark.orElse(null);
    }

    @Override
    public byte[] getContent(long id) {
        return bookRepository.getContent(id);
    }

    public List<Book> findTopBooks(int limit) {
        return bookRepository.findTopBooks(PageRequest.of(0, limit, Sort.Direction.DESC, "viewCount"));
    }

    @Override
    public Page<Book> findByGenre(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId) {
        return bookRepository.findByGenre(genreId, PageRequest.of(pageNumber, pageSize, sortDirection, sortField));
    }

    @Override
    @Transactional
    public void updateViewCount(long viewCount, long id) {
        bookRepository.updateViewCount(viewCount, id);
    }

    @Override
    @Transactional
    public void updateRating(long totalRating, long totalVoteCount, int avgRating, long id) {
        bookRepository.updateRating(totalRating, totalVoteCount, avgRating, id);
    }
}