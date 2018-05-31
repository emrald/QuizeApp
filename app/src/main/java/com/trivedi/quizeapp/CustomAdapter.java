package com.trivedi.quizeapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TI A1 on 20-06-2017.
 */
public class CustomAdapter extends ArrayAdapter<DataClass> {
    public LayoutInflater inflater;
    Context context;
    public CustomAdapter(Context context, int textViewResourceId,
                      ArrayList<DataClass> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public static class ViewHolder
    {
        TextView txtViewQuote;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.swipe_fragment, null);

           /* MyPagerAdapter adapter = new MyPagerAdapter(context);
            ViewPager myPager = (ViewPager) convertView.findViewById(R.id.mypager);
            myPager.setId(position + getCount());
            myPager.setAdapter(adapter);

            myPager.setCurrentItem(0);*/

            holder.txtViewQuote = (TextView)convertView.findViewById(R.id.text);
            //ImageView img = (ImageView)convertView.findViewById(R.id.imageView);
          //  Bundle args = getArguments();
        //    int position = args.getInt("position");
        //    String planet = Planet.PLANETS[position];
        //    int imgResId = getResources().getIdentifier(planet, "drawable", "com.javapapers.android.androidswipeableviews.app");
       //     img.setImageResource(imgResId);
            //   DataClass quote=getItem(position);
            //   tv.setText(quote.getQuestion()+" - Wikipedia.");
          //  return swipeView;

         //   holder.txtViewQuote= (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        DataClass quote=getItem(position);
        holder.txtViewQuote.setText(Html.fromHtml(quote.getQuestion()));
        //	holder.txtViewQuote.setText(quote.getQuote());

        return convertView;
    }
}
