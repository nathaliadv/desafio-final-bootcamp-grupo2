package com.mercadolibre.desafiofinalbootcampgrupo2.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.UserDAO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationViaTokenFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private UserDAO repository;

    public AuthenticationViaTokenFilter(TokenService tokenService, UserDAO repository){
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //obtem token do cabecalho da requisicao
        String token = extractToken(request);
        //validar token
        boolean tokenValid = tokenService.validToken(token);

        if(tokenValid) {
            //autenticar o token
            performsTokenAuthenticationInSpring(token);
        }
        filterChain.doFilter(request, response);
    }

    private void performsTokenAuthenticationInSpring(String token) {
        String userName = tokenService.getUsername(token);
        UserDetails user = this.repository.findByEmail(userName);
        System.out.println("user " + userName);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication); //forçando autenticacao pelo spring
    }

    private String extractToken(HttpServletRequest request) {
        String token = "";
        String authorization = request.getHeader("Authorization");
        if(authorization==null || authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            return null;
        }else {
            token = authorization.substring(7, authorization.length());
        }
        return token;
    }

}
