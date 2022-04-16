package de.aytekin.asmaulhusna;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import de.aytekin.asmaulhusna.Esma;
import de.aytekin.asmaulhusna.EsmaComparatorNumber;
import de.aytekin.asmaulhusna.MainActivity;
import de.aytekin.asmaulhusna.R;
import de.aytekin.asmaulhusna.databinding.FragmentSlideshowBinding;

public class StatsFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    private ListView listView;
    private ArrayAdapter<Esma> listAdapter;
    private ArrayList<Esma> rangedList;
    private Button reset_Button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
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
        reset_Button = getView().findViewById(R.id.button_reset);
        updateListViewSimple();

        reset_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("EsmaUlHusna: Reset");
                builder.setMessage("Are you sure you want to reset all stats?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.esmaUlHusna.resetStats();
                        updateListViewSimple();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void updateListViewSimple() {
        rangedList = MainActivity.esmaUlHusna.getRangedList();
        rangedList.sort(new EsmaComparatorStat());

        try {
            listAdapter = new ArrayAdapter<Esma>(getContext(), R.layout.statlist_card, rangedList) {

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    ViewHolderItem viewHolder;

                    if (convertView == null) {

                        convertView = getLayoutInflater().inflate(
                                R.layout.statlist_card, parent, false
                        );

                        viewHolder = new ViewHolderItem();
                        viewHolder.right = convertView.findViewById(R.id.rightcounter_text);
                        viewHolder.wrong = convertView.findViewById(R.id.wrongcounter_text);
                        viewHolder.name = convertView.findViewById(R.id.name_text);
                        viewHolder.rating = convertView.findViewById(R.id.rating_text);

                        convertView.setTag(viewHolder);

                    } else {
                        viewHolder = (ViewHolderItem) convertView.getTag();
                    }

                    viewHolder.right.setText(String.valueOf(rangedList.get(position).getRight()));
                    viewHolder.wrong.setText(String.valueOf(rangedList.get(position).getWrong()));
                    viewHolder.name.setText(rangedList.get(position).getTranscriptionEN());
                    if(rangedList.get(position).hasStats()){
                        viewHolder.rating.setText(String.valueOf(rangedList.get(position).getRating()));
                    }else{
                        viewHolder.rating.setText("---");
                    }

                    return convertView;

                }

                final class ViewHolderItem {
                    TextView name;
                    TextView right;
                    TextView wrong;
                    TextView rating;
                }
            };

            listView.setAdapter(listAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}