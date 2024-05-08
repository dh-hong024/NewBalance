package practice.newbalance.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import practice.newbalance.domain.member.Member;

import java.util.ArrayList;
import java.util.Collection;

//Spring Security에서 사용자 정보를 담아둠
public class CustomUserDetail implements UserDetails {

    private final Member member;

    public CustomUserDetail(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    // member 계정 권한을 담아둠
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });

        return collection;
    }

    // member 계정의 비밀번호를 담아둠
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // member 계정의 아이디를 담아둠
    @Override
    public String getUsername() {
        return member.getUserId();
    }

    // 계정이 만료되지 않았는지 (true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않는지 (true: 잠겨있지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 비밀번호가 만료되지 않았는지(true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화되어있는지 (true: 활성화됨)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
