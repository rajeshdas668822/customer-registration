package org.spring.cloud.demo.registration.entity.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.spring.cloud.demo.registration.entity.AuditBase;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of ={"productRef"})
@Table(name = "product")
@Audited
public class Product extends AuditBase<String>  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
    @SequenceGenerator(name = "product_id", sequenceName = "product_seq")
    @Column(name = "product_id", unique = true)
    private int productId;

    @Column(name = "description")
    private String description;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_ref",unique = true)
    private String productRef;

    @ManyToOne
    @JoinColumn(name = "plan_ref")
    private Plan plan;


}
