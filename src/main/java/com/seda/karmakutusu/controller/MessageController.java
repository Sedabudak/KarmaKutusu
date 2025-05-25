package com.seda.karmakutusu.controller;

import com.seda.karmakutusu.model.Message;
import com.seda.karmakutusu.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Base path for message-related endpoints
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Yeni Mesaj Oluşturma
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            Message savedMessage = messageRepository.save(message);
            return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tüm Mesajları Getirme
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        try {
            List<Message> messages = messageRepository.findAll();
            if (messages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tek Bir Mesajı ID ile Getirme
    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        return messageRepository.findById(id)
                .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mesaj Güncelleme
    @PutMapping("/messages/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message newMessage) {
        return messageRepository.findById(id)
                .map(message -> {
                    message.setContent(newMessage.getContent());
                    Message updatedMessage = messageRepository.save(message);
                    return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mesaj Silme
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return messageRepository.findById(id)
                .map(message -> {
                    messageRepository.deleteById(id);
                    return ResponseEntity.ok().build(); // ResponseEntity<Void> or ResponseEntity.noContent().build() is also common
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Buraya diğer CRUD operasyonları için metodlar eklenebilir (GET, POST)
} 