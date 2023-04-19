package lu.uni.graux.bm20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import lu.uni.graux.bm20.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String imei;

    static {
        System.loadLibrary("bm20");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.imei = Settings.Secure.getString(this.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        TextView tv = binding.sampleText;
        tv.setText("Imei:\n");

        try {
            this.returnUsingThrow();
        } catch(Exception e) {
            tv.append("\t"+e.getMessage()+"\n");
        }

        try {
            this.postponeException();
        } catch(Exception e) {
            tv.append("\t"+e.getMessage()+"\n");
        }
    }

    public void returnImeiUsing() throws Exception {
        throw new Exception(this.imei);
    }

    public native void returnUsingThrow() throws Exception;
    public native void postponeException() throws Exception;
}