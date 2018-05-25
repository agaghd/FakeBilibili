package io.github.agaghd.fakebilibili;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.agaghd.fakebilibili.utils.Constants;

public class MainActivity extends BaseActivity implements View.OnTouchListener {

    @Bind(R.id.bili_drawerbg_login)
    ImageView biliDrawerbgLogin;
    @Bind(R.id.main_fl)
    FrameLayout mainFl;

    @Bind(R.id.main_drawer)
    DrawerLayout mainDrawer;

    private long backpressedTime;
    private Point touchDownPoint;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setColor(this, getResources().getColor(R.color.mainThemeColor));
        ButterKnife.bind(this);
        setUpListeners();
        dataInit();
    }

    private void setUpListeners() {
        biliDrawerbgLogin.setOnTouchListener(this);
    }

    private void dataInit() {
        backpressedTime = 0;
        //// TODO: 2018/5/18 测试碎片事务
        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl, mainFragment).commit();
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
            case MotionEvent.ACTION_DOWN: {
                touchDownPoint = new Point((int) event.getX(), (int) event.getY());
                biliDrawerbgLogin.setImageResource(R.drawable.bili_drawerbg_not_logined);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (touchDownPoint != null) {
                    Point movePoint = new Point((int) event.getX(), (int) event.getY());
                    int dx = movePoint.x - touchDownPoint.x;
                    int dy = movePoint.y - touchDownPoint.y;
                    if ((dx * dx + dy * dy) > (Constants.TOUCH_SLOP)) {
                        biliDrawerbgLogin.setImageResource(R.drawable.bili_drawerbg_logined);
                    }
                }
                break;
            }
            default: {
                biliDrawerbgLogin.setImageResource(R.drawable.bili_drawerbg_logined);
                break;
            }
        }
        return true;
    }

    public DrawerLayout getMainDrawer() {
        return mainDrawer;
    }
}
