package com.projet_100_heures.lemurrescuecenter.activity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.androidifygeeks.library.fragment.PageFragment;
import com.androidifygeeks.library.fragment.TabDialogFragment;
import com.androidifygeeks.library.iface.IFragmentListener;
import com.androidifygeeks.library.iface.ISimpleDialogListener;
import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.activity.fragments.CustomFragment;
import com.projet_100_heures.lemurrescuecenter.activity.fragments.NFCFragment;
import com.projet_100_heures.lemurrescuecenter.activity.fragments.ProfileFragment;
import com.projet_100_heures.lemurrescuecenter.activity.fragments.StatsFragment;
import com.projet_100_heures.lemurrescuecenter.business.alertDialog.SearchLemurDialog;
import com.projet_100_heures.lemurrescuecenter.business.dao.RetrieveLemurTask;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONException;
import org.json.JSONObject;

public  class MainActivity extends AppCompatActivity implements ISimpleDialogListener,IFragmentListener,RetrieveLemurTask.LemurListenner, SearchLemurDialog.SearchLemurIdListenner, Communicator {

    LemurModel lemurModelBuf;
    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigationView;
    Fragment fragment;
    ProfileFragment pF;
    StatsFragment statsFragment;
    CustomFragment customFragment;
    NFCFragment nfcFragment;
    private int selector;
    NfcAdapter nfcAdapter;
    private int inter=0;
    Menu menu;

    EditText searchId;
    EditText searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logolrc2);


        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        selector = 1;
        pF = new ProfileFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.act_main,pF,"ProfilFrag1" );
        fragmentTransaction.commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.profileLemur) {

                    if(menu.size() == 0) {
                        getMenuInflater().inflate(R.menu.menu_main, menu);
                    }
                    selector = 1;
                    pF = new ProfileFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.act_main, pF,"profileFrag");
                    ft.commit();
                    pF.sendLemur(lemurModelBuf);

                }
                if(id == R.id.poids_bouton) {

                    menu.removeItem(R.id.searchLemur);
                    selector = 2;
                    statsFragment = new StatsFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.act_main, statsFragment,"statsFrag");
                    ft.commit();
                }
                if(id == R.id.nfc_bouton) {
                    selector = 3;

                    menu.removeItem(R.id.searchLemur);
                    nfcFragment = new NFCFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.act_main, nfcFragment,"nfcFrag");
                    ft.commit();


                }
                if(id == R.id.modifier_bouton) {

                    if(lemurModelBuf != null) {
                        menu.removeItem(R.id.searchLemur);
                        selector = 4;
                        customFragment = new CustomFragment();
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.act_main, customFragment, "customFrag");
                        ft.commit();
                        customFragment.sendLemurCustom(lemurModelBuf);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Cherchez un lémurien avant de faire une modification ! ", Toast.LENGTH_SHORT).show();

                        menu.removeItem(R.id.searchLemur);

                    }

                }
                return true;
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Parcelable[] parcelables = intent.getParcelableArrayExtra(nfcAdapter.EXTRA_NDEF_MESSAGES);

        if(parcelables != null && parcelables.length >0) {
            if(inter == 1 ) {
                nfcFragment.isTagDiscovered(true);
                nfcFragment.getTextFromNDEFMessage((NdefMessage) parcelables[0]);
            }
        }
        else {
            Toast.makeText(this, "no NDEFMessage found !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        if(selector == 1) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.searchLemur) {

            TabDialogFragment.createBuilder(this,getSupportFragmentManager())
                    .setTitle("Recherche Lémurien")
                    .setTabButtonText(new CharSequence[]{"ID", "Nom"})
                    .setPositiveButtonText("Chercher")
                    .setNegativeButtonText("Quitter")
                    .setRequestCode(42)
                    .show();

           /* SearchLemurDialog alertDialog = new SearchLemurDialog();
            alertDialog.setmListener(MainActivity.this);
            alertDialog.show(getFragmentManager(), TAG);*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLemurRetrieved(LemurModel lemurModel) {

        lemurModelBuf= new LemurModel();
        lemurModelBuf=lemurModel;

        if(selector == 1) {

                pF.sendLemur(lemurModelBuf);
                pF.displayLemurProf();
        }

        bottomNavigationView.getMenu().getItem(3).setEnabled(true);
        bottomNavigationView.getMenu().getItem(1).setEnabled(true);

    }

    @Override
    public void onIDBRetrieved(String idDB) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idDB", idDB);
        }catch (JSONException e) {
            Log.e("Jobect", e.getMessage());
        }

        RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(MainActivity.this,this);
        //retrieveLemurTask.execute(jsonObject);
        retrieveLemurTask.execute();
    }

    @Override
    public void sendSelector(int selector) {

        inter = selector;

    }


    @Override
    public void onFragmentViewCreated(Fragment fragment) {
        int selectedTabPosition = fragment.getArguments().getInt(PageFragment.ARG_DAY_INDEX, 0);
        View rootContainer = fragment.getView().findViewById(R.id.root_container);
        Log.i(TAG, "Position: " + selectedTabPosition);

        switch (selectedTabPosition) {
            case 0:
                // add view in container for first tab
                View tabProductDetailLayout = getLayoutInflater().inflate(R.layout.dialog_layout_id, (ViewGroup) rootContainer);

                searchId = (EditText) tabProductDetailLayout.findViewById(R.id.id_search_lemur);


                break;
            case 1:
                // add view in container for second tab
                View tabProductDetailLayout2 = getLayoutInflater().inflate(R.layout.dialog_layout_name, (ViewGroup) rootContainer);
                searchName = (EditText) tabProductDetailLayout2.findViewById(R.id.name_search_lemur);

                break;
        }
    }

    @Override
    public void onFragmentAttached(Fragment fragment) {

    }

    @Override
    public void onFragmentDetached(Fragment fragment) {

    }

    @Override
    public void onPositiveButtonClicked(int requestCode) {

        if(searchId != null){


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("idDB", searchId.getText());
            }catch (JSONException e) {
                Log.e("Jobect", e.getMessage());
            }

            RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(MainActivity.this,this);
            //retrieveLemurTask.execute(jsonObject);
            retrieveLemurTask.execute();

        }


        if(searchName != null){

            if(searchName.getText() != null) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nom", searchName.getText().toString().toUpperCase());
                } catch (JSONException e) {
                    Log.e("Jobect", e.getMessage());
                }

                RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(MainActivity.this, this);
                //retrieveLemurTask.execute(jsonObject);
                retrieveLemurTask.execute();
            }

        }
    }

    @Override
    public void onNegativeButtonClicked(int requestCode) {

    }

    @Override
    public void onNeutralButtonClicked(int requestCode) {

    }
}
