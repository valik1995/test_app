package com.example.tesstapp.ui.main.userList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesstapp.R;
import com.example.tesstapp.adapters.RecyclerAdapter;
import com.example.tesstapp.model.User;
import com.example.tesstapp.ui.main.MainInterface;
import com.example.tesstapp.viewModel.ViewModelsProvirderFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListUserFragment extends DaggerFragment implements View.OnClickListener {

    private static final String TAG = "ListUserFragment";

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private EditText searchView;
    private FloatingActionButton fab;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String stateForSearch;

    @Inject
    ViewModelsProvirderFactory provirderFactory;

    @Inject
    RecyclerAdapter recyclerAdapter;

    private MainInterface mainInterface;

    private MainViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_list_user_fragment, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, provirderFactory).get(MainViewModel.class);
       recyclerView = view.findViewById(R.id.recyclerView);
       toolbar = view.findViewById(R.id.search_toolbar);
       searchView = getActivity().findViewById(R.id.etSearchWords);
       fab = getActivity().findViewById(R.id.fab);
       fab.setVisibility(View.VISIBLE);
       fab.setOnClickListener(this);
        initRecyclerView();
        observingLoadingState();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainInterface = (MainInterface) getActivity();
    }



    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
        initUsers();
        initSearchListener();
    }

    private void initSearchListener(){

        searchView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = searchView.getText().toString().toLowerCase(Locale.getDefault());
                if(text != null){
                    implementSearch(text);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = searchView.getText().toString().toLowerCase(Locale.getDefault());
                if(text != null){
                    implementSearch(text);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = searchView.getText().toString().toLowerCase(Locale.getDefault());
                if(text != null){
                    implementSearch(text);
                }
            }

        });

    }

    private void implementSearch(String text){
        stateForSearch = mainInterface.setStateForSearch();
        if(text == null){
            initUsers();
        }else{
            initSearchUsers(text, stateForSearch);
        }
    }


    private void initSearchUsers(String word, String criterian){
        observingLoadingState();
        //Disposable for avoid memory leak
        Disposable disposable = viewModel.search(word, stateForSearch).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        Log.d(TAG, "accept: Called");
                        recyclerAdapter.setUsers(users);
                    }
                });

        //Add Disposable
        compositeDisposable.add(disposable);

    }

    private void initUsers(){
        observingLoadingState();
        //Disposable for avoid memory leak
        Disposable disposable = viewModel.getAllUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        Log.d(TAG, "accept: Called");
                        recyclerAdapter.setUsers(users);
                    }
                });
        //Add Disposable
        compositeDisposable.add(disposable);

    }

    private void observingLoadingState(){
        //Check Loading State
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.d(TAG, "onChanged: "+aBoolean);
                if (aBoolean!=null){
                    if (aBoolean){
                        mainInterface.setVesible();
                    }
                    else {
                        mainInterface.setInvisible();
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:{

                viewModel.deleteAllUsers();
                break;
            }
        }
    }
}
