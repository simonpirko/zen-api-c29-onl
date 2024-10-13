package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Post;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not Found"));
        existingPost.setTitle(post.getTitle());
        existingPost.setContents(post.getContents());
        return postRepository.save(existingPost);
    }

    @Override
    public Post getPostById(Long id) {
        return (Post) postRepository.findAllByUserId(id).orElseThrow(() -> new NotFoundException("Post not Found"));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not Found"));
        postRepository.delete(post);

    }
}
