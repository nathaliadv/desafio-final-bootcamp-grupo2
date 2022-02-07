package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.BuyerDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.RepresentativeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.RepresentativeDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    //TODO criar o service para criptografar a senha ao savar no banco de dados
    // TODO validar os parametros recebidos

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private RepresentativeDAO representativeDAO;

    @Autowired
    private BuyerDAO buyerDAO;

    @PostMapping(path = "/add/representative")
    public void addRepresentative(@RequestBody Representative representative) {
        //representativeService.saveRepresentative(representative);
        representativeDAO.save(representative);
    }

    @PostMapping(path = "/add/buyer")
    public void addUser(@RequestBody Buyer buyer) {
        buyerDAO.save(buyer);

    }
}