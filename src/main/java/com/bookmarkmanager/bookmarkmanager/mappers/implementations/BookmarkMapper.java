package com.bookmarkmanager.bookmarkmanager.mappers.implementations;

import com.bookmarkmanager.bookmarkmanager.domain.BookmarkDto;
import com.bookmarkmanager.bookmarkmanager.domain.BookmarkEntity;
import com.bookmarkmanager.bookmarkmanager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper implements Mapper<BookmarkEntity, BookmarkDto> {
    private final ModelMapper modelMapper;

    public BookmarkMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookmarkDto mapTo(BookmarkEntity bookmarkEntity) {
        return modelMapper.map(bookmarkEntity, BookmarkDto.class);
    }

    @Override
    public BookmarkEntity mapFrom(BookmarkDto bookmarkDto) {
        return modelMapper.map(bookmarkDto, BookmarkEntity.class);
    }
}
