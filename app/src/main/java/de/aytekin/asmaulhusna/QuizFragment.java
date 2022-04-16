package de.aytekin.asmaulhusna;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import de.aytekin.asmaulhusna.Esma.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;
import java.util.Random;

import de.aytekin.asmaulhusna.Esma;
import de.aytekin.asmaulhusna.MainActivity;
import de.aytekin.asmaulhusna.R;
import de.aytekin.asmaulhusna.databinding.FragmentGalleryBinding;

public class QuizFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ArrayList<Esma> list;
    private Esma topEsma = null;


    private TextView topText;
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_next;
    private ImageView audio_top;
    private ImageView audio_0;
    private ImageView audio_1;
    private ImageView audio_2;
    private ImageView audio_3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = MainActivity.esmaUlHusna.getRandomList();
        topEsma = list.get(new Random().nextInt(list.size()));

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

        topText  = getView().findViewById(R.id.topText);
        button_0 = getView().findViewById(R.id.button_1);
        button_1 = getView().findViewById(R.id.button_2);
        button_2 = getView().findViewById(R.id.button_3);
        button_3 = getView().findViewById(R.id.button_4);
        audio_top = getView().findViewById(R.id.audio_top);
        audio_0 = getView().findViewById(R.id.audio_1);
        audio_1 = getView().findViewById(R.id.audio_2);
        audio_2 = getView().findViewById(R.id.audio_3);
        audio_3 = getView().findViewById(R.id.audio_4);
        button_next = getView().findViewById(R.id.button_next);

        renewlist();

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorTheRightOne(0);
            }
        });
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorTheRightOne(1);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorTheRightOne(2);
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorTheRightOne(3);
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renewlist();
            }
        });
    }

    private void colorTheRightOne(int clickedButton){
        if(button_next.getVisibility() == View.VISIBLE ){
            return;
        }

        int rightButton = 0;
        for(int i = 0; i<list.size();i++){
            if(list.get(i).isSame(topEsma)){
                rightButton = i;
            }
        }

        boolean wasRight = rightButton==clickedButton;

        switch (rightButton){
            case 0:
                button_0.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                button_1.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                button_2.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                button_3.setBackgroundColor(Color.GREEN);
                break;
        }

        if (clickedButton != rightButton){
            switch (clickedButton){
                case 0:
                    button_0.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    button_1.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    button_2.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    button_3.setBackgroundColor(Color.RED);
                    break;
            }
        }

        if(wasRight){
            MainActivity.esmaUlHusna.increaseRightOfNr(topEsma.getNumber());
        }else{
            MainActivity.esmaUlHusna.increaseWrongOfNr(topEsma.getNumber());
            MainActivity.esmaUlHusna.increaseWrongOfNr(list.get(clickedButton).getNumber());
        }


        button_next.setVisibility(View.VISIBLE);
    }

    private void renewlist(){
        list = MainActivity.esmaUlHusna.getRandomListFromRange();
        topEsma = list.get(new Random().nextInt(3));

        Type top_type;

        while(true) {
            int i = new Random().nextInt(8);

            switch (i){
                case 0:
                    top_type = Type.ARABIC;
                    break;
                case 1:
                    top_type = Type.TRANSKRIPTIONEN;
                    break;
                case 2:
                    top_type = Type.TRANSKRIPTIONTR;
                    break;
                case 3:
                    top_type = Type.MEANINGEN;
                    break;
                case 4:
                    top_type = Type.MEANINGTR;
                    break;
                case 5:
                    top_type = Type.DETAILSTR;
                    break;
                case 6:
                    top_type = Type.NUMBER;
                    break;
                default:
                    top_type = Type.AUDIO;
            }

            if(Esma.isTypeChecked(top_type)){break;}
        }

        System.out.println(topEsma.getTranscriptionEN());
        System.out.println(list.get(0).getTranscriptionEN());
        System.out.println(list.get(1).getTranscriptionEN());
        System.out.println(list.get(2).getTranscriptionEN());
        System.out.println(list.get(3).getTranscriptionEN());

        if(top_type == Type.AUDIO){
            audio_top.setVisibility(View.VISIBLE);

            audio_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(getContext(), topEsma.getOgg());
                    mp.start();
                }
            });

            audio_top.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                        audio_top.setScaleX(0.90f);
                        audio_top.setScaleY(0.90f);
                        audio_top.setAlpha(0.7f);
                    } else {
                        audio_top.setScaleX(1f);
                        audio_top.setScaleY(1f);
                        audio_top.setAlpha(1f);
                    }
                    return false;
                }
            });
        }else{
            audio_top.setVisibility(View.GONE);
        }

        topText.setText(topEsma.getTextFromType(top_type));
        button_0.setText(list.get(0).getTextExceptType(top_type));
        button_1.setText(list.get(1).getTextExceptType(top_type));
        button_2.setText(list.get(2).getTextExceptType(top_type));
        button_3.setText(list.get(3).getTextExceptType(top_type));

        button_0.setBackgroundColor(getResources().getColor(R.color.button_green));
        button_1.setBackgroundColor(getResources().getColor(R.color.button_green));
        button_2.setBackgroundColor(getResources().getColor(R.color.button_green));
        button_3.setBackgroundColor(getResources().getColor(R.color.button_green));

        setAudioButtons();


        button_next.setVisibility(View.GONE);


    }

    private void setAudioButtons(){
        if(button_0.getText().toString().equals("AUDIO")){
            audio_0.setVisibility(View.VISIBLE);

            audio_0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(getContext(), list.get(0).getOgg());
                    mp.start();
                }
            });

            audio_0.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                        audio_0.setScaleX(0.90f);
                        audio_0.setScaleY(0.90f);
                        audio_0.setAlpha(0.7f);
                    } else {
                        audio_0.setScaleX(1f);
                        audio_0.setScaleY(1f);
                        audio_0.setAlpha(1f);
                    }
                    return false;
                }
            });

        }else{
            audio_0.setVisibility(View.GONE);
        }


        if(button_1.getText().toString().equals("AUDIO")){
            audio_1.setVisibility(View.VISIBLE);

            audio_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(getContext(), list.get(1).getOgg());
                    mp.start();
                }
            });

            audio_1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                        audio_1.setScaleX(0.90f);
                        audio_1.setScaleY(0.90f);
                        audio_1.setAlpha(0.7f);
                    } else {
                        audio_1.setScaleX(1f);
                        audio_1.setScaleY(1f);
                        audio_1.setAlpha(1f);
                    }
                    return false;
                }
            });
        }else{
            audio_1.setVisibility(View.GONE);
        }


        if(button_2.getText().toString().equals("AUDIO")){
            audio_2.setVisibility(View.VISIBLE);

            audio_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(getContext(), list.get(2).getOgg());
                    mp.start();
                }
            });

            audio_2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                        audio_2.setScaleX(0.90f);
                        audio_2.setScaleY(0.90f);
                        audio_2.setAlpha(0.7f);
                    } else {
                        audio_2.setScaleX(1f);
                        audio_2.setScaleY(1f);
                        audio_2.setAlpha(1f);
                    }
                    return false;
                }
            });

        }else{
            audio_2.setVisibility(View.GONE);
        }


        if(button_3.getText().toString().equals("AUDIO")){
            audio_3.setVisibility(View.VISIBLE);

            audio_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(getContext(), list.get(3).getOgg());
                    mp.start();
                }
            });

            audio_3.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                        audio_3.setScaleX(0.90f);
                        audio_3.setScaleY(0.90f);
                        audio_3.setAlpha(0.7f);
                    } else {
                        audio_3.setScaleX(1f);
                        audio_3.setScaleY(1f);
                        audio_3.setAlpha(1f);
                    }
                    return false;
                }
            });

        }else{
            audio_3.setVisibility(View.GONE);
        }
    }


}