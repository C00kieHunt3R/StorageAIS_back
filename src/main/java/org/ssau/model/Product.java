package org.ssau.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(allowSetters = false, value = {"supplierOperations", "consumerOperations"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "The 'name' field is required")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "crop_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cropDate;

    @Column(name = "current_out_price")
    @DecimalMin(value = "0.0", message = "The product out price cannot be less than 0")
    private Double currentOutPrice;

    @Column(name = "created", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private Date created;

    @Column(name = "updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    private Date updated;

    @Column(name = "total_value")
    @Min(value = 0, message = "The 'total_value' field cannot be less than 0")
    private Long totalValue;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<SupplierOperation> supplierOperations;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<SupplierOperation> consumerOperations;
}
