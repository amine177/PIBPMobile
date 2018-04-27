/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.events;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;
import tn.esprit.entite.Evenements;
import tn.esprit.profil.LoginForm;
import tn.esprit.profil.ProfileForm;
import tn.esprit.profil.StatsForm;
import tn.esprit.services.EventService;
import tn.esprit.widgets.SideMenuBaseForm;

/**
 *
 * @author Nayer Jaber
 */
public class ajoutEvent extends SideMenuBaseForm {
    
        private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
	        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	        return sDate;
	    }
    
 public ajoutEvent(Resources res) throws ParseException {
   
        
 
     
            Form hi = new Form();
     
            Picker datePicker = new Picker();
            datePicker.setType(Display.PICKER_TYPE_DATE);
            datePicker.setDate(new Date());
           System.out.println( datePicker.getDate());
           
          Date l =datePicker.getDate() ;
           SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ymd = ymdFormat.format(l);
           ;
           
            
           
            TextField nom = new TextField("", "Nom de l'évenements", 20, TextArea.ANY);
            TextField adresse = new TextField("", "Adresse", 100, TextArea.ANY);
            Button ajout = new Button ("Ajout") ; 
                   hi.add(nom).add(datePicker).add(ajout).add(adresse);
                        ajout.addActionListener((i)->{
                 if(nom.getText()==""){
                    ToastBar.showMessage("SAISIR UN NOM!",FontImage.MATERIAL_WARNING);
                 }else if (adresse.getText()==""){
                     ToastBar.showMessage("SAISIR UNE ADRESSE!",FontImage.MATERIAL_WARNING);
                 }else{
           ToastBar.showMessage("SUCESS D'AJOUT !",FontImage.MATERIAL_DONE);
           EventService es = new EventService();
           Evenements e = new Evenements( nom.getText(), convertUtilToSql(ymdFormat.parse(ymd)), adresse.getText());
            es.ajoutE(e);
                     
                 }
               
            }
            
            );
                       add(hi);
                       
         
        
 
        
       
       
       
       
       
       
        setupSideMenu(res);
        
        
    }
 @Override
    public void setupSideMenu(Resources res) {
        Toolbar b = getToolbar();
        b.removeAll();
        // Ajouter boutton a son container
        Button menuButton = new Button("Click me");
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton)             
        );
        
      
        titleCmp.setScrollableY(false);
        titleCmp.setScrollableX(false);
        // AJOUTER le container du BOUTTON OU toolbar
        b.setTitleComponent(titleCmp);
//        b.setTitleComponent(fab.bindFabToContainer(titleCmp,  CENTER, BOTTOM));
        //add(titleCmp);
        menuButton.setUIID("Title");
        menuButton.addActionListener((e) -> b.openSideMenu());
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        b.addMaterialCommandToSideMenu("  Profil", FontImage.MATERIAL_ARCHIVE, e -> gotoProfile(res));
        b.addMaterialCommandToSideMenu("  Evenements", FontImage.MATERIAL_ACCESS_TIME, e -> gotoEvents(res));
        b.addMaterialCommandToSideMenu("  Blog", FontImage.MATERIAL_BOOK, e -> gotoBlog(res));
        b.addMaterialCommandToSideMenu("  Paramétres", FontImage.MATERIAL_SETTINGS, e -> gotoStats(res));
        b.addMaterialCommandToSideMenu("  Déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> gotoLogin(res));

    }

    
    
    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }

    @Override
    protected void gotoProfile(Resources res) {
        new ProfileForm(res).show();
    }

    @Override
    protected void gotoStats(Resources res) {
        new StatsForm(res).show();
    }

    @Override
    protected void gotoEvents(Resources res) {
        new ReadEvents(res).show();
    }

    @Override
    protected void gotoBlog(Resources res) {
        //TODO: insetensi el form events here Bro: Amri
    }

    @Override
    protected void gotoLogin(Resources res) {
        new LoginForm(res).show();
    }
    
    
}