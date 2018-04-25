/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.events;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.profil.LoginForm;
import tn.esprit.profil.ProfileForm;
import tn.esprit.profil.StatsForm;
import tn.esprit.widgets.SideMenuBaseForm;

/**
 *
 * @author Nayer Jaber
 */
public class ReadEvents extends SideMenuBaseForm{

    public ReadEvents(Resources res) {
   
        
        
        Container c = new Container(new BorderLayout());
        c.addComponent(BorderLayout.CENTER,new Label("hey"));
        
       add(new Label("hello")) ; 
       
       
       
       
       
        setupSideMenu(res);
        
        
    }
  @Override
    public void setupSideMenu(Resources res) {
        Toolbar b = getToolbar();
        b.removeAll();
        Button menuButton = new Button("Click me");
        menuButton.setUIID("Title");
        this.add(menuButton);
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
