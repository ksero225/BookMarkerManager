package com.bookmarkmanager.bookmarkmanager.repositories;

import com.bookmarkmanager.bookmarkmanager.domain.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>,
        PagingAndSortingRepository<BookmarkEntity, Long> {
    boolean existsBookmarkEntityById(Long bookmarkId);
}
