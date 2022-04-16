package de.aytekin.asmaulhusna;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Random;

import de.aytekin.asmaulhusna.MainActivity;
import de.aytekin.asmaulhusna.R;
import de.aytekin.asmaulhusna.databinding.FragmentGalleryBinding;
import de.aytekin.asmaulhusna.databinding.SettingsFragmentBinding;

public class SettingsFragment extends Fragment {
    private SettingsFragmentBinding binding;

    EditText beginning_text;
    EditText ending_text;

    CheckBox number_checkbox;
    CheckBox arabic_checkbox;
    CheckBox transEn_checkbox;
    CheckBox transTr_checkbox;
    CheckBox meaningEn_checkbox;
    CheckBox meaningTr_checkbox;
    //CheckBox detailsTr_checkbox;
    CheckBox sound_checkbox;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beginning_text = getView().findViewById(R.id.editTextNumber);
        ending_text = getView().findViewById(R.id.editTextNumber2);
        number_checkbox = getView().findViewById(R.id.number_checkbox);
        arabic_checkbox = getView().findViewById(R.id.arabic_checkbox);
        transEn_checkbox = getView().findViewById(R.id.transen_checkbox);
        transTr_checkbox = getView().findViewById(R.id.transtr_checkbox);
        meaningEn_checkbox = getView().findViewById(R.id.meaningen_checkbox);
        meaningTr_checkbox = getView().findViewById(R.id.meaningtr_checkbox);
        //detailsTr_checkbox = getView().findViewById(R.id.detailstr_checkbox);
        sound_checkbox = getView().findViewById(R.id.sound_checkbox);

        beginning_text.setText(String.valueOf(MainActivity.beginningIndex));
        ending_text.setText(String.valueOf(MainActivity.endingIndex));
        number_checkbox.setChecked(MainActivity.number);
        arabic_checkbox.setChecked(MainActivity.arabic);
        transEn_checkbox.setChecked(MainActivity.transcriptionEN);
        transTr_checkbox.setChecked(MainActivity.transcriptionTR);
        meaningEn_checkbox.setChecked(MainActivity.meaningEN);
        meaningTr_checkbox.setChecked(MainActivity.meaningTR);
        //detailsTr_checkbox.setChecked(MainActivity.detailsTR);
        sound_checkbox.setChecked(MainActivity.soundID);


        number_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.number = number_checkbox.isChecked();
            }
        });
        arabic_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.arabic = arabic_checkbox.isChecked();
            }
        });
        transEn_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.transcriptionEN = transEn_checkbox.isChecked();
            }
        });
        transTr_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.transcriptionTR = transTr_checkbox.isChecked();
            }
        });
        meaningEn_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.meaningEN = meaningEn_checkbox.isChecked();
            }
        });
        meaningTr_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.meaningTR = meaningTr_checkbox.isChecked();
            }
        });
        /*detailsTr_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.detailsTR = detailsTr_checkbox.isChecked();
            }
        });

         */
        sound_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.soundID = sound_checkbox.isChecked();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();

        if(beginning_text.getText().toString().equals("")){
            MainActivity.beginningIndex = 0;
        }else if(Integer.parseInt(beginning_text.getText().toString()) > MainActivity.endingIndex ||
                Integer.parseInt(beginning_text.getText().toString()) > 99){
            MainActivity.beginningIndex = MainActivity.endingIndex;
        }else{
            MainActivity.beginningIndex = Integer.parseInt(beginning_text.getText().toString());
        }


        if(ending_text.getText().toString().equals("")){
            MainActivity.endingIndex = 99;
        }else if(Integer.parseInt(ending_text.getText().toString()) < MainActivity.beginningIndex){
            MainActivity.endingIndex = MainActivity.beginningIndex;
        }else if(Integer.parseInt(ending_text.getText().toString()) > 99){
            MainActivity.endingIndex = 99;
        }else{
            MainActivity.endingIndex = Integer.parseInt(ending_text.getText().toString());
        }
    }
}