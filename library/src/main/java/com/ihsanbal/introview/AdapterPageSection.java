package com.ihsanbal.introview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ihsan on 10/10/16.
 */

public class AdapterPageSection extends FragmentPagerAdapter {

    private final Bundle bundle;
    private int[] resourcesId;
    private String[] titleResources, textResource;

    public AdapterPageSection(FragmentManager fm, Bundle bundle, String[] titleResources, String[] textResources, int[] resourcesId) {
        super(fm);
        this.resourcesId = resourcesId;
        this.titleResources = titleResources;
        this.textResource = textResources;
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentPage.newInstance(getResourceId(position), getTitleResource(position), getTextResource(position), bundle);
    }

    private int getResourceId(int i) {
        return resourcesId[i];
    }

    private String getTitleResource(int i) {
        if (titleResources == null || titleResources.length < i + 1)
            return "";
        else
            return titleResources[i];
    }

    private String getTextResource(int i) {
        if (textResource == null || textResource.length < i + 1)
            return "";
        else
            return textResource[i];
    }

    @Override
    public int getCount() {
        return resourcesId != null ? resourcesId.length : 0;
    }
}
