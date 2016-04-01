package com.androidbelieve.materialnavigationdrawer;

import android.graphics.Bitmap;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by Pierre Brengues on 05/02/2016.
 */
public class AccesBDDTest {

    AccesBDD bdd;

    @Before
    public void initialize() {
        bdd = new AccesBDD(null);
    }


    @Test
    public void testGetImageArticle() throws Exception {
        Bitmap image = bdd.getImageArticle("11");
        Assert.assertTrue(image != null);
    }

    @Test
    public void testGetDateArticle() throws Exception {
        String date = bdd.getDateArticle("11");
        Assert.assertTrue(date != null);
    }

    @Test
    public void testGetTitreArticle() throws Exception {
        String titre = bdd.getTitreArticle("11");
        Assert.assertTrue(titre != null);
    }

    @Test
    public void testGetDescriptionArticle() throws Exception {
        String desc = bdd.getDescriptionArticle("11");
        Assert.assertTrue(desc != null);
    }

    @Test
    public void testGetTexteArticle() throws Exception {
        String texte = bdd.getTexteArticle("11");
        Assert.assertTrue(texte != null);
    }

    @Test
    public void testGetNombreArticle() throws Exception {
        int nbArt = bdd.getNombreArticle("all");
        Assert.assertTrue(nbArt != -1);
    }

    @Test
    public void testGetIdArticle() throws Exception {
        String id = bdd.getIdArticle("all",1);
        Assert.assertTrue(id != null);
    }
}