package ru.yandex.practicum.ewm.user.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.ewm.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
