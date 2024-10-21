package com.movie_project.movie_Base.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.movie_project.movie_Base.Enum.Badge;
import com.movie_project.movie_Base.Enum.Role;
import com.movie_project.movie_Base.Payments.PricingPlanType;
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
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "_user")
    public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;
    private String password;
    private String email;



    @Enumerated(EnumType.STRING)
    private Badge badge;

    @OneToOne
    private Watchlist watchlist;

    private boolean isActiveUser ;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comment = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Rating> reviews = new HashSet<>();

    @OneToMany
    private Set<Movie> movies = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    private String Picture;

    @Enumerated(EnumType.STRING)
    private PricingPlanType pricingPlan;





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
