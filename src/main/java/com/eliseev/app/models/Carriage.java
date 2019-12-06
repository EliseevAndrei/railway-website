package com.eliseev.app.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Carriage extends AbstractEntity {

    private Integer number;
    @Column(name="places_amount")
    private Integer placesAmount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="car_place",
            joinColumns = @JoinColumn(
                    name="carriage_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name="place_id", referencedColumnName = "id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Place> places = new ArrayList<>();

}
