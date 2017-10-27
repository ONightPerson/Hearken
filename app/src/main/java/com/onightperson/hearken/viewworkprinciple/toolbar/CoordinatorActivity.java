package com.onightperson.hearken.viewworkprinciple.toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/3/10.
 */

public class CoordinatorActivity extends Activity implements View.OnClickListener {
    private Button mDoAnythingBtn;
    private Button mShowSnackBarBtn;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_appbar_layout);

//        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
//        mShowSnackBarBtn = (Button) findViewById(R.id.show_snackbar);
//        mShowSnackBarBtn.setOnClickListener(this);
//        mDoAnythingBtn = (Button) findViewById(R.id.do_anything);
//        mDoAnythingBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mShowSnackBarBtn) {
            Snackbar.make(mCoordinatorLayout, "This is a simple Snacker", Snackbar.LENGTH_LONG)
                    .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);
                            Toast.makeText(CoordinatorActivity.this, "Snackbar消失了", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //nothing
                        }
                    }).show();
        } else if (v == mDoAnythingBtn) {
            Toast.makeText(this, "do nothing", Toast.LENGTH_SHORT).show();
        }
    }
}
