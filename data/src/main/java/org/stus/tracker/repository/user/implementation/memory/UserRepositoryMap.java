package org.stus.tracker.repository.user.implementation.memory;

import org.springframework.stereotype.Repository;
import org.stus.tracker.model.user.User;
import org.stus.tracker.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryMap implements UserRepository {

    private Map<Long, User> repository = new ConcurrentHashMap<>();

    private AtomicLong idGenerator = new AtomicLong();

    {
        this.add(new User("John", "Doe", "john@gmail.com", LocalDate.of(1995, 1, 12)));
        this.add(new User("Jane", "Doe", "jane@gmail.com", LocalDate.of(1991, 1, 12)));
    }

    @Override
    public User get(Long id) {
        return Optional.ofNullable(repository.get(id))
                .orElseThrow(() -> new IllegalArgumentException("No user for such id found"));
    }

    @Override
    public Collection<User> getAll() {
        return Collections.unmodifiableCollection(repository.values());
    }

    @Override
    public Long add(User entity) {
        if (Objects.isNull(entity) || Objects.nonNull(entity.getId()))
            throw new IllegalArgumentException("New entity must be provided");
        long nextId = idGenerator.getAndIncrement();
        entity.setId(nextId);
        repository.put(nextId, entity);
        return nextId;
    }

    @Override
    public User update(User entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId()) || !repository.containsKey(entity.getId()))
            throw new IllegalArgumentException("Existing entity must be provided");
        User previousState = repository.get(entity.getId());
        repository.put(entity.getId(), entity);
        return previousState;
    }

    @Override
    public Long remove(User entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId()) || !repository.containsKey(entity.getId()))
            throw new IllegalArgumentException("Existing entity must be provided");
        return repository.remove(entity.getId()).getId();
    }
}
