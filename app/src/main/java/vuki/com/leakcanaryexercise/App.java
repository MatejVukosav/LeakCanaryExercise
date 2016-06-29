package vuki.com.leakcanaryexercise;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by mvukosav on 29.6.2016..
 */
public class App extends Application {

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher( Context context ) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initScreenOrientation();
        initLeakCanary();
    }

    private void initLeakCanary() {
        refWatcher = LeakCanary.install( this );
    }

    private void initScreenOrientation() {
        this.registerActivityLifecycleCallbacks( new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated( Activity activity, Bundle savedInstanceState ) {
                activity.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
            }

            @Override
            public void onActivityStarted( Activity activity ) {

            }

            @Override
            public void onActivityResumed( Activity activity ) {

            }

            @Override
            public void onActivityPaused( Activity activity ) {

            }

            @Override
            public void onActivityStopped( Activity activity ) {

            }

            @Override
            public void onActivitySaveInstanceState( Activity activity, Bundle outState ) {

            }

            @Override
            public void onActivityDestroyed( Activity activity ) {

            }
        } );
    }
}
