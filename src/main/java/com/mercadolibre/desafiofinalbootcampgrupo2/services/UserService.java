package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.UserDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User findById(Long id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("User not exists in the Database"));
    }

    public User encoderPassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return user;
    }
}
