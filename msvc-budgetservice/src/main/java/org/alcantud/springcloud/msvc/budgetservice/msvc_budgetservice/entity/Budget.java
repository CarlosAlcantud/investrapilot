package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name="budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID userId;


    private String name;

    private UUID categoryId;

    private BigDecimal amountLimit;

    @Enumerated(EnumType.STRING)
    private BudgetPeriod period;

    LocalDate startDate;

    LocalDate endDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(BigDecimal amountLimit) {
        this.amountLimit = amountLimit;
    }

    public BudgetPeriod getPeriod() {
        return period;
    }

    public void setPeriod(BudgetPeriod period) {
        this.period = period;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
