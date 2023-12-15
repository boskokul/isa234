package ftn.isa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "secureTokens")
public class SecureToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private RegisteredUser user;

    @Transient
    private boolean isExpired;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }
}
