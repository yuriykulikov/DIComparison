package com.example.yuriy.dependencyinjectioncomparison;

import javax.inject.Inject;

/**
 * Created by Yuriy on 16.10.2015.
 */
public class CheeseCakeFactory implements IPieFactory {
    private final PieRegisty pieRegisty;

    @Inject
    public CheeseCakeFactory(PieRegisty pieRegisty){
        this.pieRegisty = pieRegisty;
    }

    @Override
    public IPie createPie() {
        CheeseCake cheeseCake = new CheeseCake();
        pieRegisty.addPieToRegistry(cheeseCake);
        return cheeseCake;
    }
}
