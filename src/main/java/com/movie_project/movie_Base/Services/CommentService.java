package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.Entity.Comment;
import com.movie_project.movie_Base.Entity.User;

import java.util.Optional;

public interface CommentService {

     Comment addComment(Long movieId, Comment comment, User userId);
     Comment getMovieComments(Long movieId);
    Comment getCommentById(Long commentId);
    Comment editComment(Long commentId, Comment updatedComment);
    void deleteComment(Long commentId);


}
