package com.example.feedservice.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "files")
public class FileEntity extends BaseEntity {

    @Id
    @Column(name = "file_id")
    private String fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    private String fileName;

    @Lob
    private Double fileSize;

    private String filePath;

    private String fileExtension;

    /**
     * TODO :: 편의메서드 적용 해야함
     * */
}
