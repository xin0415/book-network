package com.bookproject.book.config;

import com.bookproject.book.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

// used to handle CreatedBy and LastModifiedBy, Integer because user_id is integer
public class ApplicationAuditAware  implements AuditorAware<Integer> {


    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        User userPrincipal=(User)authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }
}
