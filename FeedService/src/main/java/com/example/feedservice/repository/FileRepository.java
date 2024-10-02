package com.example.feedservice.repository;

import com.example.feedservice.entity.FileEntity;
import com.example.feedservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {

    void deleteAllByPost(PostEntity post);

    List<FileEntity> findAllByPost(PostEntity post);

}
