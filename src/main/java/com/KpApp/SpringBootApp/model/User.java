package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 3486087007312642611L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_generator")
    @SequenceGenerator(name = "user_generator",sequenceName = "user_sequence" ,allocationSize = 25,initialValue = 25)
    private Long id;

    @NaturalId(mutable = true)
    @Column(unique = true,name = "username")
    private String username;

    @NaturalId(mutable = true)
    @Column(name = "password")
    private String password;


    @Column(nullable = true)
    private String  surname  = null ;

    @Column(nullable = true)
    private String first_name  = null ;

    @Column(nullable = true)
    private String patronymic = null;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @Column(nullable = true)
    private String photo_name;

    @Transient
    private Iterable<Order> orders;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
