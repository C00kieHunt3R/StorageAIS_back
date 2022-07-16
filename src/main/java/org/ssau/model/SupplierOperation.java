package org.ssau.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "supplier_operation")
@Data
@RequiredArgsConstructor
public class SupplierOperation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "received")
    @DecimalMin(value = "0.0", message = "Received products count can`t be less than 0")
    private Double received;

    @Column(name = "price")
    @DecimalMin(value = "0.0", message = "Received product price can`t be less than 0")
    private Double price;

    @Column(name = "created", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private Date created;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
    private Partner partner;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
