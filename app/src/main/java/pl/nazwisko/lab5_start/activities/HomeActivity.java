package pl.nazwisko.lab5_start.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pl.nazwisko.lab5_start.R;
import pl.nazwisko.lab5_start.activities.add.AddCommentActivity;
import pl.nazwisko.lab5_start.activities.add.AddReviewActivity;
import pl.nazwisko.lab5_start.activities.list.BooksListActivity;
import pl.nazwisko.lab5_start.activities.list.CommentsListActivity;
import pl.nazwisko.lab5_start.activities.list.ReviewsListActivity;

/**
 * Aktywność z menu.
 *
 * @author Michał Ciołek
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //wyświetlenie imienia "zalogowanego" użytkownika
        TextView welcomeTextView = findViewById(R.id.welcome_text);
        SharedPreferences sp = getSharedPreferences(MainActivity.SETTINGS_PREFS, Context.MODE_PRIVATE);
        welcomeTextView.setText(String.format("Witaj %s", sp.getString(MainActivity.NAME_PREFS, "Nieznany")));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Tworzenie pozycji w menu aktywności
     * @param menu aktywności
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /**
     * Obsługa naciśnięć w menu aktywności.
     *
     * @param item kliknięty element
     * @return true jeśli obsłużone, false pozostałe przypadki
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_program:
                Intent intent = new Intent(this, AboutProgramActivity.class);
                startActivity(intent);

                return true;

            case R.id.share:
                String shareBody = getString(R.string.share_app_body);
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_app_title));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_app_info)));

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Obsługa naciśnięć w menu nawigacji.
     *
     * @param item kliknięty element
     * @return true jeśli obsłużone, false pozostałe przypadki
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.book_web:
            case R.id.mathematic:
            case R.id.informatic:
            case R.id.electronic:
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.books_list:
                intent = new Intent(this, BooksListActivity.class);
                startActivity(intent);
                break;

            case R.id.add_comment:
                intent = new Intent(this, AddCommentActivity.class);
                startActivity(intent);
                break;

            case R.id.list_comment:
                intent = new Intent(this, CommentsListActivity.class);
                startActivity(intent);
                break;

            case R.id.add_review:
                intent = new Intent(this, AddReviewActivity.class);
                startActivity(intent);
                break;

            case R.id.list_reviews:
                intent = new Intent(this, ReviewsListActivity.class);
                startActivity(intent);
                break;

                // TODO odtwarzacz
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
