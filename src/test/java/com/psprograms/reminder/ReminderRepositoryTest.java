package com.psprograms.reminder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ReminderRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(ReminderRepositoryTest.class);

    private final Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

    @Autowired
    private ReminderRepository reminderRepository;

    @BeforeEach
    public void initiateDatabaseForTests() {
        logger.info("Deleting all reminders from the database...");
        reminderRepository.deleteAll();

        logger.info("Saving new reminders to the database...");

        Reminder reminder1 = new Reminder("Test", "A testing reminder.",
                LocalDate.now(), null, ReminderType.STANDARD);
        Reminder reminder2 = new Reminder("Test 2", "A testing reminder.",
                LocalDate.now(), LocalTime.parse("10:00:00"), ReminderType.STANDARD);

        reminderRepository.save(reminder1);
        logger.info("Entity with ID: " + reminder1.getId() + " has been saved to the database.");
        reminderRepository.save(reminder2);
        logger.info("Entity with ID: " + reminder2.getId() + " has been saved to the database.");
    }

    @Test
    public void getAllRemindersAndModifyThem() {
        logger.info("Collecting reminders from the database to a list...");

        List<Reminder> reminders = reminderRepository.findAll(pageable).stream()
                .peek(reminder -> {
                    logger.info("[Reminder] ID: " + reminder.getId() + " - Title: " + reminder.getTitle() + " - Content: " + reminder.getContent());
                })
                .collect(Collectors.toList());

        logger.info("Test reminders collected to a list.");

        logger.info("Modifying reminders...");

        reminders.forEach(reminder -> {
            reminder.setTitle("Modified title.");
            reminder.setContent("Modified content.");

            reminderRepository.save(reminder);

            logger.info("[Reminder] ID: " + reminder.getId() + " - Title: " + reminder.getTitle() + " - Content: " + reminder.getContent());
        });

        logger.info("Reminders are modifiable.");
    }

    @Test
    public void getOneReminder() {
        logger.info("Getting reminders separately from the database by their IDs...");

        List<Reminder> reminders = reminderRepository.findAll(pageable).stream()
                .peek(reminder -> {
                    Reminder oneReminder = reminderRepository.findById(reminder.getId())
                            .orElseThrow(() -> {
                                logger.error("Reminder of ID: " + reminder.getId() + " does not exist in the database!");

                                throw new ResourceNotFoundException(reminder.getId());
                            });
                    logger.info("[Reminder] ID: " + reminder.getId() + " - Title: " + reminder.getTitle() + " - Content: " + reminder.getContent());
                })
                .collect(Collectors.toList());

        logger.info("All reminders have been gotten.");
    }
}