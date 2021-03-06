package com.example.movie;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends BaseAdapter {
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<Diary_item> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Diary_item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom2, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */

        ImageView img_posterimg = (ImageView) convertView.findViewById(R.id.img_posterimg) ;
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title) ;


        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        Diary_item myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_title.setText(myItem.getTitle());
        img_posterimg.setImageBitmap(myItem.getPosterImg());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */


        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Bitmap posterimg, String title) {

        Diary_item mItem = new Diary_item();

        /* MyItem에 아이템을 setting한다. */
        mItem.setPosterImg(posterimg);
        mItem.setTitle(title);


        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void clearItem() {

        Diary_item mItem = new Diary_item();

        /* mItems에 MyItem을 추가한다. */
        mItems.clear();

    }



}
