package com.greenfox.paranoidandroids.fidgetcubeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.DrawingFragment;
import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.LightsOnFragment;
import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.SpinnerFragment;
import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.CatFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

  private static final String TAG = "MainActivity";
  private DrawerLayout mDrawerLayout;
  private ActionBarDrawerToggle mToggle;
  private Fragment fragment;
  private Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    displaySelectedScreen(R.id.nav_fidget1);

    mToolbar = (Toolbar) findViewById(R.id.nav_action);
    setSupportActionBar(mToolbar);

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

    mDrawerLayout.addDrawerListener(mToggle);
    mToggle.syncState();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(this);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (!Settings.System.canWrite(getBaseContext())) {
        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (mToggle.onOptionsItemSelected(item)) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void displaySelectedScreen(int id) {

    switch (id) {
      case R.id.nav_fidget1:
        fragment = new SpinnerFragment();
        Log.d(TAG, "displaySelectedScreen: SpinnerFragment()");
        break;
      case R.id.nav_fidget2:
        fragment = new CatFragment();
        Log.d(TAG, "displaySelectedScreen: CatFragment()");
        break;
      case R.id.nav_fidget3:
        fragment = new DrawingFragment();
        Log.d(TAG, "displaySelectedScreen: DrawingFragment()");
        break;
      case R.id.nav_fidget4:
        fragment = new LightsOnFragment();
        Log.d(TAG, "displaySelectedScreen: LightsOnFragment()");
        break;
    }
    if (fragment != null) {
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.fragment_container, fragment);
      transaction.commit();
    }
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    mDrawerLayout.closeDrawer(GravityCompat.START);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();
    displaySelectedScreen(id);
    return true;
  }
}
