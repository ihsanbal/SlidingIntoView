package com.ihsanbal.io;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ihsanbal.introview.IntroView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.intro_view)
    IntroView mIntroView;
    @BindView(R.id.circle_indicator)
    CircleIndicator mCircleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mIntroView.init(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titles),
                getResources().getStringArray(R.array.texts),
                R.drawable.istanbul_wp, R.drawable.rize_wp, R.drawable.diyarbekir_wp, R.drawable.izmir_wp);
        mCircleIndicator.setViewPager(mIntroView);
    }
}
