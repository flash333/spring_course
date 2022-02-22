package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(uniqueConstraints =
@UniqueConstraint(name = "uk_type_name",
        columnNames = "type_name"))
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "type_id")
    private Long id;

    @Column(name = "type_name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "type",
            cascade = CascadeType.ALL)
    private Pet pet;
}
