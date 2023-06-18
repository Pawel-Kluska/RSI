package com.pwr.skirental.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @ManyToOne
    @JoinColumn
    @NotNull(message = "Category is mandatory")
    private Category category;

    private String description;

    @NotBlank(message = "Size is mandatory")
    private String size;

    @Min(value = 0, message = "Price must be greater than 0")
    private double price;

    @NotBlank(message = "Image is mandatory")
    private String image;
    private boolean isBorrowed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Equipment equipment = (Equipment) o;
        return getId() != null && Objects.equals(getId(), equipment.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
