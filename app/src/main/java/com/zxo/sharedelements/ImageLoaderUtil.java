package com.zxo.sharedelements;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by xiaoouzhai on 16/12/8.
 */

public class ImageLoaderUtil {
    public static void load(ImageView imageView,String uri){
        Picasso.with(imageView.getContext())
                .load(uri)
                .noFade()
                .into(imageView);
    }
}
