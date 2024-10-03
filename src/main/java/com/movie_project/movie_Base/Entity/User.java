package com.movie_project.movie_Base.Entity;

import com.movie_project.movie_Base.Enum.Badge;
import com.movie_project.movie_Base.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_user")
    public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;
    private String password;
    private String email;

    private Boolean isPro;

    @Enumerated(EnumType.STRING)
    private Badge badge;

    @OneToOne
    private Watchlist watchlist;

    private boolean isActiveUser = true;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comment = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Rating> reviews = new HashSet<>();

    @OneToMany
    private Set<Movie> movies = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    private String Picture;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return UserDetails.super.isEnabled();}

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return email;
    }
}
