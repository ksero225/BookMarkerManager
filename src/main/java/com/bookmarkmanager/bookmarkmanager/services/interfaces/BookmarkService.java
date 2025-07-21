package com.bookmarkmanager.bookmarkmanager.services.interfaces;

import com.bookmarkmanager.bookmarkmanager.domain.BookmarkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkService {
    BookmarkEntity findOne(Long bookmarkId);

    Page<BookmarkEntity> findAll(Pageable pageable);

    BookmarkEntity save(BookmarkEntity bookmarkEntity);

    BookmarkEntity fullUpdate(Long bookmarkId, BookmarkEntity bookmarkEntity);

    void deleteById(Long bookmarkId);
}
