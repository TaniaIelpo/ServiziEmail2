package co.develhope.ServiziEmail2.services;

import co.develhope.ServiziEmail2.api.entities.NotificationDTO;
import co.develhope.ServiziEmail2.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * @author Tania ielpo
 */

@Service
public class NotificationService {
    @Autowired
    StudentService studentService;

    @Autowired
    EmailService emailService;

    /**
     * if the id of the Student is already in the list, then send the email to that user
     * if not, reply with a BAD_REQUEST message
     * @param notificationDTO
     * @return a response Entity
     */

    public ResponseEntity<String> sendNotification(NotificationDTO notificationDTO) throws MessagingException {
        Student studentToSendNotification= studentService.getStudentById(notificationDTO.getContactID());
        System.out.println("Getting the : "+studentToSendNotification);
        if(studentToSendNotification==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not find the user");
        }
        emailService.sendTo(studentToSendNotification.getEmail(), notificationDTO.getTitle(), notificationDTO.getText());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
