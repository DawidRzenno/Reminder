package com.psprograms.reminder;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Override
    Page<Reminder> findAll(Pageable pageable);

    List<Reminder> findByTargetDateBefore(LocalDate date);

    /*
    @Transactional
    @Modifying
    void deleteByIdAndTargetDateBefore(Long id, LocalDate date);
    */
}