package com.fulo.targets.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
//auto ddl
@Table(name = "targets")
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "why")
    private String why;

    @Column(name = "status", nullable = false)
    private TargetStatus status;

    @Column(name = "priority", nullable = false)
    private TargetPriority priority;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    public Target() {
    }

    public Target(UUID id, String name, String description, String why, TargetStatus status, TargetPriority priority, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.why = why;
        this.status = status;
        this.priority = priority;
        this.created = created;
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TargetPriority getPriority() {
        return priority;
    }

    public void setPriority(TargetPriority priority) {
        this.priority = priority;
    }

    public TargetStatus getStatus() {
        return status;
    }

    public void setStatus(TargetStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return Objects.equals(id, target.id) && Objects.equals(name, target.name) && Objects.equals(description, target.description) && Objects.equals(why, target.why) && status == target.status && priority == target.priority && Objects.equals(created, target.created) && Objects.equals(updated, target.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, why, status, priority, created, updated);
    }

    @Override
    public String toString() {
        return "Target{" +
                "created=" + created +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", why='" + why + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", updated=" + updated +
                '}';
    }
}
