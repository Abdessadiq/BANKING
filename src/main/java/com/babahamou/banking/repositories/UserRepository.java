package com.babahamou.banking.repositories;

import com.babahamou.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Exemple des méthode pour séléctionner les données en utilisant SDP
     */


    // select * from user where firstName = 'ali'
    List<User> findAllByFirstName(String firsName);

    //select * from user where firstName like '%like%'
    List<User> findAllByFirstNameContaining(String firstName);

    //select * from user where firstName ilike '%like%'
    List<User> findUserByFirstNameContainingIgnoreCase(String firstName);

    //select * from user u inner join account a where u.id = a.user_id and iban = "ZA1233434"
    List<User> findAllByAccount_Iban(String iban);

}
