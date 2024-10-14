package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements CrudOperation<Comment> {


    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public void delete(Comment comment) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }
}
