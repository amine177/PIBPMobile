/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.blog;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.entite.Article;
import tn.esprit.entite.CommentaireB;
import tn.esprit.widgets.SideMenuBaseForm;

/**
 *
 * @author aminos
 */
public class LireArticle extends SideMenuBaseForm {

    Resources res;
    Toolbar tb;
    Container articleContainer = new Container(BoxLayout.y());
    Container commentairesContainer = new Container(BoxLayout.y());
    Container searchTagContainer = new Container(BoxLayout.y());
    Container wholeContainer = new Container(BoxLayout.y());
    TextArea commentaireTextArea = new TextArea();
    Article article;

    public LireArticle(Resources res, Article article) {
        this.res = res;
        this.tb = getToolbar();
        this.article = article;
        setupSideMenu(res);
        commentaireTextArea.setHint("Ajouter un commentaire...");
        setArticle(article);
        wholeContainer.add(searchTagContainer);
        wholeContainer.add(articleContainer);
        wholeContainer.add(commentairesContainer);
        add(wholeContainer);
    }

    @Override
    public void setupSideMenu(Resources res) {
        Toolbar b = getToolbar();
        b.removeAll();
        // Ajouter boutton a son container
        Button menuButton = new Button();
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
        b.addMaterialCommandToSideMenu("  Evenements", FontImage.MATERIAL_ACCESS_TIME, e -> showOtherForm(res));
        b.addMaterialCommandToSideMenu("  Blog", FontImage.MATERIAL_BOOK, e -> gotoBlog(res));
        b.addMaterialCommandToSideMenu("  Paramétres", FontImage.MATERIAL_SETTINGS, e -> gotoStats(res));
        b.addMaterialCommandToSideMenu("  Déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> gotoLogin(res));

    }

    public void setArticle(Article article) {
        

        articleContainer.removeAll();
        commentairesContainer.removeAll();
        BrowserComponent browser = new BrowserComponent();
        browser.setPreferredSize(new Dimension(300, 300));
        browser.setScrollableY(true);
        browser.setPage(article.getTexte(), "");
        articleContainer.add(new Label(article.getTitre()));
        articleContainer.add(browser);
        commentairesContainer.add(new Label("Ajouter un commentaire:"));
        commentaireTextArea.setPreferredH(100);
        commentairesContainer.add(commentaireTextArea);
        Button commenterButton = new Button("Commenter") ;
        //bind actions to comment
        commentairesContainer.add(commenterButton);
        
        if (article.getCommentaireBCollection() != null && !article.getCommentaireBCollection().isEmpty()) {
            for (CommentaireB commentaire : article.getCommentaireBCollection()) {
                Container commentC = new Container(BoxLayout.y());

                commentC.add(new Label(commentaire.getAuteurn()));

                Label texteC = new Label(commentaire.getText());
                texteC.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
                commentC.add(texteC);
                commentairesContainer.add(commentC);
            }
        }

        
        repaint();
       
    }
}
