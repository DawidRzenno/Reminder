package com.psprograms.reminder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class ReminderService {
    private final ReminderRepository reminderRepository;

    Logger logger = LoggerFactory.getLogger(ReminderService.class);

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Scheduled(fixedDelay = 3600000)
    public void deleteOutdatedReminder() {
        LocalDate now = LocalDate.now();

        List<Reminder> outdatedReminders = reminderRepository.findByTargetDateBefore(now);

        outdatedReminders.forEach(reminder -> {
            if(reminder.getType() == ReminderType.REPEAT) {
                LocalDate previousDate = reminder.getTargetDate();
                LocalDate updatedDate = previousDate.plusDays(7);

                reminder.setTargetDate(updatedDate);
                reminderRepository.save(reminder);

                logger.info("Recent reminder of ID: " + reminder.getId() + " scheduled to next week.");
            }

            if(reminder.getStatus() == ReminderStatus.COMPLETED && reminder.getType() != ReminderType.REPEAT) {
                reminderRepository.deleteById(reminder.getId());

                logger.info("Outdated reminder with ID: " + reminder.getId() + " has been deleted.");
            }
            else {
                logger.info("Outdated reminder with ID: " + reminder.getId() + " can't be deleted, because it's not completed yet or is scheduled to the next week.");
            }
        });
    }
}
