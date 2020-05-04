package com.psprograms.reminder;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* ########################################################################################
This is the Reminder entity. Every object of this class is going to have unique ID and 
this set of fields: 
* title - this field describes the entire reminder object in a couple of words,
* content - this one is much longer, as the name says it's the main part of a reminder,
* creationDate - this field holds the date in which the reminder has been created, it
sets itself within the constructor,
* targetDate - this part is pretty important, the object will notify user only when 
the actual date and targetDate are the same
(NOTICE! both creationDate and targetDate fields provice methods for parsing strings!),
* status - this field holds values like TO_DO, IN_PROGRESS or 
COMPLETED (see: ReminderStatus enum)

The empty constructor of this class is needed by Spring to run.
######################################################################################## */
    

@Entity
@Table(name = "reminders")
class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "tatget_time", nullable = false, columnDefinition = "TIME")
    private LocalTime targetTime;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(32)")
    private ReminderStatus status;

    public Reminder() {}

    public Reminder(String title, String content, LocalDate targetDate, LocalTime targetTime) {
        this.title = title;
        this.content = content;

        this.creationDate = LocalDate.now();
        this.targetDate = targetDate;
        this.targetTime = targetTime;

        this.status = ReminderStatus.TO_DO;
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
}