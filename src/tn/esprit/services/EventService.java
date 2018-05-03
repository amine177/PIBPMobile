/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.entite.Etablissement;
import tn.esprit.entite.Evenements;

/**
 *
 * @author Nayer Jaber
 */
public class EventService {
 
    public void ajoutE(Evenements ev) throws IOException {
        MultipartRequest con = new MultipartRequest();
        String Url = "http://localhost/ajout.php?nom=" + ev.getNom() + "&adresse="+ ev.getAdresse()+"&date="+ev.getDateF()+"&brochure="+ev.getBrochure() ;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ev.getBrochure());
        
                      if(ev.getBrochure() != "" ){
        String mime="image/jpeg";
         int fileNameIndex = ev.getBrochure().lastIndexOf("/") + 1;
            String fileName = ev.getBrochure().substring(fileNameIndex);
            con.addData("file", ev.getBrochure(), mime);
        con.setFilename("file", fileName);//any unique name you want
       
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        con.setDisposeOnCompletion(dlg);
                      }
        con.setUrl(Url);
        con.setPost(true);
       
        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
      public ArrayList<Evenements> getEvents() {
        ArrayList<Evenements> listEvent = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/test.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                       Evenements e = new Evenements() ;
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        e.setId((int) id);
                        e.setNom(obj.get("nom").toString());
                        e.setAdresse(obj.get("adresse").toString());
                        e.setDateF(obj.get("dateF").toString());
                        e.setBrochure(obj.get("brochure").toString());
                                                System.out.println("+++++++++++++++++++++++++++++++++");

                        System.out.println(obj.get("brochure"));
                        listEvent.add(e);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
      
      
       public ArrayList<Evenements> getEvent(int i ) {
        ArrayList<Evenements> listEvent = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/find.php?id="+i );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                       Evenements e = new Evenements() ;
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        e.setId((int) id);
                        e.setNom(obj.get("nom").toString());
                        e.setAdresse(obj.get("adresse").toString());
                        e.setDateF(obj.get("dateF").toString());
                        e.setBrochure(obj.get("brochure").toString());
                        listEvent.add(e);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
       public ArrayList<Etablissement> getCood(String ad) {
        ArrayList<Etablissement> listEvent = new ArrayList<>();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("https://maps.googleapis.com/maps/api/geocode/json?address="+ad+"&key=AIzaSyA4DlUF_3JwkK1t4dk2IEwoC1tRGS20QY4");
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Result result = Result.fromContent(tasks    );
             Etablissement e = new Etablissement(); 
      
                 Double lt  = result.getAsDouble("results/geometry/location/lat");
                 Double lng = result.getAsDouble("results/geometry/location/lng");
                        e.setLatitude(lt);
                        e.setLongitude(lng);
                   
                  listEvent.add(e);
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
   
}
