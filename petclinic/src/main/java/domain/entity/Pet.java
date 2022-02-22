package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(uniqueConstraints =
@UniqueConstraint(name = "uk_pet",
        columnNames = {"pet_name", "pet_birth_date", "owner_id", "type_id"}))
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pet_id")
    private Long id;

    @Column(name = "pet_birth_date", nullable = false)
    private Date birth_date;

    @Column(name = "pet_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;

}
