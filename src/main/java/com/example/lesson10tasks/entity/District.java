package com.example.lesson10tasks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class District extends Auditable {
    @Column(nullable = false)
    private String name;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Region region;

    @Builder(builderMethodName = "childBuilder")
    public District(Integer id, LocalDate created_date, LocalDate updated_date,
                    String name, Region region) {
        super(id, created_date, updated_date);
        this.name = name;
        this.region = region;
    }
}
