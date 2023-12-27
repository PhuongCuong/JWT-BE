package fit.iuh.edu.vn.jwtbespring.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descriptions;

    @OneToMany(mappedBy = "group")
    private List<GroupRole> roles;

    @OneToMany(mappedBy = "group")
    private List<User> users;


    public Group(String name, String descriptions) {
        this.name = name;
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}
