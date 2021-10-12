package com.eleonoralion.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "owner")
@Entity
@Table(name = "fix_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    Long id;
    @Column(name = "model")
    String model;

    @ManyToOne
    @JoinColumn(name = "ownerid")
    User user;
}
