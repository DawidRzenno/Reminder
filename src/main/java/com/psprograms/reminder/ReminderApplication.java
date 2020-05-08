package com.psprograms.reminder;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReminderApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ReminderRepository reminderRepository) {
		return (args) -> {
			reminderRepository.save(new Reminder("Program 1", "Napraw wszystkie bugi (1).", LocalDate.parse("2020-05-05"), LocalTime.parse("14:00:00"), ReminderType.REPEAT));
			reminderRepository.save(new Reminder("Program 2", "Napraw wszystkie bugi (2).", LocalDate.parse("2020-05-06"), LocalTime.parse("14:00:00"), ReminderType.ONE_TIME));
			reminderRepository.save(new Reminder("Program 3", "Napraw wszystkie bugi (3).", LocalDate.parse("2020-05-08"), LocalTime.parse("14:00:00"), ReminderType.REPEAT));
		};
	}
}
