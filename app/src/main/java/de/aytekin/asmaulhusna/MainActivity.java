package de.aytekin.asmaulhusna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import de.aytekin.asmaulhusna.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final public static String SHAREDPREFS_NAME = "EsmaUlHusna";

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;

    public static boolean   transcriptionEN = true;
    public static boolean   transcriptionTR = true;
    public static boolean   meaningEN = true;
    public static boolean   meaningTR = true;
    public static boolean   detailsTR = false;
    public static boolean   arabic = true;
    public static boolean   number = true;
    public static boolean   soundID = true;
    public static int       beginningIndex = 0;
    public static int       endingIndex = 99;


    public static EsmaUlHusna esmaUlHusna = new EsmaUlHusna();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSettings();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_names, R.id.nav_quiz, R.id.nav_stats, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        loadSettings();

        navigationView.getHeaderView(0).findViewById(R.id.headerlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_about);
                closeDrawer();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSettings();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    private void saveSettings(){
        SharedPreferences sP = getBaseContext().getSharedPreferences(SHAREDPREFS_NAME,0);
        SharedPreferences.Editor sPE = sP.edit();

        Gson gson = new Gson();

        String s = gson.toJson(esmaUlHusna);
        sPE.putString("esmaUlHusna",s);
        sPE.putBoolean("transcriptionEN",transcriptionEN);
        sPE.putBoolean("transcriptionTR",transcriptionTR);
        sPE.putBoolean("meaningEN",meaningEN);
        sPE.putBoolean("meaningTR",meaningTR);
        sPE.putBoolean("detailsTR",detailsTR);
        sPE.putBoolean("arabic",arabic);
        sPE.putBoolean("number",number);
        sPE.putBoolean("soundID",soundID);
        sPE.putInt("beginningIndex",beginningIndex);
        sPE.putInt("endingIndex",endingIndex);


        sPE.commit();
    }

    private void loadSettings(){
        SharedPreferences sP = getBaseContext().getSharedPreferences(SHAREDPREFS_NAME,0);

        String json = sP.getString("esmaUlHusna",null);
        Type type = new TypeToken<EsmaUlHusna>() {}.getType();
        Gson gson = new Gson();

        esmaUlHusna = gson.fromJson(json, type);
        if(esmaUlHusna == null){
            esmaUlHusna = new EsmaUlHusna();
        }

        transcriptionEN = sP.getBoolean("transcriptionEN", true);
        transcriptionTR = sP.getBoolean("transcriptionTR", true);
        meaningEN = sP.getBoolean("meaningEN", true);
        meaningTR = sP.getBoolean("meaningTR", true);
        detailsTR = sP.getBoolean("detailsTR", false);
        arabic = sP.getBoolean("arabic", true);
        number = sP.getBoolean("number", true);
        soundID = sP.getBoolean("soundID", true);
        beginningIndex = sP.getInt("beginningIndex", 0);
        endingIndex = sP.getInt("endingIndex", 99);

    }

}