package br.com.douglasbello.restaurant.security;

import br.com.douglasbello.restaurant.model.dtos.ExceptionResponseDTO;
import br.com.douglasbello.restaurant.services.impl.AdminServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final AdminServiceImpl adminService;

    public SecurityFilter(@Lazy TokenService tokenService, @Lazy AdminServiceImpl adminService) {
        this.tokenService = tokenService;
        this.adminService = adminService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);

        if (token != null) {
            var username = tokenService.validateToken(token);
            UserDetails admin = adminService.loadUserByUsername(username);

            if (admin != null && admin.getAuthorities() != null) {
                var auth = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");

                ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Unauthorized.");
                ObjectMapper mapper = new ObjectMapper();
                String jsonBody = mapper.writeValueAsString(exceptionResponseDTO);
                response.getWriter().write(jsonBody);
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if ( authHeader == null ) return null;
        return authHeader.replace("Bearer ", "");
    }
}
