package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Post;
import java.util.List;


public interface PostService {
    Post createPost(Post post);
    Post updatePost(Long id, Post post);
    Post getPostById(Long id);
    List<Post> getAllPosts();
    void deletePost(Long id);
}