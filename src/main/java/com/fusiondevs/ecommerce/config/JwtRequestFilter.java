package com.fusiondevs.ecommerce.config;

import com.fusiondevs.ecommerce.service.session.AuthenticationService;
import com.fusiondevs.ecommerce.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;

public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String jwt = null;
        String username = null;

        // 1. Intenta extraer el token de las cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        // 2. Si no se encontró en las cookies, intenta extraerlo del header Authorization
        final String authorizationHeader = request.getHeader("Authorization");
        if (jwt == null && authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
        }

        // 3. Si se obtuvo el token, intenta extraer el username usando AuthenticacionService
        if (jwt != null) {
            try {
                username = authenticationService.getUserName(jwt);
            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"code\":401, \"message\":\"Token expired\", \"body\":null}");
                return; // No continúa la cadena de filtros
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"code\":401, \"message\":\"Filter: Invalid token\", \"body\":null}");
                return;
            }
        } else {
            // Si no se encontró token, se deja que la seguridad de Spring lo maneje (por ejemplo, retornando 401)
        }

        // 4. Si se obtuvo un username y aún no hay autenticación en el contexto, procede a validar el token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Verifica que el token sea válido
            if (jwtUtil.isTokenValid(jwt, username)) {
                // Se crea un objeto de usuario simple. En una aplicación real, cargarías los UserDetails desde la BD.
                User user = new User(username, "", new ArrayList<>());
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Se establece la autenticación en el contexto
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"code\":401, \"message\":\"Invalid token\", \"body\":null}");
                return;
            }
        }

        // 5. Continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
