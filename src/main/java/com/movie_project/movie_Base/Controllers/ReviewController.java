package com.movie_project.movie_Base.Controllers;

import com.movie_project.movie_Base.Entity.Comment;
import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Services.CommentService;
import com.movie_project.movie_Base.Services.RatingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@Data
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
@RequestMapping("/api/Review")

public class ReviewController {

    private final CommentService commentService;
    private final RatingService ratingService;



    @GetMapping("/getMovieComments/{id}")
    public Comment getAllMovieCommentById(@PathVariable Long id) {
        log.info("Get all movieComments");
        if (id == null ){System.out.println("no comments on movie found");}
        return commentService.getMovieComments(id);
    }

    @GetMapping("/getAllComments/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        log.info("Get all Comments");
        if (id == null ){System.out.println("no comments found");}
        return commentService.getCommentById(id);
    }

    @PostMapping("/comment/add")
    public Comment addComment(Long movieId, @RequestBody Comment comment, User userId) {
        log.info("Added Comment");
        return commentService.addComment(movieId,comment,userId);

    }

    @DeleteMapping("/comment/delete")
    public void deleteComment(Long commentId) {
        log.info("Deleting Comment: {}", commentId);
        commentService.deleteComment(commentId);
    }
    @PostMapping("/comment/edit")
    public ResponseEntity<Comment> editComment(@RequestBody Comment comment, User userId) {
        Comment updatedComment = commentService.getCommentById(comment.getId());
        updatedComment.setComment(comment.getComment());

        return ResponseEntity.ok().body(updatedComment);

    }







}
