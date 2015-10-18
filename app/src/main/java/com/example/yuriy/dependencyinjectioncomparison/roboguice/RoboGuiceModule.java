package com.example.yuriy.dependencyinjectioncomparison.roboguice;

import com.example.yuriy.dependencyinjectioncomparison.CheeseCakeFactory;
import com.example.yuriy.dependencyinjectioncomparison.ILogger;
import com.example.yuriy.dependencyinjectioncomparison.IPieFactory;
import com.example.yuriy.dependencyinjectioncomparison.LogcatLogger;
import com.example.yuriy.dependencyinjectioncomparison.PieRegisty;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

/**
 * Created by Yuriy on 18.10.2015.
 */
public class RoboGuiceModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(ILogger.class).to(LogcatLogger.class).in(Scopes.SINGLETON);
        binder.bind(PieRegisty.class).in(Scopes.SINGLETON);
        binder.bind(IPieFactory.class).to(CheeseCakeFactory.class).in(Scopes.SINGLETON);
    }
}
