package com.example.activeandroid.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.activeandroid.R;
import com.example.activeandroid.model.Apartment;
import com.example.activeandroid.repository.Repository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = MainActivity.class.getName();
  private LinearLayout rootLayout = null;

  private Repository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_active_android_basic_example);
    rootLayout = ((LinearLayout) findViewById(R.id.container));
    rootLayout.removeAllViews();

    repository = new Repository();

    basicCRUD();
    basicQuery();
    basicLinkQuery();
    complexReadWrite();
    complexQuery();
  }

  private void showStatus(String txt) {
    Log.i(TAG, txt);
    TextView tv = new TextView(this);
    tv.setText(txt);
    rootLayout.addView(tv);
  }

  private void basicCRUD() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("Perform basic create operations...");
    repository.addApartmentWithRent(7500);
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;
    showStatus("Basic create operations :- " + diff + " milliseconds");

    long startTimeForUpdate = SystemClock.currentThreadTimeMillis();
    showStatus("Perform update...");
    repository.updateFirstApartmentInTheList();
    long endTimeForUpdate = SystemClock.currentThreadTimeMillis();
    long diffForUpdate = endTimeForUpdate - startTimeForUpdate;
    showStatus("Basic Update operations :- " + diffForUpdate + " milliseconds");
  }

  private void basicQuery() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\nPerforming basic Query operation...");
    List<Apartment> results = repository.fetchApartmentsBasedOnRentValue(8000);

    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;

    showStatus("Size of result set: " + results.size());
    showStatus("Basic query :- " + diff + " milliseconds");
  }

  private void basicLinkQuery() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\nPerforming basic Link Query operation...");

    List<Apartment> results = repository.fetchApartmentsBasedOnName("Hermes Heritage");
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;

    showStatus("Size of result set: " + results.size());
    showStatus("Basic link query :- " + diff + " milliseconds");
  }

  private void complexReadWrite() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\nPerforming complex Read/Write operation...");

    for (int i = 7500; i < 10000; i++) {
      repository.addApartmentWithRent(i);
    }
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;
    showStatus("Complex read and write :- " + diff + " milliseconds");
  }

  private void complexQuery() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\n\nPerforming complex Query operation...");

    List<Apartment> results = repository.executeComplexQuery();
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;
    showStatus("\nSize of result set: " + results.size());
    showStatus("Complex link :- " + diff + " milliseconds");
  }
}
