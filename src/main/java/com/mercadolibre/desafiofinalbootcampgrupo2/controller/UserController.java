package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderUpdateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.UserDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.UserUpdateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Seller;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/add/representative")
    public ResponseEntity<UserDTO> addRepresentative(@Valid @RequestBody Representative representative) {
        UserDTO userDTO = userService.addUser(representative);

        return ResponseEntity
                .created(URI.create("/add/buyer"))
                .body(userDTO);
    }

    @PostMapping(path = "/add/buyer")
    public ResponseEntity<UserDTO> addBuyer(@Valid @RequestBody Buyer buyer) {
        UserDTO userDTO = userService.addUser(buyer);

        return ResponseEntity
                .created(URI.create("/add/buyer"))
                .body(userDTO);
    }

    @PostMapping(path = "/add/seller")
    public ResponseEntity<UserDTO> addSeller(@Valid @RequestBody Seller seller) {
        UserDTO userDTO = userService.addUser(seller);

        return ResponseEntity
                .created(URI.create("/add/seller"))
                .body(userDTO);
    }

//    @PutMapping(path = "/update/representative")
//    public ResponseEntity<UserDTO> updateRepresentative(@RequestParam Long representativeId, @Valid @RequestBody UserUpdateDTO userUpdateDTO, Authentication authentication) {
//        return ResponseEntity.ok().body(userService.updateUser(representativeId, userUpdateDTO, authentication));
//    }

//    @DeleteMapping(path = "/delete/representative")
//    public void deleteRepresentative(){
//
//    }
}

