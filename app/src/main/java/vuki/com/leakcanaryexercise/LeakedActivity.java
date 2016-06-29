package vuki.com.leakcanaryexercise;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

public class LeakedActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_leaked );

        new MyAsyncTask().execute( this );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher( this );
        refWatcher.watch( this );
    }

    public class MyAsyncTask extends AsyncTask<Object, String, String> {
        private Context context;

        @Override
        protected String doInBackground( Object... params ) {
            context = (Context) params[0];
            //Invoke the leak
            SingletonSavesContext.getInstance().setContext( context );

            //Simulate long running task
            try {
                Thread.sleep( 3000 );
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }

            return "result";
        }

        @Override
        protected void onPostExecute( String s ) {
            super.onPostExecute( s );

            Intent intent = new Intent( context, AnotherActivity.class );
            startActivity( intent );
        }
    }

}
