package com.bookmarkmanager.bookmarkmanager.controllers;

import com.bookmarkmanager.bookmarkmanager.domain.BookmarkDto;
import com.bookmarkmanager.bookmarkmanager.domain.BookmarkEntity;
import com.bookmarkmanager.bookmarkmanager.mappers.implementations.BookmarkMapper;
import com.bookmarkmanager.bookmarkmanager.services.interfaces.BookmarkService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final BookmarkMapper bookmarkMapper;

    public BookmarkController(BookmarkService bookmarkService, BookmarkMapper bookmarkMapper) {
        this.bookmarkService = bookmarkService;
        this.bookmarkMapper = bookmarkMapper;
    }

    @PostMapping(path = "/bookmarks")
    public ResponseEntity<BookmarkDto> createBookmark(@RequestBody BookmarkDto bookmarkDto) {
        BookmarkEntity bookmarkEntity = bookmarkMapper.mapFrom(bookmarkDto);

        BookmarkEntity savedBookmarkEntity = bookmarkService.save(bookmarkEntity);

        return new ResponseEntity<>(
                bookmarkMapper.mapTo(savedBookmarkEntity),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/bookmarks/{bookmarkId}")
    public ResponseEntity<BookmarkDto> getOneBookmarkById(@PathVariable("bookmarkId") Long bookmarkId) {
        BookmarkEntity bookmarkEntity = bookmarkService.findOne(bookmarkId);

        return new ResponseEntity<>(
                bookmarkMapper.mapTo(bookmarkEntity),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/bookmarks")
    public Page<BookmarkDto> getAllBookmarks(Pageable pageable) {
        Page<BookmarkEntity> allFoundBookmarks = bookmarkService.findAll(pageable);

        return allFoundBookmarks.map(bookmarkMapper::mapTo);
    }

    @PutMapping(path = "/bookmarks/{bookmarkId}")
    public ResponseEntity<BookmarkDto> fullUpdateBookmark(
            @PathVariable("bookmarkId") Long bookmarkId,
            @RequestBody BookmarkDto bookmarkDto
    ) {
        BookmarkEntity bookmarkEntity = bookmarkMapper.mapFrom(bookmarkDto);

        BookmarkEntity updatedBookmarkEntity = bookmarkService.fullUpdate(bookmarkId, bookmarkEntity);

        return new ResponseEntity<>(
                bookmarkMapper.mapTo(updatedBookmarkEntity),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/bookmarks/{bookmarkId}")
    public ResponseEntity<String> deleteBookmarkById(@PathVariable("bookmarkId") Long bookmarkId) {
        try {
            bookmarkService.deleteById(bookmarkId);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.ok("You have deleted, although there was nothing to delete error: " + e);
        }
        return ResponseEntity.ok("");
    }
}
