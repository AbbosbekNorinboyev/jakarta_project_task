package com.example.lesson10tasks.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Region extends Auditable {
    @Column(nullable = false)
    private String name;
    //    @Column(columnDefinition = "false")
//    private Boolean deleted;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "region"
    )
    private List<District> district;

    @Builder(builderMethodName = "childBuilders")
    public Region(Integer id, LocalDate created_date, LocalDate updated_date,
                  String name, List<District> district) {
        super(id, created_date, updated_date);
        this.name = name;
        this.district = district;
    }
}
