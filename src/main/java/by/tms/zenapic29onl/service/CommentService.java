package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Comment;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements CrudOperation<Comment> {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void delete(Long id) {
        Comment comment = findById(id);
        commentRepository.delete(comment);
    }

    @Override
    public Comment update(Comment comment) {
//        Comment existingComment = findById(comment.getId());
//        existingComment.setDescription(comment.getDescription());
//        return commentRepository.save(existingComment);

        return commentRepository.save(comment);

    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not Found"));
    }
}