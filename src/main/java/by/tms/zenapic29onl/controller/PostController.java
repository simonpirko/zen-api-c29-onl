package by.tms.zenapic29onl.controller;

import by.tms.zenapic29onl.entity.Post;
import by.tms.zenapic29onl.entity.User;
import by.tms.zenapic29onl.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation()
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.findAllPost();
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Создать новый пост")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пост успешно создан"),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса")
    })
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam String title, @RequestParam String content) {
        User author = getAuthenticatedUser();
        Post post = postService.save(new Post());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @Operation(summary = "Редактировать пост")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Пост не найден")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        Post updatedPost = postService.update(new Post());
        return ResponseEntity.ok(updatedPost);
    }

    @Operation(summary = "Удалить пост")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пост успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пост не найден")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private User getAuthenticatedUser() {
        return new User();
    }
}