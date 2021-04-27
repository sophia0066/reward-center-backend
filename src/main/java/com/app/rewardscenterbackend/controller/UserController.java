package com.app.rewardscenterbackend.controller;

import com.app.rewardscenterbackend.services.UserService;
import com.app.rewardscenterbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

        @Autowired
        UserService userService;

        @GetMapping("/Profiles")
        public List<User> getAllCustomersInHomePage() throws InterruptedException, ExecutionException {
            return userService.getAllCustomers();
        }

        @GetMapping("/Profiles/{id}")
        public User getCustomerByID(@PathVariable String id) throws InterruptedException, ExecutionException {
            return userService.getCustomerDetails(id);
        }

        @PostMapping("/Profiles/addCustomer")
        public String addCustomer(@RequestBody User user) throws InterruptedException, ExecutionException {
            return userService.saveCustomerDetails(user);
        }

}
