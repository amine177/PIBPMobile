/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.entite.Utilisateur;

/**
 *
 * @author ASUS
 */
public class UtilisateurService {

    public ArrayList<Utilisateur> selectAllEnabled() {
        ArrayList<Utilisateur> lstUsers = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/profil/listAuthentificators/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> users = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +users.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

                    for (Map<String, Object> obj : list) {
                        Utilisateur user = new Utilisateur();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        user.setId((int) id);
                        /*user.setUsername(obj.get("username").toString());
                        user.setUsernameCanonical("username_canonical");
                        user.setNom(obj.get("name").toString());*/
                        lstUsers.add(user);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lstUsers;
    }

    
    
}
