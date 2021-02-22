package org.stus.tracker.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.stus.tracker.model.user.User;
import org.stus.tracker.repository.user.UserRepository;
import org.stus.tracker.service.GenericService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends GenericService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return List.copyOf(userRepository.getAll());
    }

}
