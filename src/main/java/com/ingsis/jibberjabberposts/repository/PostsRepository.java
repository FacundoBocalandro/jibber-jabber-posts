package com.ingsis.jibberjabberposts.repository;

import com.ingsis.jibberjabberposts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
}
