/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.profil;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tn.esprit.entite.Etablissement;
import tn.esprit.services.EtablissementService;
import tn.esprit.widgets.SideMenuBaseForm;
import com.codename1.crypto.EncryptedStorage;

import com.codename1.crypto.EncryptedStorage;
import java.io.IOException;


/**
 *
 * @author ASUS
 */
public class BonPlan extends SideMenuBaseForm{
    EtablissementService etbsev=new EtablissementService();
    ArrayList<Etablissement> etabs=new ArrayList<>();
    public BonPlan(Resources res) {
        super(BoxLayout.y());
        setTitle("Etablissements");
        //Show list etabs here
        etabs=etbsev.selectAllEnabled();
        for(int i=0;i<etabs.size();i++)
        etabContainer(res,etabs.get(i))  ;
        EncryptedStorage.getInstance();
        setupSideMenu(res);
         
          
        
    }
    
    private void etabContainer(Resources res,Etablissement etab) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Label gouvlbl=new Label(etab.getGouvernorat());
        Container cnt = BorderLayout.west(gouvlbl);
        cnt.setLeadComponent(gouvlbl);
        TextArea ta = new TextArea(etab.getAdresse());
        ta.setUIID("");
        ta.setEditable(false);
        Label nomlbl = new Label(etab.getNom());
        nomlbl.setTextPosition(RIGHT);
        Label villelbl = new Label(etab.getVille());
        villelbl.setTextPosition(LEFT);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta, BoxLayout.encloseX(nomlbl, villelbl)
                ));
        add(cnt);
        
        gouvlbl.addPointerPressedListener(e ->  {
            DetailForm form = new DetailForm(res, etab);
            System.out.println("**************" + form != null);
            form.show();
        });        
        
    }
    @Override    
    public void setupSideMenu(Resources res) {
        Toolbar b = getToolbar();
        b.setGlobalToolbar(true);
        b.removeAll();
        // Ajouter boutton a son container
        Button menuButton = new Button("");
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton)
        );
       
        titleCmp.setScrollableY(false);
        titleCmp.setScrollableX(false);       
        b.setTitleComponent(titleCmp);
        menuButton.setUIID("Title");
        b.addSearchCommand(e -> {
            
        });  
        menuButton.addActionListener((e) -> b.openSideMenu());
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        b.addMaterialCommandToSideMenu("  Profil", FontImage.MATERIAL_ARCHIVE, e -> gotoProfile(res));
        b.addMaterialCommandToSideMenu("  Evenements", FontImage.MATERIAL_ACCESS_TIME, e -> {
            try {
                gotoEvents(res);
            } catch (IOException ex) {
                System.out.println("");
            }
        });
        b.addMaterialCommandToSideMenu("  Blog", FontImage.MATERIAL_BOOK, e -> gotoBlog(res));
        b.addMaterialCommandToSideMenu("  Paramétres", FontImage.MATERIAL_SETTINGS, e -> gotoStats(res));
        b.addMaterialCommandToSideMenu("  Déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> gotoLogin(res));
        
    }
    
    
}
