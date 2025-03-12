package com.fulo.targets.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "hit_list")
public class HitList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "hitList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Target> targets;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    public HitList() {
    }

    public HitList(UUID id, String description, LocalDateTime created, List<Target> targets, String title, LocalDateTime updated) {
        this.created = created;
        this.description = description;
        this.id = id;
        this.targets = targets;
        this.title = title;
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

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HitList hitList = (HitList) o;
        return Objects.equals(id, hitList.id) && Objects.equals(title, hitList.title) && Objects.equals(description, hitList.description) && Objects.equals(targets, hitList.targets) && Objects.equals(created, hitList.created) && Objects.equals(updated, hitList.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, targets, created, updated);
    }

    @Override
    public String toString() {
        return "HitList{" +
                "created=" + created +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", targets=" + targets +
                ", updated=" + updated +
                '}';
    }
}
