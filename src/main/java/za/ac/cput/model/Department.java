package za.ac.cput.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Chadrack Mbuyi
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Department implements Serializable {

    @Id
    private String departmentId;

    private String departmentName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Faculty faculty;
}
