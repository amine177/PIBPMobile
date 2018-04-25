/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.events;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
   
        
       add(new Label("hello")) ; 
        setupSideMenu(res);
        
        
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
