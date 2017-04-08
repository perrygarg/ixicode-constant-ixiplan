package com.ixicode.constant.ixiplan.common.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.util.AppUtil;


/**
 */
public class BaseActivity extends AppCompatActivity
{
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected Toolbar setupToolbar(String title)
    {
        return setupToolbar(title, true, 0);
    }

    protected Toolbar setupToolbar(String title, boolean setDisplayHomeAsUpEnabled)
    {
        return setupToolbar(title, setDisplayHomeAsUpEnabled, 0);
    }

    protected Toolbar setupToolbar(String title, boolean setDisplayHomeAsUpEnabled, int drawableId)
    {
        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled);

        if(AppUtil.isStringEmpty(title))
            supportActionBar.setDisplayShowTitleEnabled(false);
        else
            supportActionBar.setTitle(title);

        if(drawableId != 0)
        {
            supportActionBar.setHomeAsUpIndicator(drawableId); //Used to Change Hamberger/Up Icon
        }

        return topToolBar;
    }
}
