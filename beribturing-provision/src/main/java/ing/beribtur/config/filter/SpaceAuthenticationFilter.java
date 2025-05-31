package ing.beribtur.config.filter;


import ing.beribtur.accent.context.SpaceContextBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(force = true)
public class SpaceAuthenticationFilter {
    //
    private final SpaceContextBuilder requestBuilder;


    public SpaceAuthenticationFilter(SpaceContextBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        this.requestBuilder.buildRequest(servletRequest);
    }
}
