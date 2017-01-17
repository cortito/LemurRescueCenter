package com.projet_100_heures.lemurrescuecenter.activity.fragments;

//import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

public class ProfileFragment extends Fragment {

    LemurModel lemurModel;
    private final int Tag=42;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayLemurProf();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(lemurModel != null){
        outState.putString("model",lemurModel.toJSON());
        }
    }

    public void displayLemurProf(){

        if(lemurModel != null) {
            TextView name = (TextView) getActivity().findViewById(R.id.lemurName);
            if (!lemurModel.getName().equals("")) {
                name.setText(lemurModel.getName());
            } else {
                name.setText("Non déterminé");
            }

            TextView age = (TextView) getActivity().findViewById(R.id.lemureAge);
            if (!lemurModel.getBirthDate().equals("")) {
                age.setText(lemurModel.getBirthDate());
            } else {
                age.setText("Non déterminé");
            }

            TextView sexe = (TextView) getActivity().findViewById(R.id.lemurGender);
            if (!lemurModel.getGender().equals("")) {
                sexe.setText(lemurModel.getGender());
            } else {
                sexe.setText("Non déterminé");
            }

            TextView numIdentification = (TextView) getActivity().findViewById(R.id.num_Identification);
            if (!lemurModel.getIdLemur().equals("")) {
                numIdentification.setText(lemurModel.getIdLemur());
            } else {
                numIdentification.setText("Non déterminé");
            }

            TextView origine = (TextView) getActivity().findViewById(R.id.LemurOrigin);
            if (!lemurModel.getOrigin().equals("")) {
                origine.setText(lemurModel.getOrigin());
            } else {
                origine.setText("Non déterminé");
            }

            TextView entryDate = (TextView) getActivity().findViewById(R.id.lemurEntryDate);
            if (!lemurModel.getEntryDate().equals("")) {
                entryDate.setText(lemurModel.getEntryDate());
            } else {
                entryDate.setText("Non déterminé");
            }

            TextView entryNature = (TextView) getActivity().findViewById(R.id.LemurEntryNature);
            if (!lemurModel.getEntryNature().equals("")) {
                entryNature.setText(lemurModel.getEntryNature());
            } else {
                entryNature.setText("Non déterminé");
            }

            TextView lastOwner = (TextView) getActivity().findViewById(R.id.LemurLastOwner);
            if (!lemurModel.getLastOwner().equals("")) {
                lastOwner.setText(lemurModel.getLastOwner());
            } else {
                lastOwner.setText("Non déterminé");
            }

            TextView leavingDate = (TextView) getActivity().findViewById(R.id.LemurLeavingDate);
            if (!lemurModel.getLeaveDate().equals("")) {
                leavingDate.setText(lemurModel.getLeaveDate());
            } else {
                leavingDate.setText("Non déterminé");
            }

            TextView leavingNature = (TextView) getActivity().findViewById(R.id.LemurLeavingNature);
            if (!lemurModel.getLeaveNature().equals("")) {
                leavingNature.setText(lemurModel.getLeaveNature());
            } else {
                leavingNature.setText("Non déterminé");
            }
            TextView leavingCommentary = (TextView) getActivity().findViewById(R.id.LemurLeavingCommentary);
            if (!lemurModel.getLeaveCommentary().equals("")) {
                leavingCommentary.setText(lemurModel.getLeaveCommentary());
            } else {
                leavingCommentary.setText("Non déterminé");
            }
        }

    }

    public void sendLemur(LemurModel lemurModel) {
        this.lemurModel = lemurModel;
    }

}
