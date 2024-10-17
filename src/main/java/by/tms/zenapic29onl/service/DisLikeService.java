package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.DisLike;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.DisLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class DisLikeService {
    private final DisLikeRepository disLikeRepository;

    public DisLikeService(DisLikeRepository disLikeRepository) {
        this.disLikeRepository = disLikeRepository;
    }

    public DisLike save(DisLike disLike) {
        return disLikeRepository.save(disLike);
    }

    public void delete(DisLike disLike) {
        disLikeRepository.delete(disLike);
    }

    public void delete(Long id) {
        disLikeRepository.deleteById(id);
    }

    public DisLike update(DisLike disLike) {
        return disLikeRepository.save(disLike);
    }

    public DisLike findById(Long id) {
        return disLikeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dislike not Found"));
    }

}