package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Subscriber;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.SubscriberRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService implements CrudOperation<Subscriber> {
    private final SubscriberRepository subscriberRepository;

    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Subscriber save(Subscriber subscriber) {

//        if (subscriberRepository.findByUser(subscriber.getUser()).isPresent()) {
//            throw new IllegalArgumentException("User is already Subscribed");
//        }

        return subscriberRepository.save(subscriber);
    }

    @Override
    public void delete(Subscriber subscriber) {
        subscriberRepository.delete(subscriber);
    }

    @Override
    public void delete(Long id) {
        subscriberRepository.deleteById(id);
    }

    @Override
    public Subscriber update(Subscriber subscriber) {
//        Subscriber existingSubscriber = subscriberRepository.findById(subscriber.getId())
//                .orElseThrow(() -> new NotFoundException("Subscriber not Found"));
//
//        existingSubscriber.setUser(subscriber.getUser());

//        return subscriberRepository.save(existingSubscriber);

        return subscriberRepository.save(subscriber);
    }

    @Override
    public Subscriber findById(Long id) {
        return subscriberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subscriber not Found"));
    }
}