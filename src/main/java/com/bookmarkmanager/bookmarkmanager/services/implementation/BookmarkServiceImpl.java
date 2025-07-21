package com.bookmarkmanager.bookmarkmanager.services.implementation;

import com.bookmarkmanager.bookmarkmanager.domain.BookmarkEntity;
import com.bookmarkmanager.bookmarkmanager.repositories.BookmarkRepository;
import com.bookmarkmanager.bookmarkmanager.services.interfaces.BookmarkService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public BookmarkEntity save(BookmarkEntity bookmarkEntity) {
        return bookmarkRepository.save(bookmarkEntity);
    }

    @Override
    public BookmarkEntity findOne(Long bookmarkId) {
        Optional<BookmarkEntity> foundBookMark = bookmarkRepository.findById(bookmarkId);

        if (foundBookMark.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bookmark not found");
        }

        return foundBookMark.get();
    }

    @Override
    public Page<BookmarkEntity> findAll(Pageable pageable) {
        return bookmarkRepository.findAll(pageable);
    }

    @Override
    public BookmarkEntity fullUpdate(Long bookmarkId, BookmarkEntity bookmarkEntity) {
        findOne(bookmarkId);

        return bookmarkRepository.save(bookmarkEntity);
    }

    @Override
    public void deleteById(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }
}
