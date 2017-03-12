package Models;

/**
 * Created by Piercing on 12/3/17.
 */


public class EarthQuak {

    private String mMagnitude;
    private String mCountry;
    private String mDate;

    public EarthQuak(String mMagnitude, String mCountry, String date) {
        this.mMagnitude = mMagnitude;
        this.mCountry = mCountry;
        this.mDate = date;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmCountry() {
        return mCountry;
    }

    public String getmDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return "EarthQuakAdapter{" +
                "mMagnitude=" + mMagnitude +
                ", mCountry='" + mCountry + '\'' +
                ", mDate=" + mDate +
                '}';
    }
}
