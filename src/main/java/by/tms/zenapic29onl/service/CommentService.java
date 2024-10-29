package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Comment;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = findById(id);
        commentRepository.delete(comment);
    }

    @Transactional
    public Optional<List<Comment>> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Transactional
    public Comment update(Comment comment) {
        Comment existingComment = findById(comment.getId());
        existingComment.setDescription(comment.getDescription());
        return commentRepository.save(existingComment);
    }

    @Transactional
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not Found"));
    }
}