package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.BuyerDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.RepresentativeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.SellerDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.UserDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.UserDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.UserInvalidException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Seller;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private RepresentativeDAO representativeDAO;
    @Autowired
    private BuyerDAO buyerDAO;
    @Autowired
    private SellerDAO sellerDAO;
    @Autowired
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private String encoderPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public Representative saveRepresentative(Representative representative) {
        return representativeDAO.save(representative);
    }

    public Buyer saveBuyer(Buyer buyer) {
        return buyerDAO.save(buyer);
    }

    public Seller saveSeller(Seller seller) {
        return sellerDAO.save(seller);
    }

    public <T> UserDTO addUser(User user) {
        String password = encoderPassword(user.getPassword());
        user.setPassword(password);
        User userVerify =  verifyToUserAndSave(user);

        return convertUserToUserDTO(userVerify);
    }

//    public UserDTO updateUser(Long userId, UserUpdateDTO userDTO, Authentication authentication) {
//        userDAO.deleteAllByUser(user);
//        UserDTO user = userDTO;
//        user = userDAO.save(user);
//
//        return convertUserInUserDTO(user, authentication);
//    }

    public UserDTO convertUserInUserDTO(User user, Authentication authentication) {
        return UserDTO.builder()
                .id(getUserId(authentication))
                .name(user.getName())
                .email(user.getEmail())
                .userName(user.getUsername())
                .isEnabled(user.isEnabled())
                .isCredentialsNonExpired(user.isCredentialsNonExpired())
                .build();
    }

    public <T> UserDTO convertUserToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .userName(user.getUsername())
                .isEnabled(user.isEnabled())
                .isCredentialsNonExpired(user.isCredentialsNonExpired())
                .build();
    }

    public <T> User verifyToUserAndSave(User user) {
        try {
            if (user instanceof Representative)
                return saveRepresentative((Representative) user);

            if (user instanceof Buyer)
                return saveBuyer((Buyer) user);

            if (user instanceof Seller)
                return saveSeller((Seller) user);

            throw new UserInvalidException("Invalid User!");
        }catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Error saving to database!");
        }
    }

    private Long getUserId(Authentication authentication) {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

}