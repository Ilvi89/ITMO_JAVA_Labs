package kotiki.entity;

import lombok.*;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Cat> friends;

    @ManyToOne()
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return id.equals(cat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
