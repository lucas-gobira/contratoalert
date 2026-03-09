package com.contratoAlert.ContratoAlertAplication.config;

import com.contratoAlert.ContratoAlertAplication.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint authEntryPoint;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService, JwtAuthenticationEntryPoint authEntryPoint
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
         try{

            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                try {
                    String token = authHeader.substring(7);
                    String username = jwtService.extractUsername(token);

                    if (username != null &&
                            SecurityContextHolder.getContext().getAuthentication() == null &&
                            jwtService.isTokenValid(token)) {

                        UserDetails userDetails =
                                userDetailsService.loadUserByUsername(username);

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );

                        SecurityContext context =
                                SecurityContextHolder.createEmptyContext();

                        context.setAuthentication(authToken);
                        SecurityContextHolder.setContext(context);

                        AuthContext.set(
                                new UsuarioAuth(
                                        jwtService.extractUserId(token),
                                        jwtService.extractEmpresaId(token),
                                        username,
                                        jwtService.extractRole(token)
                                )
                        );
                    }

                } catch (JwtException | IllegalArgumentException e) {
                    authEntryPoint.commence(
                            request,
                            response,
                            new BadCredentialsException("Token inválido", e)
                    );
                }
            }

            filterChain.doFilter(request, response);

         }finally {
             AuthContext.clear();
         }
    }

}