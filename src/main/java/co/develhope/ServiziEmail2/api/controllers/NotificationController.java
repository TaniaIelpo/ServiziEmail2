package co.develhope.ServiziEmail2.api.controllers;

import co.develhope.ServiziEmail2.api.entities.NotificationDTO;
import co.develhope.ServiziEmail2.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Tania Ielpo
 */

@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;



    /**
     * use a try/catch in the dedicated controller
     * @param notificationDTO Body of request
     * @return a Response Entity
     */
    @PostMapping("/notification")

    public ResponseEntity<String> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            return notificationService.sendNotification(notificationDTO);
        } catch (Exception e) { //if the request is not correct, reply with an error 500 HTTP response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
