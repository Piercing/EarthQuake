package Adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Models.Earthquake;

public class EarthQuakAdapter extends ArrayAdapter<Earthquake> {

    //private static Pattern twopart = Pattern.compile("(\\d+),(\\d+)");
    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR;

    static {
        switch ( LOCATION_SEPARATOR = "(?<=of )" ) {
        }
    }

    public EarthQuakAdapter( Context context, ArrayList<Earthquake> earthQuaks ) {
        super( context, 0, earthQuaks );
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent ) {

        // Check if the existing view is being reused, otherwise inflate the view.
        View listItemView = convertView;
        if ( listItemView == null ) {
            listItemView = LayoutInflater.from( getContext( ) ).inflate( R.layout.list_earthquak, parent, false );
        }

        // Get the Earthquake object located at this position in the list.
        Earthquake currentEarthquake = getItem( position );

        // Find the TextView in the list_earthquake layout with the ID magnitude.
        TextView textViewMagnitude = ( TextView ) listItemView.findViewById( R.id.magnitude );

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = ( GradientDrawable ) textViewMagnitude.getBackground( );

        // Get the appropriate background color based on the current earthquake magnitude
        assert currentEarthquake != null;
        int magnitudeColor = getMagnitudeColor( currentEarthquake.getmMagnitude( ) );

        // Set the color on the magnitude circle
        magnitudeCircle.setColor( magnitudeColor );

        // Format the magnitude to show 1 decimal place
        String formattedMaginitude = formatMagnitude( currentEarthquake.getmMagnitude( ) );

        // Get the magnitude from the current Earthquake object and set this text on the name TextView.
        textViewMagnitude.setText( formattedMaginitude );

        // Find the TextView in the list_earthquake layout with the ID location_offset & ID location_primary.
        TextView textViewLocationOffSet = ( TextView ) listItemView.findViewById( R.id.location_offset );
        TextView textViewLocationPrimary = ( TextView ) listItemView.findViewById( R.id.primary_location );

        // Split location & show two parts into views corresponding.
        String getLocation = currentEarthquake.getLocation( );
        splitLocation( getLocation, textViewLocationOffSet, textViewLocationPrimary );

        //Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date( currentEarthquake.getTimeInMilliseconds( ) );

        // Find the TextView with view ID date
        TextView dateView = ( TextView ) listItemView.findViewById( R.id.date );

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate( dateObject );

        // Display the date of the current earthquake in that TextView
        dateView.setText( formattedDate );

        // Find the TextView with view ID time
        TextView timeView = ( TextView ) listItemView.findViewById( R.id.time );

        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime( dateObject );

        // Display the time of the current earthquake in that TextView
        timeView.setText( formattedTime );

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate( Date dateObject ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd LLL, yyyy" );
        return dateFormat.format( dateObject );
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime( Date dateObject ) {
        SimpleDateFormat timeFormat = ( new SimpleDateFormat( "HH:mm a" ) );
        return timeFormat.format( dateObject );
    }

    /**
     * Split the location into two parts from which it finds the word 'of' and assigns it to its views.
     *
     * @param location
     * @param locationOffset
     * @param locationPrimary
     */
    private void splitLocation( String location, TextView locationOffset, TextView locationPrimary ) {

        String[] parts = location.split( LOCATION_SEPARATOR, 2 );
        String part1;
        String part2 = "";

        if ( parts.length > 1 ) { // Avoids the indexOfBounds.
            part1 = parts[ 0 ];
            part2 = parts[ 1 ];
        } else {
            part1 = parts[ 0 ];
        }

        try {
            locationOffset.setText( part1 );
            locationPrimary.setText( part2 );
        } catch ( StringIndexOutOfBoundsException ex ) {
            ex.getStackTrace( );
        }
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude( double magnitude ) {
        DecimalFormat magnitudeFormat = new DecimalFormat( "0.0" );
        return magnitudeFormat.format( magnitude );
    }

    /**
     * call ContextCompat getColor() to convert the color resource ID into an actual integer color value,
     * and return the result as the return value of the getMagnitudeColor() helper method.
     *
     * @param magnitude
     * @return
     */
    private int getMagnitudeColor( double magnitude ) {
        int magnitudeColorResourceId;
        int magnitudeFloor = ( int ) Math.floor( magnitude );
        switch ( magnitudeFloor ) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor( getContext( ), magnitudeColorResourceId );
    }
}
