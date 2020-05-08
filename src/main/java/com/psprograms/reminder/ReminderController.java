package com.psprograms.reminder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
class ReminderController {
    private final ReminderRepository reminderRepository;
    private final ReminderModelAssembler reminderAssembler;

    public ReminderController(ReminderRepository reminderRepository, ReminderModelAssembler reminderAssembler) {
        this.reminderRepository = reminderRepository;
        this.reminderAssembler = reminderAssembler;
    }

    @GetMapping("/reminder/page/{page}")
    CollectionModel<EntityModel<Reminder>> getAllReminders(@PathVariable int page) {
        Pageable xPageWithFiveElementsSortById = PageRequest.of(page, 5, Sort.by("id").ascending());

        List<EntityModel<Reminder>> reminderModel = reminderRepository.findAll(xPageWithFiveElementsSortById)
            .stream()
            .map(reminderAssembler::toModel)
            .collect(Collectors.toList());

        return new CollectionModel<>(reminderModel, linkTo(methodOn(ReminderController.class).getAllReminders(page)).withSelfRel());
    }

    @GetMapping("/reminder/{reminderId}")
    EntityModel<Reminder> getReminderById(@PathVariable Long reminderId) throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
            .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        return reminderAssembler.toModel(reminder);
    }

    @PostMapping("/reminder")
    ResponseEntity<?> createReminder(@RequestBody Reminder newReminder) {
        newReminder.setCreationDate(LocalDate.now());
        newReminder.setStatus(ReminderStatus.TO_DO);

        EntityModel<Reminder> savedReminder = reminderAssembler.toModel(reminderRepository.save(newReminder));


        return ResponseEntity
            .created(savedReminder.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(savedReminder);
    }
    
    @PutMapping("/reminder/{reminderId}")
    ResponseEntity<?> updateReminder(@RequestBody Reminder newReminder, @PathVariable Long reminderId) throws ResourceNotFoundException {
        Reminder updatedReminder = reminderRepository.findById(reminderId)
            .map((reminder) -> {
                reminder.setTitle(newReminder.getTitle());
                reminder.setContent(newReminder.getContent());
                reminder.setTargetDate(newReminder.getTargetDate());
                reminder.setTargetTime(newReminder.getTargetTime());
                return reminderRepository.save(reminder);
            })
            .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        EntityModel<Reminder> reminderModel = reminderAssembler.toModel(updatedReminder);

        return ResponseEntity
            .created(reminderModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(reminderModel);
    }

    @PatchMapping("/reminder/{reminderId}/in-progress")
    ResponseEntity<?> updateInProgressReminder(@PathVariable Long reminderId) throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
            .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        if(reminder.getStatus() == ReminderStatus.TO_DO) {
            reminder.setStatus(ReminderStatus.IN_PROGRESS);

            return ResponseEntity
                .ok(reminderAssembler.toModel(reminderRepository.save(reminder)));
        }

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .build();
    }

    @PatchMapping("/reminder/{reminderId}/completed")
    ResponseEntity<?> updateCompletedReminder(@PathVariable Long reminderId) throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
            .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        if(reminder.getStatus() == ReminderStatus.IN_PROGRESS || reminder.getStatus() == ReminderStatus.TO_DO) {
            reminder.setStatus(ReminderStatus.COMPLETED);

            return ResponseEntity
                .ok(reminderAssembler.toModel(reminderRepository.save(reminder)));
        }

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .build();
    }

    @PatchMapping("/reminder/{reminderId}/undo-completed") 
    ResponseEntity<?> undoCompletedReminder(@PathVariable Long reminderId) throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
            .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        if(reminder.getStatus() == ReminderStatus.COMPLETED) {
            reminder.setStatus(ReminderStatus.IN_PROGRESS);

            return ResponseEntity
                .ok(reminderAssembler.toModel(reminderRepository.save(reminder)));
        }

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .build();
    }

    @DeleteMapping("/reminder/{reminderId}/delete")
    ResponseEntity<?> deleteReminder(@PathVariable Long reminderId) throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
            .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        reminderRepository.deleteById(reminder.getId());

        return ResponseEntity
            .status(HttpStatus.OK)
            .build();
    }
}