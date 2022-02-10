package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Seller;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.BuyerService;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.RepresentativeService;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.SellerService;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // TODO validar os parametros recebidos

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add/representative")
    public void addRepresentative(@RequestBody Representative representative) {
        representative = (Representative) userService.encoderPassword(representative);
        representativeService.saveRepresentative(representative);
    }

    @PostMapping(path = "/add/buyer")
    public void addUser(@RequestBody Buyer buyer) {
        buyer = (Buyer) userService.encoderPassword(buyer);
        buyerService.saveBuyer(buyer);
    }

    @PostMapping(path = "/add/seller")
    public void addSeller(@RequestBody Seller seller) {
        seller = (Seller) userService.encoderPassword(seller);
        sellerService.saveSeller(seller);
    }
}