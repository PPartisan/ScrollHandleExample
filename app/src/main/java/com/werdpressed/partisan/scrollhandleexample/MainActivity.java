package com.werdpressed.partisan.scrollhandleexample;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AppBarManager{

    private Toolbar mToolbar;
    private RecyclerFragment mRecyclerFragment;
    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        if (getSupportFragmentManager().findFragmentById(R.id.rv_container) == null) {
            mRecyclerFragment = RecyclerFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.rv_container, mRecyclerFragment, RecyclerFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        mRecyclerFragment.getLayoutManager().setAppBarManager(this);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mRecyclerFragment == null) {
            mRecyclerFragment =
                    (RecyclerFragment) getSupportFragmentManager()
                    .findFragmentByTag(RecyclerFragment.TAG);
        }

        int id = item.getItemId();
        switch (id) {
            case R.id.action_lock:
                makeToast(getString(R.string.action_lock));
                mRecyclerFragment.getConfigurableRecyclerView().setScrollingActive(true);
                break;
            case R.id.action_unlock:
                makeToast(getString(R.string.action_unlock));
                mRecyclerFragment.getConfigurableRecyclerView().setScrollingActive(false);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collapseAppBar() {
        mAppBarLayout.setExpanded(false, true);
    }

    @Override
    public void expandAppBar() {
        mAppBarLayout.setExpanded(true, true);
    }
}
