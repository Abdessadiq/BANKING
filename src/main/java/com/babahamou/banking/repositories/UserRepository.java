package com.babahamou.banking.repositories;

import com.babahamou.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Exemple des méthode pour séléctionner les données en utilisant SDP
     */


    // select * from user where firstName = 'ali'
    List<User> findAllByFirstName(String firsName);

    //select * from user where firstName like '%ali%'
    List<User> findAllByFirstNameContaining(String firstName);

    //select * from user where firstName ilike '%Ali%'
    List<User> findUserByFirstNameContainingIgnoreCase(String firstName);

    //select * from user u inner join account a where u.id = a.user_id and iban = "ZA1233434"
    List<User> findAllByAccount_Iban(String iban);

    // select * from user u where firstname = "%ali%" and mail = "abdoubhm2018@gmail.com"
    User findByFirstNameContainingIgnoreCaseAndEmail(String firstname, String mail);

    /**
     * L'annotation @Query Avec  JPQL == Pour créer les requête pérsonnalisé ..
     */

    @Query("from User where firstName = :fn")
    List<User> searchAllByFirstName(@Param("fn") String firsName);


    @Query("from User where firstName = '%:firsName%'")
    List<User> searchAllByFirstNameContaining(String firstName);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchAllByAccount_Iban(String iban);


    /**
     * Pour Envoyé une requête native SQL En utilise
     */

    @Query(value = "select * from user u inner join account a where u.id = a.user_id and iban = :iban", nativeQuery = true)
    List<User> searchAllByAccount_IbanNative(String iban);


}
