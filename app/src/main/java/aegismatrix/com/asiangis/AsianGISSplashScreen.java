package aegismatrix.com.asiangis;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Observable;
import java.util.Observer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Main class for splash screen.
 */
public class AsianGISSplashScreen extends AppCompatActivity implements Observer {
    private ProgressBar progressBar;
    private NetworkChangeReceiver networkChangeReceiverSplashScreen = new NetworkChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        ObservableObject.getInstance().addObserver(this);
        registerReceiver(networkChangeReceiverSplashScreen, intentFilter);
    }

    /**
     * Transition from Left to Right
     *
     * @param context
     */
    public void animateSlideLeft(Context context) {
        ((AsianGISSplashScreen) context).overridePendingTransition(R.anim.animate_slide_left_enter, R.anim.animate_slide_left_exit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ObservableObject.getInstance().deleteObserver(this);
        unregisterReceiver(networkChangeReceiverSplashScreen);
    }

    @Override
    public void update(Observable observable, Object intent) {
        if (null != intent) {
            boolean status = ((Intent) intent).getBooleanExtra("status", Boolean.TRUE);
            Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), status ? "Connected to internet." : "Check internet connection!!", status ? Snackbar.LENGTH_SHORT : Snackbar.LENGTH_INDEFINITE);
            // get snackbar view
            View mView = snackBar.getView();
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) mView;

            layout.setPadding(0, 0, 0, 0);//set padding to 0
            // get textview inside snackbar view
            TextView mTextView = mView.findViewById(com.google.android.material.R.id.snackbar_text);
            // set text to center
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            } else {
                mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            mTextView.setBackgroundColor(ContextCompat.getColor(AsianGISSplashScreen.this, status ? R.color.colorGreen : R.color.design_default_color_error));
            // show the snackbar
            snackBar.show();

            if (status) {
                Intent mainIntent = new Intent(AsianGISSplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                animateSlideLeft(this);
                finish();
            }
        }
    }
}
