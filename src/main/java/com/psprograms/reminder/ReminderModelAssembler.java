package com.psprograms.reminder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ReminderModelAssembler implements RepresentationModelAssembler<Reminder, EntityModel<Reminder>> {
    @Override
    public EntityModel<Reminder> toModel(Reminder reminder) {
        EntityModel<Reminder> reminderModel = new EntityModel<>(
            reminder,
            linkTo(methodOn(ReminderController.class).getReminderById(reminder.getId())).withSelfRel(),
            linkTo(methodOn(ReminderController.class).getAllReminders(0)).withRel("reminders")
        );
        if(reminder.getStatus() == ReminderStatus.TO_DO) {
            reminderModel.add(
                linkTo(methodOn(ReminderController.class).updateInProgressReminder(reminder.getId())).withRel("inProgress")
            );
            reminderModel.add(
                linkTo(methodOn(ReminderController.class).updateCompletedReminder(reminder.getId())).withRel("completed")
            );
        }
        else if(reminder.getStatus() == ReminderStatus.IN_PROGRESS) {
            reminderModel.add(
                linkTo(methodOn(ReminderController.class).updateCompletedReminder(reminder.getId())).withRel("completed")
            );
        }
        else if(reminder.getStatus() == ReminderStatus.COMPLETED) {
            reminderModel.add(
                linkTo(methodOn(ReminderController.class).undoCompletedReminder(reminder.getId())).withRel("undoCompleted")
            );
        }

        return reminderModel;
    }
    
}