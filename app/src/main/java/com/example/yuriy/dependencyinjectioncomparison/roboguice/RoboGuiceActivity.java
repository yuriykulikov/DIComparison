package com.example.yuriy.dependencyinjectioncomparison.roboguice;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yuriy.dependencyinjectioncomparison.ILogger;
import com.example.yuriy.dependencyinjectioncomparison.IPieFactory;
import com.example.yuriy.dependencyinjectioncomparison.R;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;

public class RoboGuiceActivity extends RoboActionBarActivity {
    /** public for dagger even though it does not inject this actvity */
    @Inject
    public ILogger logger;
    /** public for dagger even though it does not inject this actvity */
    @Inject
    public IPieFactory pieFactory;
    @InjectView(R.id.throwPie)
    private Button button;
    @InjectView(R.id.textView)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger.d("Injected everything with RoboGuice, very happy!");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(pieFactory.createPie().getName());
            }
        });
    }
}
