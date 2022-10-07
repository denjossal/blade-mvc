package com.dsdev.blade.mvc;

import com.hellokaton.blade.Blade;

public class Application {
    public static void main(String[] args) {
        Blade.create().start(Application.class, args);
    }
}
