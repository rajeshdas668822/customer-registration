package org.spring.cloud.demo.registration.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.spring.cloud.demo.registration.entity.AuditBase;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of = {"planRef","name"})
@Table(name = "plan")
@Audited
public class Plan extends AuditBase<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_id")
    @SequenceGenerator(name = "plan_id", sequenceName = "plan_seq")
    @Column(name = "plan_id", unique = true)
    private Integer planId;

    @Column(name = "plan_ref")
    private String planRef;

    @Column(name = "plan_name")
    private String name;

    @OneToMany(mappedBy = "plan")
    private List<Product> defaultProducts;


}
