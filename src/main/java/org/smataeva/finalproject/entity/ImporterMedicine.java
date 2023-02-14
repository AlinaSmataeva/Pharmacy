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
public class ImporterMedicine {
    private int id;
    private Importer importer;
    private Medicine medicine;
    private int dose;
    private int cost;
    private String imageName;
}
