package com.example.justmotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        //Toolbar derecho
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Boton central

        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */

        //Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //Navegation entre frragments tanto drawer como bottom
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Filter, R.id.nav_comparador, R.id.navigation_Fav, R.id.navigation_Home, R.id.navigation_Buzon,
                R.id.navigation_Opciones)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Bottom navegation
        BottomNavigationView bottom_nav_view = findViewById(R.id.bottomNavigationView);
        bottom_nav_view.setBackground(null);
        NavigationUI.setupWithNavController(bottom_nav_view, navController);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

 */





    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


   public  boolean OnNavegationItemSelected(MenuItem item) {
       int id = item.getItemId();

       Intent intent;

       if (id == R.id.Salir) {
           auth.signOut();
           //menu = navigationView.getMenu();
           //MenuItem visi = menu.findItem(R.id.Salir);
           //visi.setVisible(isChequed)
       }

            /*
        } else if (id == R.id.double_phase) {
            Toast.makeText(this, "Doble Fase", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.penalization) {
            Toast.makeText(this, "Penalizacion", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.what_is_operative_research) {
            what_is_operative_research what_is_operative_research = new what_is_operative_research();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, what_is_operative_research).commit();
        } else if (id == which_is_the_simplex_method) {
            which_is_the_simplex_method which_id_the_simplex_method = new which_is_the_simplex_method();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, which_id_the_simplex_method).commit();
        } else if (id == R.id.which_is_the_method_of_the_double_phase) {
            which_is_the_method_of_the_double_phase which_is_the_method_of_the_double_phase = new which_is_the_method_of_the_double_phase();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, which_is_the_method_of_the_double_phase).commit();
        } else if (id == R.id.which_is_the_method_of_penalization){
            which_is_the_method_of_penalization which_is_the_method_of_penalization = new which_is_the_method_of_penalization();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, which_is_the_method_of_penalization).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

*/return true;
    }




}