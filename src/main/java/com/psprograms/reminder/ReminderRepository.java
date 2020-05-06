package com.psprograms.reminder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Override
    Page<Reminder> findAll(Pageable pageable);
    
}