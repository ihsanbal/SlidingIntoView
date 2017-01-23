package com.ihsanbal.introview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TEXT_BOTTOM;
import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TEXT_LEFT;
import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TEXT_RIGHT;
import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TEXT_TOP;
import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TITLE_BOTTOM;
import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TITLE_LEFT;
import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TITLE_RIGHT;
import static com.ihsanbal.introview.FragmentPage.ARG_PADDING_TITLE_TOP;
import static com.ihsanbal.introview.FragmentPage.ARG_SCALE_TYPE;
import static com.ihsanbal.introview.FragmentPage.ARG_TEXT_COLOR;
import static com.ihsanbal.introview.FragmentPage.ARG_TEXT_SIZE;
import static com.ihsanbal.introview.FragmentPage.ARG_TITLE_COLOR;
import static com.ihsanbal.introview.FragmentPage.ARG_TITLE_SIZE;

/**
 * @author ihsan on 10/10/16.
 */

public class IntroView extends ViewPager {

    private static final int DELAY_MILLIS = 6000;
    private static final int DURATION_FACTOR = 4;

    private int getDelayMillis = DELAY_MILLIS;
    private int getDurationFactor = DURATION_FACTOR;

    private Handler mHandler;
    private Runnable mRunnable;
    private AdapterPageSection mAdapterPageSection;
    private Bundle mBundle;

    public IntroView(Context context) {
        super(context);
        postInitViewPager(context, null);
    }

    public IntroView(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager(context, attrs);
    }

    /**
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private void postInitViewPager(Context context, @Nullable AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            ScrollerCustomDuration mScroller = new ScrollerCustomDuration(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);

            mHandler = new Handler();
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    if (getCurrentItem() >= mAdapterPageSection.getCount() - 1) {
                        setCurrentItem(0, true);
                    } else
                        setCurrentItem(getCurrentItem() + 1, true);
                    mHandler.postDelayed(mRunnable, getDelayMillis);
                }
            };

            if (attrs != null) {
                TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IntroView);
                getDelayMillis = typedArray.getInteger(R.styleable.IntroView_scrollDelay, DELAY_MILLIS);
                getDurationFactor = typedArray.getInteger(R.styleable.IntroView_scrollDuration, DURATION_FACTOR);
                mBundle = new Bundle();
                mBundle.putFloat(ARG_PADDING_TITLE_TOP, typedArray.getDimension(R.styleable.IntroView_paddingTitleTop, 50));
                mBundle.putFloat(ARG_PADDING_TITLE_BOTTOM, typedArray.getDimension(R.styleable.IntroView_paddingTitleBottom, 0));
                mBundle.putFloat(ARG_PADDING_TITLE_LEFT, typedArray.getDimension(R.styleable.IntroView_paddingTitleLeft, 0));
                mBundle.putFloat(ARG_PADDING_TITLE_RIGHT, typedArray.getDimension(R.styleable.IntroView_paddingTitleRight, 0));

                mBundle.putFloat(ARG_PADDING_TEXT_TOP, typedArray.getDimension(R.styleable.IntroView_paddingTextTop, 0));
                mBundle.putFloat(ARG_PADDING_TEXT_BOTTOM, typedArray.getDimension(R.styleable.IntroView_paddingTextBottom, 0));
                mBundle.putFloat(ARG_PADDING_TEXT_LEFT, typedArray.getDimension(R.styleable.IntroView_paddingTextLeft, 0));
                mBundle.putFloat(ARG_PADDING_TEXT_RIGHT, typedArray.getDimension(R.styleable.IntroView_paddingTextRight, 0));

                mBundle.putParcelable(ARG_TITLE_COLOR, typedArray.getColorStateList(R.styleable.IntroView_titleTextColor));
                mBundle.putParcelable(ARG_TEXT_COLOR, typedArray.getColorStateList(R.styleable.IntroView_textColor));

                mBundle.putFloat(ARG_TITLE_SIZE, typedArray.getDimension(R.styleable.IntroView_titleTextSize, 32));
                mBundle.putFloat(ARG_TEXT_SIZE, typedArray.getDimension(R.styleable.IntroView_textSize, 17));

                mBundle.putInt(ARG_SCALE_TYPE, typedArray.getInt(R.styleable.IntroView_scaleType, 0));
                typedArray.recycle();
            }
            mScroller.setScrollDurationFactor(getDurationFactor);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(FragmentManager fm, String[] titleRes, String[] textRes, int... drawableRes) {
        mAdapterPageSection = new AdapterPageSection(fm, mBundle, titleRes, textRes, drawableRes);
        setAdapter(mAdapterPageSection);
        ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.page_title, -0.65f, ParallaxPageTransformer.ParallaxTransformInformation.PARALLAX_EFFECT_DEFAULT))
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.page_text, -1.85f, ParallaxPageTransformer.ParallaxTransformInformation.PARALLAX_EFFECT_DEFAULT))
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.page_image, 2, 2));
        setPageTransformer(false, pageTransformer);
        mHandler.postDelayed(mRunnable, getDelayMillis);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mHandler.removeCallbacks(mRunnable);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mHandler.postDelayed(mRunnable, DELAY_MILLIS);
        }
        return super.onTouchEvent(event);
    }
}
