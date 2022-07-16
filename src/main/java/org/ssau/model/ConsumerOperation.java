package org.ssau.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "consumer_operation")
@Data
@RequiredArgsConstructor
public class ConsumerOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "shipped")
    @DecimalMin(value = "0.0", message = "The value of shipped products cannot be less than 0")
    private Double shipped;

    @Column(name = "price")
    @DecimalMin(value = "0.0", message = "The price cannot be less than 0")
    private Double price;

    @Column(name = "comment")
    private String comment;

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
