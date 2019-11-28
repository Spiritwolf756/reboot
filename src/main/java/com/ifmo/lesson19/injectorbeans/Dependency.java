package com.ifmo.lesson19.injectorbeans;

import com.ifmo.lesson19.injector.Inject;

public class Dependency {
    @Inject
    private DependencyOfDependency testedBean2;

    @Override
    public String toString() {
        return "Dependency{" +
                "testedBean2=" + testedBean2 +
                '}';
    }
}
