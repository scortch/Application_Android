package com.androidbelieve.materialnavigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pierre Brengues on 28/01/2016.
 */
public class AccesBDD {

    private final String URL_JSON = "https://boitej.ville-cugnaux.fr/wp-json/wp/v2/posts";
    private final String URL_JSON_MEDIA = "https://boitej.ville-cugnaux.fr/wp-json/wp/v2/media";
    private HashMap<String,String> tabMois = new HashMap<String,String>();

    private static HashMap<String,Bitmap> stockImages = new HashMap<String,Bitmap>();

    static String jsonStock;
    static ArrayList<JSONObject> listeJson = new ArrayList<JSONObject>();
    private final Context mainActivity;







    public AccesBDD(Context mainActivity){
        super();
        this.mainActivity = mainActivity;

        tabMois.put("01","Janvier");
        tabMois.put("02","Février");
        tabMois.put("03","Mars");
        tabMois.put("04","Avril");
        tabMois.put("05","Mai");
        tabMois.put("06","Juin");
        tabMois.put("07","Juillet");
        tabMois.put("08","Août");
        tabMois.put("09","Septembre");
        tabMois.put("10","Octobre");
        tabMois.put("11","Novembre");
        tabMois.put("12","Decembre");

        miseAjourJSON();

    }


    private void miseAjourJSON()
    {
        SharedPreferences sharedPref = mainActivity.getSharedPreferences("bdd", Context.MODE_PRIVATE);
        String bdd = sharedPref.getString("bdd", null);


        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    jsonStock = readJsonFromUrl(URL_JSON);

                    SharedPreferences sharedPref = mainActivity.getSharedPreferences("bdd",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("bdd", jsonStock);
                    editor.commit();
                }
                catch (Exception e)
                {
                    SharedPreferences sharedPref = mainActivity.getSharedPreferences("bdd", Context.MODE_PRIVATE);
                    jsonStock = sharedPref.getString("bdd", null);
                }
                listeJson = getListeJson();
            }
        });
        T.start();


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
        }catch(Exception e){
            //e.printStackTrace();
            return null;
        }finally {
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


    public Bitmap getImageArticle(String numArticle){

        if(this.stockImages.containsKey(numArticle)){
            return this.stockImages.get(numArticle);
        }
        Bitmap imgBitmap = getDrawableFromUrl(getAddresseImage(numArticle));
        if(imgBitmap != null){
            this.stockImages.put(numArticle,imgBitmap);

        }
        return imgBitmap;

    }



    private Bitmap getDrawableFromUrl(String url) {
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


    private String getAddresseImage(String numArticle){
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


    public String getDateArticle(String numArticle) {
        return reecrireDate(getStringFromJson("date", numArticle));
    }


    private String reecrireDate(String dateBrute){




        String annee = dateBrute.substring(0,3);
        String mois = tabMois.get(dateBrute.substring(5, 6));
        String jour = dateBrute.substring(8,9);


        return jour + " "+ mois + " "+ annee;
    }


    private String getStringFromJson(String idChamp, String numArticle){
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

    public String getDescriptionArticle(String numArticle){

        return getStringFromJson("content", numArticle).substring(0,35)+"...";
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
