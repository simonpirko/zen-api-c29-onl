package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Like;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like save(Like like) {
        return likeRepository.save(like);
    }

    public void delete(Like like) {
        likeRepository.delete(like);
    }

    public void delete(Long id) {
        likeRepository.deleteById(id);
    }

    public Like update(Like like) {
        return likeRepository.save(like);
    }

    public Like findById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Like not Found"));
    }

}