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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.EarthQuakAdapter;
import Helpers.QueryUtils;
import Models.Earthquake;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


        // Create an {@link EarthQuakAdapter}, whose data source is a list of {@link EarthQuakAdapter}s.
        // The adapter knows how to create list item views for each item in the list.
        EarthQuakAdapter earthQuakAdapter = new EarthQuakAdapter(this, earthquakes);

        // Find a reference to the {@link ListView} in the layout.
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Attach the adapter to the listView.
        assert earthquakeListView != null;
        earthquakeListView.setAdapter(earthQuakAdapter);
    }
}
