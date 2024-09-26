package com.movie_project.movie_Base.Repositories;

import com.movie_project.movie_Base.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CommentRepository extends JpaRepository<Comment, Long> {

}
