package com.example.myfirstapp.ui.list_wine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;

import com.example.myfirstapp.DetailActivity;
import com.example.myfirstapp.OnRecyclerItemClickListener;
import com.example.myfirstapp.R;
import com.example.myfirstapp.Singleton;
import com.example.myfirstapp.Wine;
import com.example.myfirstapp.WineAdapter;

import java.util.Locale;

public class ListWineFragment extends Fragment implements OnRecyclerItemClickListener {

    private ListWineViewModel listWineViewModel;

    RecyclerView recyclerview_list_wine;
    RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listWineViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ListWineViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_list_wine, container, false);
        final TextView textView = root.findViewById(R.id.textview_fragment_list_wine_hello_user);
        listWineViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        layoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerview_list_wine = root.findViewById(R.id.recyclerview_list_wines);
        recyclerview_list_wine.setHasFixedSize(false);
        recyclerview_list_wine.setLayoutManager(layoutManager);

        recyclerview_list_wine.setAdapter(new WineAdapter(this));


        //search button
        ImageButton search_button = root.findViewById(R.id.list_wine_search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.log4Me("le bouton est cliqué la recherche doit être lancé et la page rechargée", "List_Search_On Clicked : ");
                search(((EditText) root.findViewById(R.id.editText_fragment_list_wine_search)).getText().toString());
                reload();
            }
        });

        return root;
    }

    @Override
    public void onRecyclerViewItemClick(View view, int position) {
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("wine_position", position);
        startActivity(intent);
    }

    private void search(String string) {
        Singleton.getInstance().wines_searched.clear();
        if (!string.equals("")) {
            System.out.println("length string "+string.length());
            for (Wine wine : Singleton.getInstance().wines) {
                if(wine.wine_name.toLowerCase(Locale.ROOT).equals(string.toLowerCase(Locale.ROOT))){
                    Singleton.getInstance().wines_searched.add(wine);
                }
                int length = wine.wine_name.length() - string.length();
                for (int i = 0; i < length; i++) {
                    if (wine.wine_name.substring(i, i+string.length()).toLowerCase(Locale.ROOT).equals(string.toLowerCase(Locale.ROOT))) {
                        if(wineNotInSearchedWine(wine)){
                            Singleton.getInstance().wines_searched.add(wine);
                        }
                    }
                }
            }
        } else {
            for (Wine wine : Singleton.getInstance().wines) {
                Singleton.getInstance().wines_searched.add(wine);
            }
        }
    }

    private void reload(){
        recyclerview_list_wine.setAdapter(new WineAdapter(this,true));
    }

    private boolean wineNotInSearchedWine(Wine wine) {
        System.out.println("entrée méthode");
        if(Singleton.getInstance().wines_searched.isEmpty()) {
            System.out.println("coucou");
            return true;
        }
        for (Wine list_wine : Singleton.getInstance().wines_searched) {
            System.out.println("enter for");
            if(!list_wine.wine_name.equals(wine.wine_name)) {
                System.out.println("coucou");
                return true;
            }
            System.out.println("oups");
        }
        return false;
    }
}