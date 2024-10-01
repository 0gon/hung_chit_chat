package com.example.feedservice.repository;

import com.example.feedservice.entity.FileEntity;
import com.example.feedservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {

    void deleteAllByPost(PostEntity post);

}
