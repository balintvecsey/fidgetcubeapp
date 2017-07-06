package com.greenfox.paranoidandroids.fidgetcubeapp;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.Fidget1;
import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.Fidget2;
import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.Fidget3;
import com.greenfox.paranoidandroids.fidgetcubeapp.fragments.SpinnerFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

  private DrawerLayout mDrawerLayout;
  private ActionBarDrawerToggle mToggle;
  Fragment fragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

    mDrawerLayout.addDrawerListener(mToggle);
    mToggle.syncState();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(this);
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
        break;
      case R.id.nav_fidget2:
        fragment = new Fidget2();
        break;
      case R.id.nav_fidget3:
        fragment = new Fidget3();
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
