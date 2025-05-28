package ing.beribtur.accent.context;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;


@NoArgsConstructor
public class SpaceContextBuilder {
    //
    public static final String USERNAME = "username";

    public void buildRequest(HttpServletRequest request) {
        //
        SpaceRequest spaceRequest = createSpaceRequest(request);

        if (spaceRequest == null) {
            System.out.println("Cannot create space request");
        } else {
            SpaceContext.set(spaceRequest);
        }
    }

    private SpaceRequest createSpaceRequest(HttpServletRequest request) {
        //
        String username = getUsername(request);
        if (username == null || username.isEmpty()) {
            return null;
        }
        return SpaceRequest.builder().username(username).build();
    }

    private String getUsername(HttpServletRequest request) {
        //
        if (StringUtils.hasText(request.getHeader(SpaceContextBuilder.USERNAME))) {
            return request.getHeader(SpaceContextBuilder.USERNAME);
        }
        return SpaceRequest.anonymous().getUsername();
    }
}
