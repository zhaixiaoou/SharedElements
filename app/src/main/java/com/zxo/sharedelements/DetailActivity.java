package com.zxo.sharedelements;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * Created by xiaoouzhai on 16/12/8.
 */

public class DetailActivity extends Activity {

    private static final String TAG = "DetailActivity";
    public static final String EXTRA_PARAM_ID = "detail_id";

    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    public static final String VIEW_NAME_HEADER_TITLE = "detail:header:title";

    private ImageView mHeaderImageView;
    private TextView        mHeaderTitle;
    private String mUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mUri = getIntent().getStringExtra(EXTRA_PARAM_ID);
        mHeaderImageView = (ImageView) findViewById(R.id.imageview_header);
        mHeaderTitle     = (TextView) findViewById(R.id.textview_title);

        /**
         * 用静态变量设置transition name
         * 也可以在xml中设置,但是通过静态变量的方式,容易从其他Activity中查询
         */
        ViewCompat.setTransitionName(mHeaderImageView,VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(mHeaderTitle,VIEW_NAME_HEADER_TITLE);

        loadItem();
    }

    private void loadItem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && addTransitionListener()){
            loadThumbnail();
        }else{
            loadFullImage();
        }
    }


    private void loadThumbnail() {
        Logger.d(mUri);
//        Picasso.with(mHeaderImageView.getContext())
//                .load(mUri)
//                .noFade()
//                .into(mHeaderImageView);
        ImageLoaderUtil.load(mHeaderImageView,mUri);
    }

    private void loadFullImage(){
//        Picasso.with(mHeaderImageView.getContext())
//                .load(mUri)
//                .noFade()
//                .noPlaceholder()
//                .into(mHeaderImageView);
        ImageLoaderUtil.load(mHeaderImageView,mUri);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        Transition transition = getWindow().getSharedElementEnterTransition();

        if (transition != null){
            //enter Shared
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    loadFullImage();
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
            return true;
        }
        // 没有添加listener
        return false;
    }

}
