package com.example.yuriy.dependencyinjectioncomparison;

import javax.inject.Inject;

/**
 * Created by Yuriy on 16.10.2015.
 */
public class PieRegisty {
    private final ILogger logger;

    @Inject
    public PieRegisty(ILogger logger) {
        this.logger = logger;
    }

    public void addPieToRegistry(IPie pie) {
        logger.d("Adding " + pie.getName() + "@" + pie.toString());
    }
}
