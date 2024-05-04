package com.example.resens.model;
import com.example.resens.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.util.*;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private Date birthday;
    @NonNull
    @Email
    private String email;
    @NonNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean enabled;
    private long resetToken;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private  byte[] profileImage;
    @Transient
    private static byte[] defaultProfileImage;
    private Integer phoneNumber;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println(role.name());
        authorities.add(() -> role.name());
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
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
        return enabled;
    }
    static {
        try {
            defaultProfileImage = StreamUtils.copyToByteArray(
                    Objects.requireNonNull(User.class.getClassLoader().getResourceAsStream("HelpCenterAssets/profileImage.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PrePersist
    public void prePersist() {
        if (profileImage == null || profileImage.length == 0) {
            profileImage = defaultProfileImage;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId != null && userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }


    @OneToOne
    private Adress adress;




}

