/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.profil;

import com.codename1.db.Database;

import tn.esprit.securite.User;

/**
 *
 * @author ASUS
 */
public class BaseDD {
    Database db=null;
    public static String createUserTableDb(){
        String query="CREATE TABLE User ( \n" +
"	username             varchar(180) NOT NULL  ,\n" +
"	plain                 varchar(255)   NOT NULL,\n" +
" );";
        return query;
    }
    public static String insertUser(User u){
        String query="insert into User value('"+u.getLogin()+"','"+u.getPlain()+"')";
        return query;
    }
}
