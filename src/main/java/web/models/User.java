package web.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Component
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "Поле 'Имя' не должно быть пустым!")
    @Size(min = 3, max = 30, message = "Имя должно быть от 3 до 30 символов!")
    @Column(name = "name", unique = true)
    private String username;

    @NotNull
    @Min(value = 18, message = "Возраст от 18 лет")
    @Max(value = 150, message = "Возраст до 150 лет")
    @Column(name = "age", length = 3)
    private int age;

    @NotNull
    @Column(name = "city")
    @NotEmpty(message = "Поле 'Город' не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Введите название города правильно!")
    private String city;

    @NotNull
    @Column(name = "email")
    @NotEmpty(message = "Поле 'Email' не должно быть пустым!")
    @Email(message = "Введите правильный Email!")
    private String email;

    @Column(name = "password")
    @Size(min = 6, message = "Введите не меньше 6 символов!")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, int age, String city, String email, String password) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.city = city;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String username, int age, String city, String email, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.city = city;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
