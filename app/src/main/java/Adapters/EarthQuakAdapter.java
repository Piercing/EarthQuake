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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import Models.Earthquake;

public class EarthQuakAdapter extends ArrayAdapter<Earthquake> {

    private static Pattern twopart = Pattern.compile("(\\d+),(\\d+)");

    public EarthQuakAdapter(Context context, ArrayList<Earthquake> earthQuaks) {
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

        // Get the Earthquake object located at this position in the list.
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_earthquake layout with the ID magnitude.
        TextView textViewMagnitude = (TextView) listItemView.findViewById(R.id.magnitude);

        // Get the magnitude from the current Earthquake object and set this text on the name TextView.
        assert currentEarthquake != null;
        textViewMagnitude.setText(currentEarthquake.getmMagnitude());

        // Find the TextView in the list_earthquake layout with the ID location_offset.
        TextView textViewLocationOffSet = (TextView) listItemView.findViewById(R.id.location_offset);
        // Find the TextView in the list_earthquake layout with the ID location_primary.
        TextView textViewLocationPrimary = (TextView) listItemView.findViewById(R.id.location_primary);

        // Split location & show two parts into views corresponding.
        String getLocation = currentEarthquake.getLocation();
        splitLocation(getLocation, textViewLocationOffSet, textViewLocationPrimary);

        //Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);

        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);

        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = (new SimpleDateFormat("HH:mm"));
        return timeFormat.format(dateObject);
    }

    private static void splitLocation(String location, TextView locationOffset, TextView locationPrimary) {

        String[] parts = location.split("(?<=of)", 2);
        String part1;
        String part2 = "";

        if (parts.length > 1) { // Avoids the indexOfBounds.
            part1 = parts[0];
            part2 = parts[1];
        } else {
            part1 = parts[0];
        }

        try {
            locationOffset.setText(part1);
            locationPrimary.setText(part2);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}
