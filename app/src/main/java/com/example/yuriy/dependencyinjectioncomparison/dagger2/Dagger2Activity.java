package com.example.yuriy.dependencyinjectioncomparison.dagger2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.yuriy.dependencyinjectioncomparison.ILogger;
import com.example.yuriy.dependencyinjectioncomparison.IPieFactory;
import com.example.yuriy.dependencyinjectioncomparison.R;
import com.example.yuriy.dependencyinjectioncomparison.roboguice.MyApplication;

import javax.inject.Inject;

/**
 * Created by Yuriy on 18.10.2015.
 */
public class Dagger2Activity extends Activity {
    /**
     * public for dagger2
     */
    @Inject
    public ILogger logger;
    @Inject
    /** public for dagger2 */
    public IPieFactory pieFactory;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dagger2Component.Singleton.getsInstance().inject(this);

        logger.d("Injected everything with Dagger2, very happy!");

        button = (Button) findViewById(R.id.throwPie);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(pieFactory.createPie().getName());
            }
        });
    }
}
