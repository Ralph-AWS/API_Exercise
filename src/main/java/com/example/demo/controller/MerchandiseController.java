package com.example.demo.controller;

import com.example.demo.service.*;
import com.example.demo.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/merchandise")
class MerchandiseController {

    private final OrderService orderService;

    public MerchandiseController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Merchandise>> getAllMerchandise() {
        List<Merchandise> merchandises = orderService.getAllMerchandise();
        return new ResponseEntity<>(merchandises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchandise> getMerchandiseById(@PathVariable("id") Long id) {
        Optional<Merchandise> merchandise = orderService.getMerchandiseById(id);
        if (merchandise.isPresent()) {
            return new ResponseEntity<>(merchandise.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Merchandise> createMerchandise(@RequestBody Merchandise merchandise) {
        Merchandise createdMerchandise = orderService.createMerchandise(merchandise);
        return new ResponseEntity<>(createdMerchandise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchandise> updateMerchandise(@PathVariable("id") Long id, @RequestBody Merchandise updatedMerchandise) {
        Merchandise updated = orderService.updateMerchandise(id, updatedMerchandise);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchandise(@PathVariable("id") Long id) {
        orderService.deleteMerchandise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}