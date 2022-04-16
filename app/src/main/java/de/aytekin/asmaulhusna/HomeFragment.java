package de.aytekin.asmaulhusna;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import de.aytekin.asmaulhusna.Esma;
import de.aytekin.asmaulhusna.MainActivity;
import de.aytekin.asmaulhusna.R;
import de.aytekin.asmaulhusna.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView listView;
    private ArrayAdapter<Esma> listAdapter;
    private ArrayList<Esma> rangedList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
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

        listView = getView().findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.Style_Dialog_Rounded_Corner);
                builder.setTitle(rangedList.get(position).getTranscriptionEN());
                builder.setMessage(rangedList.get(position).getDetailsTR());

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        updateListViewSimple();
    }

    private void updateListViewSimple() {
        rangedList = MainActivity.esmaUlHusna.getRangedList();

        try {
            listAdapter = new ArrayAdapter<Esma>(getContext(), R.layout.esma_card, rangedList) {

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    ViewHolderItem viewHolder;

                    if (convertView == null) {

                        convertView = getLayoutInflater().inflate(
                                R.layout.esma_card, parent, false
                        );

                        viewHolder = new ViewHolderItem();
                        viewHolder.number = convertView.findViewById(R.id.number);
                        viewHolder.arabic = convertView.findViewById(R.id.arabic);
                        viewHolder.english_transcription = convertView.findViewById(R.id.english_transcription);
                        viewHolder.turkish_transcription = convertView.findViewById(R.id.turkish_transcription);
                        viewHolder.english_meaning = convertView.findViewById(R.id.english_meaning);
                        viewHolder.turkish_meaning = convertView.findViewById(R.id.turkish_meaning);
                        viewHolder.play_sound = convertView.findViewById(R.id.play_sound);
                        convertView.setTag(viewHolder);

                    } else {
                        viewHolder = (ViewHolderItem) convertView.getTag();
                    }

                    viewHolder.number.setText(String.valueOf(rangedList.get(position).getNumber()));
                    viewHolder.arabic.setText(rangedList.get(position).getArabic());
                    viewHolder.english_transcription.setText(rangedList.get(position).getTranscriptionEN());
                    viewHolder.turkish_transcription.setText(rangedList.get(position).getTranscriptionTR());
                    viewHolder.english_meaning.setText(rangedList.get(position).getMeaningEN());
                    viewHolder.turkish_meaning.setText(rangedList.get(position).getMeaningTR());

                    if(rangedList.get(position).getNumber() == 0) {
                        viewHolder.play_sound.setVisibility(View.GONE);
                    }else{
                        viewHolder.play_sound.setVisibility(View.VISIBLE);
                        viewHolder.play_sound.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final MediaPlayer mp = MediaPlayer.create(getContext(), rangedList.get(position).getOgg());
                                mp.start();

                            }
                        });
                        viewHolder.play_sound.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                                    viewHolder.play_sound.setScaleX(0.90f);
                                    viewHolder.play_sound.setScaleY(0.90f);
                                    viewHolder.play_sound.setAlpha(0.7f);
                                } else {
                                    viewHolder.play_sound.setScaleX(1f);
                                    viewHolder.play_sound.setScaleY(1f);
                                    viewHolder.play_sound.setAlpha(1f);
                                }
                                return false;
                            }
                        });
                    }

                    return convertView;

                }

                final class ViewHolderItem {
                    TextView number;
                    TextView arabic;
                    TextView english_transcription;
                    TextView turkish_transcription;
                    TextView english_meaning;
                    TextView turkish_meaning;
                    ImageView play_sound;
                }
            };

            listView.setAdapter(listAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}