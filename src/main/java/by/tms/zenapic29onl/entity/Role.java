package by.tms.zenapic29onl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String typeOfRole;

    public Role(String typeOfRole) {
        this.typeOfRole = typeOfRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", typeOfRole='" + typeOfRole + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return typeOfRole;
    }
}

