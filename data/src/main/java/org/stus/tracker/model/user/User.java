package org.stus.tracker.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.stus.tracker.model.AbstractBaseEntity;

import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter @AllArgsConstructor
public class User extends AbstractBaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registrationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
