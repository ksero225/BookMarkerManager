package com.bookmarkmanager.bookmarkmanager.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @URL
    private String url;
    private List<String> tags;
}
