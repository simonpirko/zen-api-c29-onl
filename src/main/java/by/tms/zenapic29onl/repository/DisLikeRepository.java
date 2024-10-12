package by.tms.zenapic29onl.repository;

import by.tms.zenapic29onl.entity.DisLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisLikeRepository extends JpaRepository<DisLike, Long> {
    Optional<DisLike> findByUserIdAndPostId(long userId, long postId);
}
