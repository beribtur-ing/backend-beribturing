package ing.beribtur.aggregate.shared.entity;


import ing.beribtur.aggregate.shared.entity.vo.Address;
import ing.beribtur.aggregate.shared.entity.vo.UserRole;

import java.time.LocalDateTime;

public class User {
    //
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private String phoneNumber;
    private Address address;
    private UserRole role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

