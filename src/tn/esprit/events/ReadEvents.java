/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.events;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
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
public class ReadEvents extends SideMenuBaseForm{

    public ReadEvents(Resources res) {
   
        
          Form f = new Form(); 
          SpanLabel lb = new SpanLabel("");
          FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
          fab.addActionListener(e -> gotoajoutEvent(res));
          fab.bindFabToContainer( f.getContentPane());
        EventService es = new EventService() ; 
         ArrayList<Evenements> lis=es.getEvents();
            lb.setText(lis.get(0).getNom());
            add(lb);
            add(f);


  

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

    private void gotoajoutEvent(Resources res) {
       new ajoutEvent(res).show();
    }
    
    
}
