package com.zxo.sharedelements;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private GridView    mGridView;
    private GridAdapter mAdapter;
    private ArrayList<String>   mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(this);
        mAdapter = new GridAdapter(mData,this);
        mGridView.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add("http://img15.3lian.com/2015/h1/280/d/5.jpg");
        mData.add("http://img15.3lian.com/2015/h1/280/d/4.jpg");
        mData.add("http://img15.3lian.com/2015/h1/280/d/10.jpg");
        mData.add("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1402/27/c4/31612517_1393474458218_mthumb.jpg");
        mData.add("http://img5.duitang.com/uploads/item/201509/29/20150929205118_8Jcz5.jpeg");
        mData.add("http://img1.3lian.com/2015/w22/48/d/101.jpg");
        mData.add("http://scimg.jb51.net/allimg/150925/14-1509250Z615109.jpg");
        mData.add("http://a.hiphotos.baidu.com/zhidao/pic/item/8b13632762d0f703c935737c0afa513d2797c58f.jpg");
        mData.add("http://www.th7.cn/d/file/p/2016/11/14/5bf8210740d4eb2ce018eb90ef57f539.jpg");
        mData.add("http://img.weixinyidu.com/151222/c63f96b7.jpg");

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_PARAM_ID,mData.get(position));

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair<View, String>(view.findViewById(R.id.imageview_item),DetailActivity.VIEW_NAME_HEADER_IMAGE),
                new Pair<View, String>(view.findViewById(R.id.textview_name),DetailActivity.VIEW_NAME_HEADER_TITLE));

        ActivityCompat.startActivity(this,intent,activityOptions.toBundle());
    }


    private class GridAdapter extends BaseAdapter{

        private ArrayList<String> list;
        private LayoutInflater  mInflater;
        public GridAdapter(ArrayList<String> list,Context context){
            this.list = list;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return null!= list && list.size()>0 ?list.size():0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null){
                view = mInflater.inflate(R.layout.gridview_item,null);
            }
            String uri = list.get(position);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview_item);
            ImageLoaderUtil.load(imageView,uri);
            return view;
        }
    }
}
