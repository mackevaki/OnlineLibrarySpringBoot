package library.springboot.onlinelibraryspring.dao.impl;

import library.springboot.onlinelibraryspring.dao.GenreDao;
import library.springboot.onlinelibraryspring.models.Genre;
import library.springboot.onlinelibraryspring.repositories.GenreRepository;
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
public class GenreService implements GenreDao {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAll() {
       return genreRepository.findAll();
    }

    public List<Genre> getAll(Sort sort) {
        return genreRepository.findAll(sort);
    }

    @Override
    public Page<Genre> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return genreRepository.findAll(PageRequest.of(pageNumber, pageSize, sortDirection, sortField));
    }

    @Override
    public List<Genre> search(String... searchString) {
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0]);
    }

    @Override
    public Page<Genre> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0], PageRequest.of(pageNumber, pageSize, sortDirection, sortField));
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

    @Override
    public Genre get(long id) {
        Optional<Genre> bookmark = genreRepository.findById(id);
        return bookmark.orElse(null);
    }
}