package com.example.justmotor;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justmotor.ui.Comparador.ComparadorFragment;
import com.example.justmotor.ui.Favoritos.FavFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth auth;
    private FirebaseUser Usu = FirebaseAuth.getInstance().getCurrentUser();
    TextView NombreUsu, CorreoUsu;
    FirebaseFirestore Acceso = FirebaseFirestore.getInstance();


    String NombreUsuu = "";
    String CorreoUsuu = "";

    String Identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();


        //Toolbar derecho
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //Navegation entre frragments tanto drawer como bottom
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        NombreUsu = (TextView) headerView.findViewById(R.id.NombreUsuarioNavHe);
        CorreoUsu = (TextView) headerView.findViewById(R.id.CorreoUsuarioNavHe);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Filter, R.id.nav_comparador, R.id.navigation_Fav, R.id.nav_home, R.id.navigation_Buzon,
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





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Fragment fragment;
                //it's possible to do more actions on several items, if there is a large amount of items I prefer switch(){case} instead of if()
                if (id == R.id.salir){
                    if(Usu != null ){
                        FirebaseAuth.getInstance().signOut();
                        String l = String.valueOf(Usu);
                        Log.d("Usu despues de deslog" ,l);
                        Usu = null;
                        NombreUsu.setText("JustMotor");
                        CorreoUsu.setText("byjomsa@gmail.com");
                        if(Usu == null ){
                            Toast.makeText(getApplicationContext(), "Ya no estas logeado", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "NO estas registrado o logeado.", Toast.LENGTH_LONG).show();
                    }

                }else{
                    if(id == R.id.nav_comparador){
                        if(Usu != null ) {
                            fragment = new ComparadorFragment();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.nav_comparador, fragment);
                            ft.commit();
                            /*
                            Intent intent = new Intent(getApplicationContext(), ComparadorFragment.class);
                            startActivity(intent);

                             */
                            //NavHostFragment.findNavController(getApplication().navigate(R.id.nav_comparador);
                        }else{
                            if(Usu == null ){
                                Toast.makeText(getApplicationContext(), "NO estas registrado o logeado.", Toast.LENGTH_LONG).show();
                            }

                        }
                    }else{
                        if(id == R.id.reg){
                            if(Usu != null ){
                                Toast.makeText(getApplicationContext(), "Ya estas registrado o logeado.", Toast.LENGTH_LONG).show();
                            }else{
                                Intent i = new Intent(getApplicationContext(), LocginActivity.class);
                                startActivity(i);
                            }

                        }else{
                            if(id == R.id.nav_home){
                                fragment = new ComparadorFragment();
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.nav_home, fragment);
                                ft.commit();
                            }
                            else{
                                if( id == R.id.navigation_Fav){
                                    if(Usu != null){
                                        fragment = new FavFragment();
                                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                        ft.replace(R.id.navigation_Fav, fragment);
                                        ft.commit();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Ya estas registrado o logeado.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
                //This is for maintaining the behavior of the Navigation view
                if(Usu != null ){
                    NavigationUI.onNavDestinationSelected(menuItem,navController);
                }else{

                }

                //This is for closing the drawer after acting on it
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        if(Usu != null){
            MirarPersona();
        }else{
            NombreUsu.setText("JustMotor");
            CorreoUsu.setText("byjomsa@gmail.com");
        }

    }



    private void MirarPersona() {
        String Email = Usu.getEmail();
        Acceso.collection("Usuarios").whereEqualTo("Email", Email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        Identificador = document.getId();
                        NombreUsuu = (document.getData().get("Nombre").toString());
                        CorreoUsuu = (document.getData().get("Email").toString());



                        NombreUsu.setText(NombreUsuu);
                        CorreoUsu.setText(CorreoUsuu);

                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}