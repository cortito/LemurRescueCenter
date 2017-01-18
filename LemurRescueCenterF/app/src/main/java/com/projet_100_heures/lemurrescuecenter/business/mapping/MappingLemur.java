package com.projet_100_heures.lemurrescuecenter.business.mapping;

import android.util.Log;

import com.projet_100_heures.lemurrescuecenter.model.LemurModel;
import com.projet_100_heures.lemurrescuecenter.model.PoidsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by corto_000 on 05/01/2017.
 */

public class MappingLemur {

    public static LemurModel mappingLemurModel(LemurModel lemurmodel, JSONObject resultPost) {
        try {
            lemurmodel.setIdDB(resultPost.getInt("idDB"));
            lemurmodel.setName(resultPost.getString("nom"));
            lemurmodel.setIdLemur(resultPost.getString("numeroIdentification"));
            lemurmodel.setGender(resultPost.getString("sexe"));
            lemurmodel.setBirthDate(resultPost.getString("dateDeNaissance"));
            lemurmodel.setEntryDate(resultPost.getString("dateEntree"));
            lemurmodel.setOrigin(resultPost.getString("origine"));
            lemurmodel.setEntryNature(resultPost.getString("natureEntree"));
            lemurmodel.setLastOwner(resultPost.getString("ancienProprietaire"));
            lemurmodel.setLeaveDate(resultPost.getString("dateSortie"));
            lemurmodel.setLeaveNature(resultPost.getString("natureSortie"));
            lemurmodel.setLeaveCommentary(resultPost.getString("commentaireSortie"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return lemurmodel;
    }
    public static LemurModel mappingWeightLemurModel (LemurModel lemurModel, JSONArray resultPost) {
        Integer arrayLenght = resultPost.length();
        List<PoidsModel> poidsModels = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int anneeCourante = calendar.get(Calendar.YEAR) -2000;
        try{
            for(int annee = 15 ; annee <= anneeCourante; annee++){
                for(int mois = 1 ; mois <= 12; mois++){
                    String moisStr = mois < 10 ? "0" + String.valueOf(mois) : String.valueOf(mois);
                    lemurModel.getWeightDate().add(moisStr+"/"+annee);
                    int i =0;
                    for( i = 0 ; i< arrayLenght; i++){
                        if(resultPost.getJSONObject(i).get("date").equals(moisStr + "/" + annee)) {
                            if(resultPost.getJSONObject(i).getString("poids").equals("")){
                                lemurModel.getWeight().add("0.00");
                            }else {
                                lemurModel.getWeight().add(resultPost.getJSONObject(i).getString("poids"));

                            }
                            break;
                        }
                    }
                    if(i == arrayLenght){
                        lemurModel.getWeight().add("0.00");
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lemurModel;
    }

    public static LemurModel updateLemurModel (LemurModel lemurModel, JSONObject resultEdit) {

        try {
            for (String key : iteratorToIterable(resultEdit.keys())) {
                if (!resultEdit.getString(key).equals("") && !resultEdit.getString(key).equals("null")) {
                    switch (key) {
                        case "nom":
                            lemurModel.setName(resultEdit.getString("nom"));
                            break;
                        case "numeroIdentification":
                            lemurModel.setIdLemur(resultEdit.getString("numeroIdentification"));
                            break;
                        case "sexe":
                            lemurModel.setGender(resultEdit.getString("sexe"));
                            break;
                        case "dateDeNaissance":
                            lemurModel.setBirthDate(resultEdit.getString("dateDeNaissance"));
                            break;
                        case "idDB":
                            lemurModel.setIdDB(resultEdit.getInt("idDB"));
                            break;
                        case "dateEntree":
                            lemurModel.setEntryDate(resultEdit.getString("dateEntree"));
                            break;
                        case "origine":
                            lemurModel.setOrigin(resultEdit.getString("origine"));
                            break;
                        case "natureEntree":
                            lemurModel.setEntryNature(resultEdit.getString("natureEntree"));
                            break;
                        case "ancienProprietaire":
                            lemurModel.setLastOwner(resultEdit.getString("ancienProprietaire"));
                            break;
                        case "dateSortie":
                            lemurModel.setLeaveDate(resultEdit.getString("dateSortie"));
                            break;
                        case "natureSortie":
                            lemurModel.setLeaveNature(resultEdit.getString("natureSortie"));
                            break;
                        case "commentaireSortie":
                            lemurModel.setLeaveCommentary(resultEdit.getString("commentaireSortie"));
                            break;
                        default:
                            Log.d("TAG", "Mauvaise cle donnee pour l'update");
                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return lemurModel;

    }

    private static <T> Iterable<T> iteratorToIterable(final Iterator<T> iterator) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterator;
            }
        };
    }
}
