package model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @NonNull
    @Builder.ObtainVia
    private String name;

    private Date birth;

    private String race;

    @Enumerated(EnumType.STRING)
    private CatColor color;

    @NonNull
    private Long ownerId;

    @ManyToMany
    @ToString.Exclude
    private List<Cat> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cat cat = (Cat) o;
        return id != null && Objects.equals(id, cat.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
