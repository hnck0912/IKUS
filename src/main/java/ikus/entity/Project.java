package ikus.entity; // Đảm bảo đúng package của bạn

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore; // Import này để tránh lỗi lặp vô hạn
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

}
