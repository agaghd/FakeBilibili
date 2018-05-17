package io.github.agaghd.fakebilibili;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnTouchListener {

    @Bind(R.id.bili_drawerbg_login)
    ImageView biliDrawerbgLogin;

    private long backpressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpListeners();
        dataInit();
    }

    private void setUpListeners() {
        biliDrawerbgLogin.setOnTouchListener(this);
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


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.bili_drawerbg_login: {
                return onBiliDrawerbgLoginTouch(event);
            }
            default: {
                break;
            }
        }
        return false;
    }

    private boolean onBiliDrawerbgLoginTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP: {
                biliDrawerbgLogin.setImageResource(R.drawable.bili_drawerbg_not_logined);
                break;
            }
            default: {
                biliDrawerbgLogin.setImageResource(R.drawable.bili_drawerbg_logined);
                break;
            }
        }
        return true;
    }
}
