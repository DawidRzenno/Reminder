package com.psprograms.reminder;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
    
@Entity
@Table(name = "reminders")
class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(128)")
    private String title;
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(4096)")
    private String content;

    @Column(name = "creation_date", nullable = false, columnDefinition = "DATE")
    private LocalDate creationDate;
    @Column(name = "target_date", nullable = false, columnDefinition = "DATE")
    private LocalDate targetDate;

    @Column(name = "target_time", columnDefinition = "TIME")
    private LocalTime targetTime;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(32)")
    private ReminderStatus status;

    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(32)")
    private ReminderType type;

    public Reminder() {}

    public Reminder(String title, String content, LocalDate targetDate, LocalTime targetTime, ReminderType type) {
        this.title = title;
        this.content = content;

        this.creationDate = LocalDate.now();
        this.targetDate = targetDate;
        this.targetTime = targetTime;

        this.status = ReminderStatus.TO_DO;
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public void setTargetTime(LocalTime targetTime) {
        this.targetTime = targetTime;
    }

    public void setStatus(ReminderStatus status) {
        this.status = status;
    }

    public void setType(ReminderType type) { this.type = type; }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public LocalTime getTargetTime() {
        return targetTime;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public ReminderType getType() { return type; }
}