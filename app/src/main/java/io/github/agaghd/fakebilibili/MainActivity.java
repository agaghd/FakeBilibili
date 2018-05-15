package io.github.agaghd.fakebilibili;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private long backpressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataInit();
    }

    private void dataInit() {
        backpressedTime = 0;
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        long detaTime = currentTime - backpressedTime;
        if (detaTime < 2000) {
            System.exit(0);
        } else {
            String text = getString(R.string.press_back_twice_to_exit);
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            backpressedTime = currentTime;
        }
    }
}
