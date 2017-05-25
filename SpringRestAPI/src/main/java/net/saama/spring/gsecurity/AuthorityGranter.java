package net.saama.spring.gsecurity;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

/**
 * Interface to grant authorities based on the values in the map. Open for extension as custom logic for authorities
 * can be added as implementations of the interface and wired in.
 */
public interface AuthorityGranter {
    List<? extends GrantedAuthority> getAuthorities(Map<String, ?> map);
}
