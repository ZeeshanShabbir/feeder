package io.droidninja.feeder;

import android.app.Application;

import io.droidninja.feeder.di.components.ApplicationComponent;
import io.droidninja.feeder.di.components.BaseComponent;
import io.droidninja.feeder.di.components.DaggerApplicationComponent;
import io.droidninja.feeder.di.components.DaggerBaseComponent;
import io.droidninja.feeder.di.modules.ApplicationModule;
import io.droidninja.feeder.di.modules.ContextModule;
import timber.log.Timber;

/**
 * Created by Zeeshan on 2/7/17.
 */

public class FeederApplication extends Application {

    private static BaseComponent sBaseComponent;
    BaseComponent feederApplicationComponent;

    public static BaseComponent getsBaseComponent() {
        return sBaseComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        sBaseComponent = DaggerBaseComponent.builder()
                .applicationComponent(applicationComponent)
                .contextModule(new ContextModule(this))
                .build();

        Timber.plant(new Timber.DebugTree());
    }

    public BaseComponent component() {
        return feederApplicationComponent;
    }
}
