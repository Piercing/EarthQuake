package Models;

/**
 * Created by Piercing on 12/3/17.
 */


public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude          is the magnitude (size) of the earthquake
     * @param location           is the city location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *                           earthquake happened
     */

    public Earthquake( double magnitude, String location, long timeInMilliseconds, String url ) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public double getmMagnitude( ) {
        return mMagnitude;
    }

    public String getLocation( ) {
        return mLocation;
    }

    public long getTimeInMilliseconds( ) {
        return mTimeInMilliseconds;
    }

    public String getUrl( ) { return mUrl; }


    @Override
    public String toString( ) {
        return "EarthQuakAdapter{" +
                "mMagnitude=" + mMagnitude +
                ", mLocation='" + mLocation + '\'' +
                ", mTimeInMilliseconds=" + mTimeInMilliseconds +
                '}';
    }
}
