error id: file://<WORKSPACE>/src/main/java/com/example/bookingsystem/service/UserService.java:com/example/bookingsystem/dto/UserResponse#
file://<WORKSPACE>/src/main/java/com/example/bookingsystem/service/UserService.java
empty definition using pc, found symbol in pc: com/example/bookingsystem/dto/UserResponse#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 92
uri: file://<WORKSPACE>/src/main/java/com/example/bookingsystem/service/UserService.java
text:
```scala
package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.UserRespons@@e;
import com.example.bookingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(User user) {
        // check if email is already used
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
             throw new RuntimeException("E-mail is already used for another account");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: com/example/bookingsystem/dto/UserResponse#