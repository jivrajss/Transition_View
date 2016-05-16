package module.custom.jugnig.sampletransitionview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private static  float MIN_DISTANCE = 500f;
    ImageView imageView;
    private float downY;
    private float deltaY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.imageview);
        imageView.setOnTouchListener(this);
        MIN_DISTANCE= (float) (0.5*imageView.getHeight());
        originalX = imageView.getX();
        originalY = imageView.getY();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    float dX, dY, originalX, originalY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");
                dX = imageView.getX() - event.getRawX();
                dY = imageView.getY() - event.getRawY();
                downY = event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                imageView.animate()
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP---" + event.getRawY() + "---Original---" + imageView.getHeight() + "---difference---" + (imageView.getHeight() - event.getRawY()));
                float upY = event.getRawY();

                deltaY = downY - upY;

                if (Math.abs(deltaY) > MIN_DISTANCE) { // vertical swipe
                    if (deltaY < 0) {
                        Log.i(TAG, "Swipe Top to Bottom:::" + deltaY + "---Min_distance---" + MIN_DISTANCE);
                        //mSwipeDetected = Action.TB;
                        Snackbar.make(imageView, "Removed from Preferences", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                    if (deltaY > 0) {
                        Log.i(TAG, "Swipe Bottom to Top:::" + deltaY + "---Min_distance---" + MIN_DISTANCE);
                        //mSwipeDetected = Action.BT;
                        Snackbar.make(imageView, "Added to Watch later..", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                } else {
                    Log.e(TAG, "into ELse---" + Math.abs(deltaY));
                    Snackbar.make(imageView, "Card Retained....no Action Yet!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }


                imageView.animate()
                        .x(originalX)
                        .y(originalY)
                        .setDuration(0)
                        .start();
                break;

            default:
                return false;
        }
        return true;
    }
}
