package org.ssau.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "partner")
@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(allowSetters = false, value = {"supplierOperations", "consumerOperations"})
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_name", unique = true, nullable = false)
    private String companyName;

    @Column(name = "description")
    private String description;

    @Column(name = "email", length = 254, nullable = false)
    @Email
    private String email;

    @Column(name = "phone", length = 18, nullable = false)
    @Pattern(regexp = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$")
    private String phone;

    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;

    @Column(name = "created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private Date created;

    @Column(name = "updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    private Date updated;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.REMOVE)
    private List<SupplierOperation> supplierOperations;
    @OneToMany(mappedBy = "partner", cascade = CascadeType.REMOVE)
    private List<ConsumerOperation> consumerOperations;
}
