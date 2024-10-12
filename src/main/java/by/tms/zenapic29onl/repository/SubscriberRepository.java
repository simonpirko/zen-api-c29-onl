package by.tms.zenapic29onl.repository;

import by.tms.zenapic29onl.entity.Subscriber;
import by.tms.zenapic29onl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByUser(User user);
}
