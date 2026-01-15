error id: file://<WORKSPACE>/src/main/java/com/example/bookingsystem/entity/Room.java:_empty_/Column#
file://<WORKSPACE>/src/main/java/com/example/bookingsystem/entity/Room.java
empty definition using pc, found symbol in pc: _empty_/Column#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 360
uri: file://<WORKSPACE>/src/main/java/com/example/bookingsystem/entity/Room.java
text:
```scala
package com.example.bookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //UUID ?

    @Co@@lumn(nullable = false)
    private String name;
    
    private int capacity;
    private String location;
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Column#