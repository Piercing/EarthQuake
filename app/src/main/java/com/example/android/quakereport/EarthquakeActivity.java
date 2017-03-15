/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.EarthQuakAdapter;
import Helpers.QueryUtils;
import Models.Earthquake;

public class EarthquakeActivity extends AppCompatActivity {

    /**
     * URL for earthquake data from the USGS dataset
     */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
           // "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.earthquake_activity );

        // Kick off an {@link AsyncTask} to perform the network request.
        // Create an {@link AsyncTask} to perform the HTTP request to the given URL
        // on a background thread. When the result is received on the main UI thread,
        // then update the UI.
        EarthquakeAsyncTask task = new EarthquakeAsyncTask( );
        task.execute( USGS_REQUEST_URL );
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground( String... urls ) {

            // Don't perform the request if there are no URLs, or the first URL is nul.
            if ( urls.length < 1 || urls[ 0 ] == null ) {
                return null;
            }

            // Perform the HTTP request for earthquake data and process the response.
            // En vez de pasar la constante USGS_REQUEST_URL, paso [0] para que pueda aceptar cualquier url.
            ArrayList<Earthquake> result = QueryUtils.fetchEarthquakeData( USGS_REQUEST_URL );

            // Return the {@link Event} object as the result for the {@link EarthquakeAsyncTask}
            // Este resultado que devuelve, se pasa como parámetro al método 'onPostExecute'.
            return result;
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link EarthquakeAsyncTask}).
         */
        @Override
        protected void onPostExecute( ArrayList<Earthquake> result ) {

            if ( result == null ) {
                Toast.makeText( EarthquakeActivity.this, "Not result found for this end point", Toast.LENGTH_SHORT ).show( );
                return;
            }

            // Create an {@link EarthQuakAdapter}, whose data source is a list of {@link EarthQuakAdapter}s.
            // The adapter knows how to create list item views for each item in the list.
            EarthQuakAdapter earthQuakAdapter = new EarthQuakAdapter( getApplicationContext( ), result );// Aquí tengo que pasarle el arrayList con Earthquake.

            // Find a reference to the {@link ListView} in the layout.
            ListView earthquakeListView = ( ListView ) findViewById( R.id.list );

            // Attach the adapter to the listView.
            assert earthquakeListView != null;
            earthquakeListView.setAdapter( earthQuakAdapter );

        }
    }
}
