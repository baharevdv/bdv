package com.company.its.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "ITS_TASK")
@Entity(name = "its_Task")
public class Task {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @JoinColumn(name = "TYPE_ID", nullable = false)
    @ManyToOne(optional = false)
    private TaskType type;

    @Column(name = "INPUT_", nullable = false)
    @NotNull
    private String input;

    @Column(name = "RESULT_")
    private String result;

    @Column(name = "DATE_", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String calculate(String inp, TaskType type){
        return inp;
    }
}