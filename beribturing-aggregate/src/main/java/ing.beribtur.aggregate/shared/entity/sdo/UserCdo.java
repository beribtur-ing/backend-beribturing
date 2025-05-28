package ing.beribtur.aggregate.shared.entity.sdo;


import ing.beribtur.aggregate.shared.entity.vo.Address;
import ing.beribtur.aggregate.shared.entity.vo.UserRole;

import java.time.LocalDateTime;

public class UserCdo {
    //
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

