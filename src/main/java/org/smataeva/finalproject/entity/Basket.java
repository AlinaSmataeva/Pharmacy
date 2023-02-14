package org.smataeva.finalproject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Basket {
    private int id;
    private User doctor;
    private ImporterMedicine impMedicine;
    private int amount;
    private int sumCost;
}

