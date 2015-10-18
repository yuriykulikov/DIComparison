package com.example.yuriy.dependencyinjectioncomparison.dagger2;

import com.example.yuriy.dependencyinjectioncomparison.CheeseCakeFactory;
import com.example.yuriy.dependencyinjectioncomparison.ILogger;
import com.example.yuriy.dependencyinjectioncomparison.IPieFactory;
import com.example.yuriy.dependencyinjectioncomparison.LogcatLogger;
import com.example.yuriy.dependencyinjectioncomparison.PieRegisty;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yuriy on 17.10.2015.
 */

//dagger2 start
@Module
public class Dagger2Module {
    @Provides
    @Singleton
    public ILogger provideILogger() {
        return new LogcatLogger();
    }

    @Provides
    @Singleton
    public PieRegisty provideSender(ILogger logger) {
        return new PieRegisty(logger);
    }

    @Provides
    @Singleton
    public IPieFactory provideIPieFactory(PieRegisty sender) {
        return new CheeseCakeFactory(sender);
    }
}
//dagger2 end