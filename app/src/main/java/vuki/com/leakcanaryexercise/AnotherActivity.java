package vuki.com.leakcanaryexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.leakcanary.RefWatcher;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_another_application );

        //Invoke the GC to accelerate the analysis.
        System.gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher( this );
        refWatcher.watch( this );
    }
}
