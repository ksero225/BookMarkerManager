package com.bookmarkmanager.bookmarkmanager.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @URL
    private String url;

    @ElementCollection
    @CollectionTable(
            name = "bookmark_tags",
            joinColumns = @JoinColumn(name = "bookmark_id")
    )
    @Column(name = "tag")
    private List<String> tags;
}
