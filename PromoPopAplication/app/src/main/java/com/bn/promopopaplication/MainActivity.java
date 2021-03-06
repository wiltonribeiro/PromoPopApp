package com.bn.promopopaplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, home.OnFragmentInteractionListener,
        map.OnFragmentInteractionListener, sales.OnFragmentInteractionListener, ProductList.OnFragmentInteractionListener, ProductGrid.OnFragmentInteractionListener{

    private DrawerLayout drawerLayout;
    private BottomNavigationView navigation;

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new home()).commit();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new ProductGrid()).commit();

        //fragmentTransaction.add(R.id.fragment_container, new ProductGrid()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.map:
                //Aqui eu  'seto' o navigation para a posição em que o fragment do map se encontra, para isso deixei o
                //navigation global e o deixei a inicialização no onCreate
                navigation.setSelectedItemId(R.id.navigation_map);
                break;
            case R.id.list:
                startActivity(new Intent(this, WishList.class));
                break;
            case R.id.login:
                startActivity(new Intent(this, Login.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new home();
                    break;
                case R.id.navigation_map:
                    selectedFragment = new map();
                    break;
                case R.id.navigation_sales:
                    selectedFragment = new sales();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();

            return true;
        }
    };


    public void showCategories(View view){
        registerForContextMenu(view);
        openContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.categories_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.bebes:
                Toast.makeText(this, item.getTitle()+" evento aciodado pela activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bebidas:
                Toast.makeText(this, item.getTitle()+" evento aciodado pela activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hobbies:
                Toast.makeText(this, item.getTitle()+" evento aciodado pela activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.eletronicos:
                Toast.makeText(this, item.getTitle()+" evento aciodado pela activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.eletrodomesticos:
                Toast.makeText(this, item.getTitle()+" evento aciodado pela activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.games:
                Toast.makeText(this, item.getTitle()+" evento aciodado pela activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mais:
                Toast.makeText(this, item.getTitle()+" evento aciodado pela activity", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onContextItemSelected(item);
    }


    public void showProduct(View view){
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }

}
