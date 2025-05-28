package ing.beribtur.accent.context;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRequest {
    //
    private String username;
    private String displayName;
    private String email;
    private boolean enabled;

    public static SpaceRequest anonymous() {
        //
        return SpaceRequest.builder()
                .username("anonymous")
                .displayName("Anonymous")
                .email("anonymous@mail.com")
                .enabled(true)
                .build();

    }
}
