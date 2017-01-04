package com.example.corto_000.lemurrescuecenter2.business.mapping;

import com.example.corto_000.lemurrescuecenter2.model.LemurModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by corto_000 on 02/01/2017.
 */

public class MappingLemur {

    public static LemurModel mappingLemurModel(LemurModel lemurmodel, JSONObject resultPost) {

        JSONObject object = new JSONObject();
        try {
            object.put("idDB", new Integer(1));
            object.put("nom", "MANOU");
            object.put("numeroIdentification", "250228700001593");
            object.put("sexe", "M - Y1");
            object.put("dateDeNaissance", "");
            object.put("dateEntree", "7/6/2011");
            object.put("origine", "BEHOMPY TULEAR");
            object.put("natureEntree", "SAISIE");
            object.put("ancienProprietaire", "BEBY");
            object.put("dateSortie", "");
            object.put("natureSortie", "");
            object.put("commentaireSortie", "");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

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


        try{
            for ( int i=0 ; i< arrayLenght; i++) {
                lemurModel.setWeightDate(resultPost.getJSONObject(i).getString("date"));
                if(!resultPost.getJSONObject(i).getString("poids").equals("") && (!resultPost.getJSONObject(i).getString("poids").equals("null"))){
                    lemurModel.getWeight().add(resultPost.getJSONObject(i).getString("poids"));
                }
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return lemurModel;
    }
}
