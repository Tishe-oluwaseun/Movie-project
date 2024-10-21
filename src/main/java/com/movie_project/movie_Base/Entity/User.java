package com.movie_project.movie_Base.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.movie_project.movie_Base.Enum.Badge;
import com.movie_project.movie_Base.Enum.Role;
import com.movie_project.movie_Base.Payments.PricingPlanType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "_user")
    public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(min = 5, message = "username is too short")
    @Size(max = 32, message = "username is too long")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Size(min = 5, message = "password is too short")
    @Size(max = 32, message = "password is too long")
    private String password;

    @NotBlank(message = "Email is necessary")
    @Email(message = "email must contain '@example.com' ")
    @Size(min = 5, message = "email is too short")
    @Size(max = 32, message = "email is too long")
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
