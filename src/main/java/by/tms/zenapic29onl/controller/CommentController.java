package by.tms.zenapic29onl.controller;

import by.tms.zenapic29onl.entity.Comment;
import by.tms.zenapic29onl.entity.Post;
import by.tms.zenapic29onl.service.CommentService;
import by.tms.zenapic29onl.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class CommentController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> allComment(@PathVariable Long postId) {
        List<Comment> comments = commentService.findAllByPostId(postId);
        if (!comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        } else return ResponseEntity.notFound().build();
    }
    @PostMapping("/{postId}/comment")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        Post post = postService.findById(postId);
        comment.setPost(post);
        Comment newComment = commentService.save(comment);
        return ResponseEntity.ok(newComment);
    }
    @DeleteMapping("/{postId}/comment/remove")
    public ResponseEntity<?> removeComment(@RequestBody Comment comment) {
        commentService.delete(comment);
        return ResponseEntity.ok("Comment removed");
    }

    @PutMapping("/{postId}/comment/update")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        commentService.update(comment);
        return ResponseEntity.ok("Comment updated successfully");
    }
}
