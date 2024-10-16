package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Post;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);

    }

    public void delete(Long id) {
        postRepository.deleteById(id);

    }

    public Post update(Post post) {
        return postRepository.save(post);

    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not Found"));
    }
}
