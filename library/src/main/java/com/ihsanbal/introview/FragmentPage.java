package com.ihsanbal.introview;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author ihsan on 10/10/16.
 */
@SuppressLint("ValidFragment")
class FragmentPage extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_TITLE = "section_title";
    private static final String ARG_SECTION_TEXT = "section_text";

    public static final String ARG_TEXT_SIZE = "attr_text_size";
    public static final String ARG_TITLE_SIZE = "attr_title_size";
    public static final String ARG_TEXT_COLOR = "attr_text_color";
    public static final String ARG_TITLE_COLOR = "attr_title_color";
    public static final String ARG_PADDING_TITLE_TOP = "attr_title_padding_top";
    public static final String ARG_PADDING_TITLE_BOTTOM = "attr_title_padding_bottom";
    public static final String ARG_PADDING_TITLE_LEFT = "attr_title_padding_left";
    public static final String ARG_PADDING_TITLE_RIGHT = "attr_title_padding_right";
    public static final String ARG_PADDING_TEXT_TOP = "attr_text_padding_top";
    public static final String ARG_PADDING_TEXT_BOTTOM = "attr_text_padding_bottom";
    public static final String ARG_PADDING_TEXT_LEFT = "attr_text_padding_left";
    public static final String ARG_PADDING_TEXT_RIGHT = "attr_text_padding_right";
    public static final String ARG_SCALE_TYPE = "attr_scale_type";
    private static final String BUNDLE = "args_bundle";

    FragmentPage() {

    }

    static FragmentPage newInstance(int sectionNumber, String sectionTitle, String sectionText, Bundle bundle) {
        FragmentPage fragment = new FragmentPage();
        Bundle args = new Bundle();
        if (bundle != null) {
            args.putBundle(BUNDLE, bundle);
        }
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putString(ARG_SECTION_TEXT, sectionText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.page_image);
        TextView title = (TextView) rootView.findViewById(R.id.page_title);
        TextView text = (TextView) rootView.findViewById(R.id.page_text);

        Bundle arr = getArguments();
        Bundle bundle = arr.getBundle(BUNDLE);

        title.setText(arr.getString(ARG_SECTION_TITLE));
        text.setText(arr.getString(ARG_SECTION_TEXT));
        if (bundle != null) {
            title.setPadding((int) bundle.getFloat(ARG_PADDING_TITLE_LEFT), (int) bundle.getFloat(ARG_PADDING_TITLE_TOP),
                    (int) bundle.getFloat(ARG_PADDING_TITLE_RIGHT), (int) bundle.getFloat(ARG_PADDING_TITLE_BOTTOM));

            text.setPadding((int) bundle.getFloat(ARG_PADDING_TEXT_LEFT), (int) bundle.getFloat(ARG_PADDING_TEXT_TOP),
                    (int) bundle.getFloat(ARG_PADDING_TEXT_RIGHT), (int) bundle.getFloat(ARG_PADDING_TEXT_BOTTOM));


            ColorStateList titleColor = bundle.getParcelable(ARG_TITLE_COLOR);
            if (titleColor != null) {
                title.setTextColor(titleColor);
            }
            ColorStateList textColor = bundle.getParcelable(ARG_TEXT_COLOR);
            if (textColor != null) {
                text.setTextColor(textColor);
            }

            title.setTextSize(bundle.getFloat(ARG_TITLE_SIZE));
            text.setTextSize(bundle.getFloat(ARG_TEXT_SIZE));
        }

        imageView.setImageResource(arr.getInt(ARG_SECTION_NUMBER));

        switch (bundle != null ? bundle.getInt(ARG_SCALE_TYPE) : 0) {
            case 0:
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case 1:
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case 2:
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case 3:
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
            case 4:
                imageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;
            case 5:
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;
            case 6:
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case 7:
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                break;
            default:
                break;
        }
        return rootView;
    }
}
