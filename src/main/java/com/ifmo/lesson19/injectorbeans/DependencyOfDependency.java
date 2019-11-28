package com.ifmo.lesson19.injectorbeans;

import com.ifmo.lesson19.injector.Inject;

public class DependencyOfDependency {
    @Inject(singleton = false)
    private String emptyString;

    @Override
    public String toString() {
        return "DependencyOfDependency{" +
                "emptyString='" + emptyString + '\'' +
                '}';
    }
}
