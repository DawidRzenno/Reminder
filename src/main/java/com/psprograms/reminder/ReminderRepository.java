package com.psprograms.reminder;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    
}