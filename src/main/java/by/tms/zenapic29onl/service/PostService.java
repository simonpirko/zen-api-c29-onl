package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Post;
import by.tms.zenapic29onl.entity.User;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post createPost(String title, String content, User author) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedDate(LocalDateTime.now());
        post.setUpdatedDate(LocalDateTime.now());
        post.setAuthor(author);
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedDate(LocalDateTime.now());
        return post;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

