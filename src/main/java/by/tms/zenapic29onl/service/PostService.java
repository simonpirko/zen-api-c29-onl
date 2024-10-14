package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Post;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService implements CrudOperation<Post> {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);

    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);

    }

    @Override
    public Post update(Post post) {
        Post existingPost = postRepository.findById(post.getId()).orElseThrow(() -> new NotFoundException("Post not Found"));
        existingPost.setTitle(post.getTitle());
        existingPost.setContents(post.getContents());
        return postRepository.save(existingPost);

    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not Found"));
    }
}
