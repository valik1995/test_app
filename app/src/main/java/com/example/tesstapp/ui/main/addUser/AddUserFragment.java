package com.example.tesstapp.ui.main.addUser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.RequestManager;
import com.example.tesstapp.R;
import com.example.tesstapp.model.User;
import com.example.tesstapp.ui.main.MainInterface;
import com.example.tesstapp.ui.main.userList.MainViewModel;
import com.example.tesstapp.viewModel.ViewModelsProvirderFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class AddUserFragment extends DaggerFragment implements View.OnClickListener {

    private static final String TAG = "AddUserFragment";

    @Inject
    ViewModelsProvirderFactory provirderFactory;

    private AddUserViewModel viewModel;
    private MainInterface mainInterface;
    private FloatingActionButton fab;

    @Inject
    Drawable nav_header;

    @Inject
    RequestManager requestManager;

    private Button buttonSave;
    private TextInputEditText name, forname, middlename, phone_number, age;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_add_user, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, provirderFactory).get(AddUserViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        buttonSave = view.findViewById(R.id.save_user);
        name = view.findViewById(R.id.et_name);
        forname = view.findViewById(R.id.et_forname);
        middlename = view.findViewById(R.id.et_middlename);
        phone_number = view.findViewById(R.id.et_phonenumber);
        age = view.findViewById(R.id.et_age);
        buttonSave.setOnClickListener(this);
        fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        setLogo(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainInterface = (MainInterface) getActivity();
    }

    private void setLogo(View view){
        requestManager
                .load(nav_header)
                .into((ImageView)view.findViewById(R.id.imagePhoto));
    }



    private void saveUser(String name, String forname, String middlename, String phone_number, String age){

        if(!name.equals("") && !forname.equals("") && !middlename.equals("") && !phone_number.equals("") && !age.equals("")){
            User user = new User(name, forname, middlename, phone_number, Integer.valueOf(age));
            viewModel.insert(user);
            Toast.makeText(getActivity(), "User is saved.", Toast.LENGTH_SHORT).show();
            setEmpty();
        }else {
            Toast.makeText(getActivity(), "Fill all fields..", Toast.LENGTH_SHORT).show();
        }
        
    }

    private void setEmpty(){
        name.setText("");
        forname.setText("");
        middlename.setText("");
        phone_number.setText("");
        age.setText("");
    }

    @Override
    public void onClick(View view) {
        observingLoadingState();
        saveUser(
                name.getText().toString().trim(),
                forname.getText().toString().trim(),
                middlename.getText().toString().trim(),
                phone_number.getText().toString().trim(),
                age.getText().toString().trim()
        );
//        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(
//                R.id.listUserFragment);


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
}
