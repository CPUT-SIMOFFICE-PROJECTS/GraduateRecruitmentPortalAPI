package za.ac.cput.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Mbuso Kotobe
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Recruiter extends User implements Serializable {
    private String companyName;
    private LocalDate dateAdded;
    @OneToMany
    private Set<Vacancy> vacancies;
}
