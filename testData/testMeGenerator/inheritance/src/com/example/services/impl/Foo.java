package com.example.services.impl;

import com.example.parents.impl.FooParent;
import com.example.warriers.FooFighter;
import com.example.foes.Fire;
<caret>
public class Foo extends FooParent{

    private FooFighter fooFighter;

    public String fight(Fire withFire,String foeName) {
        return fooFighter.fight(withFire);
    }

}
