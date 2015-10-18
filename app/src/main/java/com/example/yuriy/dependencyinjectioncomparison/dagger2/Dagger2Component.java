package com.example.yuriy.dependencyinjectioncomparison.dagger2;

import org.roboguice.shaded.goole.common.base.Preconditions;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yuriy on 17.10.2015.
 */
@Singleton
@Component(modules = Dagger2Module.class)
public interface Dagger2Component {
    class Singleton {
        private static Dagger2Component sInstance;

        public static void initialze(Dagger2Component component) {
            Singleton.sInstance = component;
        }

        public static Dagger2Component getsInstance() {
            return Preconditions.checkNotNull(sInstance);
        }
    }

    void inject(Dagger2Activity mainActivity);
    //add here whatever else you want to inject
}
