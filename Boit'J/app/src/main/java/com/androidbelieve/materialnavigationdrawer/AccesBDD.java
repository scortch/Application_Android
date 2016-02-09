package com.gnirt69.applicugnaux;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Pierre Brengues on 28/01/2016.
 */
public class AccesBDD{

    private final String URL_JSON = "https://boitej.ville-cugnaux.fr/wp-json/wp/v2/posts";
    private final String URL_JSON_MEDIA = "https://boitej.ville-cugnaux.fr/wp-json/wp/v2/media";

    private String jsonStock;
    private ArrayList<JSONObject> listeJson;



    private void miseAjourJSON() {
        if(this.listeJson != null && this.listeJson.size() != 0){
            return;
        }
        try {
            jsonStock = readJsonFromUrl(URL_JSON);
            this.listeJson = getListeJson();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Erreur de reception du JSON");
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private String readJsonFromUrl(String url)throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            return readAll(rd);
        } finally {
            is.close();
        }
    }

    private ArrayList<JSONObject> getListeJson(){

        if(jsonStock == null){
            return null;
        }
        ArrayList<JSONObject> listeJson= new ArrayList<JSONObject>();

        String res = "";
        int count = 0;
        int debut = -1;
        for (int i = 0; i < jsonStock.length(); i++){
            if(jsonStock.charAt(i)=='{'){
                count++;
                if(debut == -1){
                    debut = i;
                }
            }
            if(jsonStock.charAt(i)=='}'){
                count--;
            }
            if(debut != -1) {
                res+=jsonStock.charAt(i);

                if (count == 0) {
                    //System.out.println(res);
                    try {
                        listeJson.add(new JSONObject(res));
                    } catch (JSONException e) {
                        //e.printStackTrace();
                    }
                    count = 0;
                    debut = -1;
                    res="";
                }
            }
        }
        return  listeJson;
    }

    public Bitmap getDrawableFromUrl(String url) {
        try {
            StringBuffer sb = new StringBuffer(url);
            sb.insert(4,'s');
            url = sb.toString();
            InputStream in;
            in = new URL(url).openStream();
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }


    public String getAddresseImage(String numArticle){
        try {
            String id_image = "";

            for(int i = 0; i < this.listeJson.size(); i++){
                if(this.listeJson.get(i).getString("id").equals(numArticle)){
                    id_image = this.listeJson.get(i).getString("featured_media");
                }
            }
            String jsonImage = readJsonFromUrl(URL_JSON_MEDIA+"/"+id_image);
            JSONObject jsonObjImage = new JSONObject(jsonImage);

            String image_url = jsonObjImage.getJSONObject("guid").getString("rendered");

            return image_url;
            //return "<img src=\""+image_url+"\">";

        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }

    }



    private String getStringFromJson(String idChamp, String numArticle){
        miseAjourJSON();
        try {

            for(int i = 0; i < this.listeJson.size(); i++){
                if(this.listeJson.get(i).getString("id").equals(numArticle)){

                    JSONObject obj = this.listeJson.get(i).getJSONObject(idChamp);
                    return obj.getString("rendered");
                }
            }
        } catch (Exception e) {
            return "ERREUR";
        }
        return "article introuvable";
    }


    public String getTitreArticle(String numArticle){
        return getStringFromJson("title", numArticle);
    }


    public String getTexteArticle(String numArticle){

        return getStringFromJson("content", numArticle);
    }

    /**
     * Permet de connaitre le nombre d'articles dans
     * une catégorie donnée
     * @param categorie id de la catégorie
     * @return le nombre d"articles dans cette catégorie
     */
    public int getNombreArticle(String categorie) {
        miseAjourJSON();

        if(categorie == "all"){
            return this.listeJson.size();
        }

        int compteur = 0;
        try {
            for(int i = 0; i < this.listeJson.size(); i++){
                if(this.listeJson.get(i).getString("categories").equals(categorie)){
                    compteur++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compteur;
    }

    /**
     * Permet de récuperer l'ID d'un article par rapport à sa position
     * dans les articles de la catégorie
     * (autrement dit: dans la liste des articles d'une catégorie, on
     * veut recuperer par exemple l'id du 3eme article affiché)
     * @param categorie L'ID de la categorie des articles
     * @param numArticleDansCat Le positionnement de l'article dans la catégorie
     * @return l'id de l'article
     */
    public String getIdArticle(String categorie, int numArticleDansCat) {
        miseAjourJSON();
        int compteur = 0;
        try {
            for(int i = 0; i < this.listeJson.size(); i++){
                if(this.listeJson.get(i).getString("categories").equals(categorie) || categorie.equals("all")){
                    if(compteur == numArticleDansCat){
                        return this.listeJson.get(i).getString("id");
                    }
                    compteur++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
