package com.ingsis.jibberjabberposts.repository;

import com.ingsis.jibberjabberposts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
}
