package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;

import java.util.ArrayList;

import Models.EarthQuak;

public class EarthQuakAdapter extends ArrayAdapter<Models.EarthQuak> {

    public EarthQuakAdapter(Context context, ArrayList<Models.EarthQuak> earthQuaks) {
        super(context, 0, earthQuaks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_earthquak, parent, false);
        }

        // Get the EarthQuake object located at this position in the list.
        EarthQuak currentEartQuake = getItem(position);

        // Find the TextView in the list_earthquake layout with the ID magnitude.
        TextView textViewMagnitude = (TextView) listItemView.findViewById(R.id.magnitude);

        // Get the magnitude from the current EarthQuake object and set this text on the name TextView.
        assert currentEartQuake != null;
        textViewMagnitude.setText(currentEartQuake.getmMagnitude());

        // Find the TextView in the list_earthquake layout with the ID magnitude.
        TextView textViewCountry = (TextView) listItemView.findViewById(R.id.country);

        // Get the magnitude from the current EarthQuake object and set this text on the name TextView.
        textViewCountry.setText(currentEartQuake.getmCountry());

        // Find the TextView in the list_earthquake layout with the ID magnitude.
        TextView textViewDate = (TextView) listItemView.findViewById(R.id.date);

        // Get the magnitude from the current EarthQuake object and set this text on the name TextView.
        textViewDate.setText(currentEartQuake.getmDate());

        return listItemView;
    }
}
