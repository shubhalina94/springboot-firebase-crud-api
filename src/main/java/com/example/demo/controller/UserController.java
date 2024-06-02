package com.example.demo.controller;

import com.example.demo.model.User;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.CollectionReference;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/users")
public class UserController {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            Firestore dbFirestore = getFirestore();
            DocumentReference documentReference = dbFirestore.collection("users").document(user.getId());
            documentReference.set(user);
            return ResponseEntity.ok("User created successfully with ID: " + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            Firestore dbFirestore = getFirestore();
            DocumentReference documentReference = dbFirestore.collection("users").document(id);
            DocumentSnapshot documentSnapshot = documentReference.get().get();
            if (documentSnapshot.exists()) {
                return ResponseEntity.ok(documentSnapshot.toObject(User.class));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            Firestore dbFirestore = getFirestore();
            DocumentReference documentReference = dbFirestore.collection("users").document(user.getId());
            documentReference.set(user);
            return ResponseEntity.ok("User updated successfully with ID: " + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            Firestore dbFirestore = getFirestore();
            DocumentReference documentReference = dbFirestore.collection("users").document(id);
            documentReference.delete();
            return ResponseEntity.ok("User deleted successfully with ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            Firestore dbFirestore = getFirestore();
            CollectionReference users = dbFirestore.collection("users");
            List<User> userList = new ArrayList<>();
            for (DocumentSnapshot doc : users.get().get().getDocuments()) {
                userList.add(doc.toObject(User.class));
            }
            return ResponseEntity.ok(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
