package com.movie_project.movie_Base.Services.Impl;

import com.movie_project.movie_Base.Entity.Comment;
import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Repositories.CommentRepository;
import com.movie_project.movie_Base.Repositories.MovieRepository;
import com.movie_project.movie_Base.Services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;
    @Override
    public Comment addComment(Long movieId, Comment comment, User userId) {
        log.info("adding comment to movie: {}", movieId);
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        comment.setMovie(movie);
        comment.setUser(userId);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getComments(Long movieId) {
        log.info("getting comments for movie: {}", movieId);
        return commentRepository.findById(movieId).orElseThrow();
    }

    @Override
    public Comment getComment(Long commentId) {
        log.info("getting comment with id: {}", commentId);
        return commentRepository.findById(commentId).orElseThrow();
    }

    @Override
    public Comment editComment(Long commentId, Comment updatedComment) {
        log.info("updating comment with id: {}", commentId);
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.setComment(updatedComment.getComment());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        log.info("deleting comment with id: {}", commentId);
        commentRepository.deleteById(commentId);

    }
}
