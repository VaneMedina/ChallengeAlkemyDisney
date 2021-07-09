package com.company.challengealkemy.Model;
import com.company.challengealkemy.Enum.Rol;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_role;
    @Enumerated(EnumType.ORDINAL)
    private Rol nameRol ;
}
