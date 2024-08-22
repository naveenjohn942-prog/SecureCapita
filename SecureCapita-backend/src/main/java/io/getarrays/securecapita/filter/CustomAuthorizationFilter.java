package io.getarrays.securecapita.filter;

import io.getarrays.securecapita.provider.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static io.getarrays.securecapita.utils.ExceptionUtils.processError;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private static final String[] PUBLIC_ROUTES = {"/user/login", "/user/register", "/user/verify/code", "/user/refresh/token","/user/image"};
    private final TokenProvider tokenProvider;
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request);
            Long userId = getUserId(request);
            if(tokenProvider.isTokenValid(userId, token)){
                List<GrantedAuthority> authorities = tokenProvider.getAuthorities(token);
                log.info(authorities.toString() + "came here" + "4");
                Authentication authentication = tokenProvider.getAuthentication(userId, authorities, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        }
        catch (Exception e){
            log.error("{}coming from here ?", e.getMessage());
            processError(request, response, e);
        }
    }

    private String getToken(HttpServletRequest request) {
        try{
            return request.getHeader(AUTHORIZATION)
                    .startsWith(TOKEN_PREFIX) ? request.getHeader(AUTHORIZATION).replace(TOKEN_PREFIX, EMPTY) : "";
        }
        catch (Exception e){
            log.error(e.getMessage());
            return "";
        }

    }

    private Long getUserId(HttpServletRequest request) {
        return  tokenProvider.getSubject(getToken(request), request);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getHeader(AUTHORIZATION) == null || !request.getHeader(AUTHORIZATION).startsWith(TOKEN_PREFIX)
                || request.getMethod().equalsIgnoreCase("OPTIONS") || asList(PUBLIC_ROUTES).contains(request.getRequestURI());
    }

}
