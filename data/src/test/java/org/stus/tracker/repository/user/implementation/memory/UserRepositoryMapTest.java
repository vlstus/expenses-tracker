package org.stus.tracker.repository.user.implementation.memory;

import org.junit.jupiter.api.*;
import org.stus.tracker.GenericTest;
import org.stus.tracker.model.user.User;
import org.stus.tracker.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;

class UserRepositoryMapTest extends GenericTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryMap();
        List.of(new User("John", "Doe", "john@gmail.com", LocalDate.now()),
                new User("Jane", "Doe", "jane@gmail.com", LocalDate.now().minusDays(1)))
                .forEach(user -> userRepository.add(user));
    }


    @Test
    void getById() {
        final User john = userRepository.get(0L);
        final User jane = userRepository.get(1L);
        assertEquals("John", john.getFirstName());
        assertEquals("Jane", jane.getFirstName());
        assertThrows(IllegalArgumentException.class, () -> userRepository.get(42L));
    }

    @Test
    void add() {
        final User jim = new User("Jim", "Beam", "jim@gmail.com", LocalDate.now().minusMonths(1));
        final Long jimId = userRepository.add(jim);
        Assertions.assertThat(jimId)
                .isNotNull()
                .isNotNegative();
        final User jimFromRepository = userRepository.get(jimId);
        Assertions.assertThat(jimFromRepository)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(jim);
    }

    @Test
    void update() {
        final User john = userRepository.get(0L);
        final User updatedLocal = new User(john.getFirstName(), john.getLastName(), john.getEmail(), john.getRegistrationDate());
        updatedLocal.setId(john.getId());
        updatedLocal.setEmail("johnNewMail@gmail.com");
        final User previousJohnState = userRepository.update(updatedLocal);
        assertEquals("john@gmail.com", previousJohnState.getEmail());
        final User updatedJohnState = userRepository.get(0L);
        assertEquals("johnNewMail@gmail.com", updatedJohnState.getEmail());
    }

    @Test
    void remove() {
        final User john = new User("John", "Doe", "john@gmail.com", LocalDate.now());
        john.setId(0L);
        final Long previousId = userRepository.remove(john);
        assertEquals(previousId, 0L);
        assertThrows(IllegalArgumentException.class, () -> userRepository.get(0L));
    }
}