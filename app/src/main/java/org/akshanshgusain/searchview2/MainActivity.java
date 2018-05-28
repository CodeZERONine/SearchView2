package org.akshanshgusain.searchview2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.akshanshgusain.searchview2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Adapter adapter;
    ArrayList<String> countries;
    private String currentQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        binding.searchView.setVoiceSearch(true);
      //  binding.searchView.setVoiceIcon(R.drawable.ic_keyboard_voice_black_24dp);
       // binding.searchView.setCursorDrawable(R.drawable.ic_keyboard_voice_black_24dp);

        setupList();
        setupSearch();
    }

    private void setupSearch() {
           binding.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
               @Override
               public boolean onQueryTextSubmit(String query) {
                   processQuery(query);
                   return true;
               }

               @Override
               public boolean onQueryTextChange(String newText) {
                  // processQuery(newText);
                   return false;
               }
           });
           /////  /____________TO SHOW SEARCHED QUERY_________________________/////////
           binding.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
               @Override
               public void onSearchViewShown() {
                          binding.searchView.setQuery(currentQuery,false);
               }

               @Override
               public void onSearchViewClosed() {
                     currentQuery="";
                     adapter.setCountires(countries);
               }
           });


    }

    private void processQuery(String query) {
        currentQuery=query;
        ArrayList<String> result=new ArrayList<>();
        for(String country:countries){
            if(country.toLowerCase().contains(query.toLowerCase())){
                   result.add(country);
            }
        }
      adapter.setCountires(result);
    }

    private void setupList() {
        populateList();
        adapter=new Adapter(this,countries);
        binding.include.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.include.recycler.setAdapter(adapter);


    }

    private void populateList() {
        countries=new ArrayList<>();
        countries.add("India");
        countries.add("Australia");
        countries.add("canada");
        countries.add("England");
        countries.add("itly");
        countries.add("Russia");
        countries.add("Germany");
        countries.add("USA");
        countries.add("SouthAfrica");
        countries.add("Mexico");
        countries.add("portugal");
        countries.add("japan");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        binding.searchView.setMenuItem(menu.findItem(R.id.action_settings));
        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    binding.searchView.setQuery(searchWrd, false);
                    Toast.makeText(this, "ACtivityReturn", Toast.LENGTH_SHORT).show();
                }
            }

            //return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    


}
