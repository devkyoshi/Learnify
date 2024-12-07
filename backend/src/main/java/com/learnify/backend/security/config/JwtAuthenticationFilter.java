package com.learnify.backend.security.config;

import com.learnify.backend.security.service.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    public void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain ) throws IOException, ServletException
    {

        final String authorizationHeader = request.getHeader( "Authorization" );

        String path = request.getRequestURI();
        if(path.contains("/user/login") || path.contains("/user/register")) {
            filterChain.doFilter( request, response ); //Skip the filter
            log.trace("Skipping JWT Authentication for path: {}, request: {}", path, request);
            return;
        }

        final String jwtToken = extractJwtFromCookies(request);

        if(jwtToken == null) {
            log.warn("JWT token not found in cookies");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing JWT token");
            return;
        }

        String username = jwtTokenProvider.extractUsername(jwtToken);
        log.info("User logging in: {}", username);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            //validate the token
            if(!jwtTokenProvider.isTokenValid(jwtToken,userDetails)){
                log.error("Invalid or expired JWT Token for user: {}}" , username);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Invalid or expired JWT Token");
                return;
            }

            final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            // Set the authentication details
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set the authentication in the context
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("An error occurred while processing the request: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Internal server error");
        }

    }

    // Method to extract JWT token from cookies
    private String extractJwtFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue(); // Return the JWT token
                }
            }
        }
        return null; // No JWT token found in cookies
    }
}





//
//package com.learnify.backend.security.config;
//
//import com.learnify.backend.security.service.JwtTokenProvider;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.security.sasl.AuthenticationException;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    public void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain ) throws IOException, ServletException
//    {
//
//        final String authorizationHeader = request.getHeader( "Authorization" );
//        final String jwtToken;
//        final String username;
//
//        String path = request.getRequestURI();
//        if(path.contains("/user/login") || path.contains("/user/register")) {
//            filterChain.doFilter( request, response ); //Skip the filter
//            log.trace("String JWT Authentication for path: {}, request: {}", path, request);
//            return;
//        }
//
//        if (authorizationHeader == null || authorizationHeader.isEmpty()){
//            throw new AuthenticationCredentialsNotFoundException("Authorization header is missing");
//        }
//
//        if(!authorizationHeader.startsWith("Bearer ")){
//            throw new AuthenticationCredentialsNotFoundException("Invalid Authorization header");
//        }
//
//        jwtToken = authorizationHeader.substring(7);
//        username = jwtTokenProvider.extractUsername(jwtToken);
//        log.info("User logging in: {}", username);
//
//        //Check if the user is already authenticated
//        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            //Validate the token
//            if(jwtTokenProvider.isTokenValid(jwtToken, userDetails)){
//                final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//
//                //Set the authentication details
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                //Set the authentication in the context
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        //Continue the filter execution
//        try {
//            filterChain.doFilter(request, response);
//        } catch (AuthenticationException e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Unauthorized" + e.getMessage());
//        }
//
//    }
//}
//
