package by.tms.zenapic29onl.controller;

import by.tms.zenapic29onl.entity.Post;
import by.tms.zenapic29onl.entity.User;
import by.tms.zenapic29onl.service.PostService;
import by.tms.zenapic29onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, @AuthenticationPrincipal User user) {
        post.setUser(user);
        postService.save(post);
        user.getPosts().add(post);

        userService.save(user);

        return ResponseEntity.ok(post);
    }
}
