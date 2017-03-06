package woddiary20.bazaleev.io.woddiary20;

import android.app.Application;
import android.content.Context;

import woddiary20.bazaleev.io.woddiary20.di.AppComponent;
import woddiary20.bazaleev.io.woddiary20.di.AppModuleImpl;
import woddiary20.bazaleev.io.woddiary20.di.WODDiaryComponent;

/**
 * Created by Max Bazaleev on 7/28/16.
 */
public class WODDiaryApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = new WODDiaryComponent(new AppModuleImpl(this));
    }

    private AppComponent getComponent() {
        return component;
    }

    public static AppComponent getAppComponent(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof WODDiaryApplication) {
            return ((WODDiaryApplication) applicationContext).getComponent();
        } else throw new IllegalArgumentException("wrong application type");
    }
}
