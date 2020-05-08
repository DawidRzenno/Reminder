package com.psprograms.reminder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReminderBasicTest {
    private final Logger logger = LoggerFactory.getLogger(ReminderBasicTest.class);

    private final Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

    @Autowired
    private ReminderRepository reminderRepository;

    @Before
    public void setUp() {
        logger.info("Saving reminders to the database...");

        reminderRepository.save(new Reminder("Test", "A testing reminder.",
                LocalDate.now(), null, ReminderType.STANDARD));
        reminderRepository.save(new Reminder("Test 2", "A testing reminder.",
                LocalDate.now(), LocalTime.parse("10:00:00"), ReminderType.STANDARD));

        logger.info("Test reminders saved to the database.");
    }

    @Test
    public void getAllRemindersAndModifyThem() {
        logger.info("Collecting reminders from the database to a list...");

        List<Reminder> reminders = reminderRepository.findAll(pageable).stream()
                .peek(reminder -> {
                    logger.info("[Reminder] ID: " + reminder.getId() + " - Title: " + reminder.getTitle()
                            + " - Content: " + reminder.getContent());
                })
                .collect(Collectors.toList());

        logger.info("Test reminders collected to a list.");

        logger.info("Modifying reminders...");

        reminders.forEach(reminder -> {
            reminder.setTitle("Modified title.");
            reminder.setContent("Modified content.");

            reminderRepository.save(reminder);

            logger.info("[Reminder] ID: " + reminder.getId() + " - Title: " + reminder.getTitle()
                    + " - Content: " + reminder.getContent());
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
                    logger.info("Reminder of ID: " + reminder.getId() + " exists.");
                })
                .collect(Collectors.toList());

        logger.info("All reminders have been gotten.");
    }
}