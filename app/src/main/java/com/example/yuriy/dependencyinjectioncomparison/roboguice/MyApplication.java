package com.example.yuriy.dependencyinjectioncomparison.roboguice;

import android.app.Application;
import android.util.Log;

import com.example.yuriy.dependencyinjectioncomparison.dagger2.Dagger2Component;
import com.example.yuriy.dependencyinjectioncomparison.dagger2.DaggerDagger2Component;
import com.google.inject.util.Modules;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * Created by Yuriy on 16.10.2015.
 */
public class MyApplication extends Application {
    private RoboInjector injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "onCreate");

        //RoboGuice start

        RoboGuice.setUseAnnotationDatabases(false);
        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(this)).with(new RoboGuiceModule()));
        injector = RoboGuice.getInjector(this);

        //RoboGuice end

        //Dagger2 start
        Dagger2Component.Singleton.initialze(DaggerDagger2Component.create());
        //Dagger2 end
    }
}
