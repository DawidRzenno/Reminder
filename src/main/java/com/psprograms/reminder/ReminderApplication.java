package com.psprograms.reminder;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReminderApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ReminderRepository reminderRepository) {
		return (args) -> {
			reminderRepository.save(new Reminder("Program", "Dokończ pisanie programu Reminders.", LocalDate.parse("2020-05-03"), LocalTime.parse("10:00:00")));
			reminderRepository.save(new Reminder("Program 2", "Sprawdź czy program Reminders działa poprawnie.", LocalDate.parse("2020-05-04"), LocalTime.parse("12:00:00")));
			reminderRepository.save(new Reminder("Program 3", "Napraw wszystkie bugi.", LocalDate.parse("2020-05-05"), LocalTime.parse("14:00:00")));
			reminderRepository.save(new Reminder("Program 4", "Napraw wszystkie bugi (2).", LocalDate.parse("2020-05-06"), LocalTime.parse("14:00:00")));
			reminderRepository.save(new Reminder("Program 5", "Napraw wszystkie bugi (3).", LocalDate.parse("2020-05-07"), LocalTime.parse("14:00:00")));
			reminderRepository.save(new Reminder("Program 6", "Napraw wszystkie bugi (4).", LocalDate.parse("2020-05-08"), LocalTime.parse("14:00:00")));
		};
	}
}
