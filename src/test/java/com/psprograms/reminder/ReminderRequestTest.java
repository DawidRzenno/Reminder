package com.psprograms.reminder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class ReminderRequestTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReminderRepository reminderRepository;

    @Test
    public void receiveAndPrintRequest() throws Exception {
        Reminder reminder = new Reminder("Test", "A testing reminder.",
                LocalDate.now(), null, ReminderType.STANDARD);

        reminderRepository.save(reminder);

        this.mockMvc.perform(get("/reminder/{id}", reminder.getId())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.id").value(reminder.getId()))
                .andExpect(jsonPath("$.title").value(reminder.getTitle()))
                .andExpect(jsonPath("$.content").value(reminder.getContent()))
                .andReturn();
    }
}
