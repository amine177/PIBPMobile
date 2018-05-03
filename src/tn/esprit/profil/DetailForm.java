/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.profil;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.entite.Etablissement;
import tn.esprit.widgets.SideMenuBaseForm;

/**
 *
 * @author ASUS
 */
public class DetailForm extends SideMenuBaseForm{
    private Etablissement currentEtab;
    public DetailForm(Resources res,Etablissement etab) {
        currentEtab=etab;
        formEtab();
    }
    public void formEtab(){
        Container cnt = GridLayout.encloseIn(CENTER, new Label("soems"));
       
        TextArea ta = new TextArea(currentEtab.getAdresse());
        ta.setUIID("");
        ta.setEditable(false);
        Label nomlbl = new Label(currentEtab.getNom());
        nomlbl.setTextPosition(RIGHT);
        Label villelbl = new Label(currentEtab.getVille());
        villelbl.setTextPosition(LEFT);
        cnt.add(new Label(currentEtab.getNote().toString()));
        cnt.add(new TextArea(currentEtab.getDescription().toString()));
        add(cnt);
    }
    
}
