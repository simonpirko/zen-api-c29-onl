package by.tms.zenapic29onl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POSTS")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @CreatedDate
    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private long numberOfReads;

    @NotNull
    private PostType postType;

    @NotNull
    private String contents;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Like> likes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<DisLike> dislikes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Category> categories;
}
