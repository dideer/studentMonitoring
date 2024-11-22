package javawithdatabase.crudspringboot.conf;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomerSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        var auth = authentication.getAuthorities();
        var roles = auth.stream().map(r ->r.getAuthority()).findFirst();

        if (roles.orElse("").equals("ROLE_ADMIN")) {
            response.sendRedirect("/admin/");
        } else if (roles.orElse("").equals("ROLE_TEACHER")) {
            response.sendRedirect("/teacher/");
        }else if (roles.orElse("").equals("ROLE_HOD")) {
            response.sendRedirect("/hod/");
        } else {
            response.sendRedirect("/error");
        }


    }
}
