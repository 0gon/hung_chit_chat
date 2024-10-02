package com.example.feedservice.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Entity
@Getter
@Table(name = "files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileEntity extends BaseEntity {

    @Id
    @Column(name = "file_id")
    private String fileId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    private String fileName;

    @Lob
    private Long fileSize;

    private String filePath;


    @Builder
    public FileEntity(String fileId, String fileName, Long fileSize, String filePath) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    /**
     * TODO :: 편의메서드 적용 해야함
     * */

    public void setPost(PostEntity post) {
        this.post = post;
        post.addFile(this);
    }


    @Override
    public String getId() {
        return this.fileId;
    }
}
