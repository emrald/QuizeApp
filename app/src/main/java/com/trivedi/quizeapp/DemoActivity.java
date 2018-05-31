package com.trivedi.quizeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by TI A1 on 21-06-2017.
 */
public class DemoActivity extends FragmentActivity {
    static final int NUM_ITEMS = 10;
    PlanetFragmentPagerAdapter planetFragmentPagerAdapter;
    ViewPager viewPager;
    int counter = 1;
    ArrayList<String> new_list = new ArrayList<String>();
    static ArrayList<String> final_ans_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pager);
        planetFragmentPagerAdapter = new PlanetFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(planetFragmentPagerAdapter);
        Button button = (Button) findViewById(R.id.goto_first);
        button.setOnClickListener(btnListener);
        button = (Button) findViewById(R.id.goto_previous);
        button.setOnClickListener(btnListener);
        button = (Button) findViewById(R.id.goto_next);
        button.setOnClickListener(btnListener);
        button = (Button) findViewById(R.id.goto_next);
        button.setOnClickListener(btnListener);
    }
    private View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.goto_first:
                    viewPager.setCurrentItem(0);
                    counter = 1;
                    break;
                case R.id.goto_previous:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    counter--;
                    break;
                case R.id.goto_next:
                    counter++;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    if (counter == 11) {
                       /* if(final_ans_list.contains("144")|| final_ans_list.contains("26") || final_ans_list.contains("Blue")||final_ans_list.contains("Steel")||final_ans_list.contains("Cheetah")) {
                            new_list.addAll(final_ans_list);
                        }*/
                        if (final_ans_list.contains("144")) {
                            new_list.add("144");
                        }
                        if (final_ans_list.contains("26")) {
                            new_list.add("26");
                        }
                        if (final_ans_list.contains("Blue")) {
                            new_list.add("Blue");
                        }
                        if (final_ans_list.contains("Steel")) {
                            new_list.add("Steel");
                        }
                        if (final_ans_list.contains("Cheetah")) {
                            new_list.add("Cheetah");
                        }
                        if (final_ans_list.contains("100")) {
                            new_list.add("100");
                        }
                        if (final_ans_list.contains("366")) {
                            new_list.add("366");
                        }
                        if (final_ans_list.contains("70 minutes")) {
                            new_list.add("70 minutes");
                        }
                        if (final_ans_list.contains("30")) {
                            new_list.add("30");
                        }
                        if (final_ans_list.contains("Watt")) {
                            new_list.add("Watt");
                        }
                        //     ArrayList<String> values = new ArrayList<String>();
                        HashSet<String> hashSet = new HashSet<String>();
                        hashSet.addAll(new_list);
                        new_list.clear();
                        new_list.addAll(hashSet);

                   /*     for(int i = 0;i<new_list.size();i++)
                        Log.e("Counter..."+i+" - ",new_list.get(i)+"");*/
                        Log.e("Counter..." + " - ", new_list.size() + "");
                        Toast.makeText(DemoActivity.this, "Your Score is : " + new_list.size(), Toast.LENGTH_SHORT).show();
                    //    counter = 1;
                    }
                    break;
                case R.id.goto_last:
                    viewPager.setCurrentItem(NUM_ITEMS - 1);
                    counter = 10;
                    break;
            }
        }
    };

    public static class PlanetFragmentPagerAdapter extends FragmentPagerAdapter {
        public PlanetFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            Log.e("Total...", NUM_ITEMS + "");
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            SwipeFragment fragment = new SwipeFragment();
            return SwipeFragment.newInstance(position);
        }
    }

    public static class SwipeFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View swipeView = inflater.inflate(R.layout.swipe_fragment, container, false);
            TextView tv = (TextView) swipeView.findViewById(R.id.text);
            final RadioGroup group = (RadioGroup) swipeView.findViewById(R.id.rdgroup);
            final RadioButton radioButton = (RadioButton) swipeView.findViewById(R.id.radioButton);
            final RadioButton radioButton2 = (RadioButton) swipeView.findViewById(R.id.radioButton2);
            final RadioButton radioButton3 = (RadioButton) swipeView.findViewById(R.id.radioButton3);
            final RadioButton radioButton4 = (RadioButton) swipeView.findViewById(R.id.radioButton4);
            //  ImageView img = (ImageView)swipeView.findViewById(R.id.imageView);
            Bundle args = getArguments();
            int position = args.getInt("position");
            String planet = Planet.PLANETS[position];
            int imgResId = getResources().getIdentifier(planet, "drawable", "com.javapapers.android.androidswipeableviews.app");
            //  img.setImageResource(imgResId);
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
            databaseAccess.open();
            ArrayList<DataClass> quotes = databaseAccess.getQuotes();
            Log.e("Size",quotes.size()+":Size");
            databaseAccess.close();

            Log.e("position",position+":position");

            String data = quotes.get(position).getAnswer();
            ArrayList<String> ans_list = new ArrayList<String>();
            String[] items = data.split(",");
            for (String item : items) {
                System.out.println("item = " + item);
                ans_list.add(item);
            }

            tv.setText(quotes.get(position).getQuestion());
            radioButton.setText(ans_list.get(0));
            radioButton2.setText(ans_list.get(1));
            radioButton3.setText(ans_list.get(2));
            radioButton4.setText(ans_list.get(3));

            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButton:
                            // TODO Something
                            Log.e("radiobutton", radioButton.getText().toString());
                            final_ans_list.add(radioButton.getText().toString());
                            break;
                        case R.id.radioButton2:
                            // TODO Something
                            Log.e("radiobutton2", radioButton2.getText().toString());
                            final_ans_list.add(radioButton2.getText().toString());
                            break;
                        case R.id.radioButton3:
                            // TODO Something
                            Log.e("radiobutton3", radioButton3.getText().toString());
                            final_ans_list.add(radioButton3.getText().toString());
                            break;
                        case R.id.radioButton4:
                            // TODO Something
                            Log.e("radiobutton4", radioButton4.getText().toString());
                            final_ans_list.add(radioButton4.getText().toString());
                            break;
                    }
                }
            });

            return swipeView;
        }

        static SwipeFragment newInstance(int position) {
            SwipeFragment swipeFragment = new SwipeFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            Log.e("Swipe Pos...",position+"");
            swipeFragment.setArguments(args);
            return swipeFragment;
        }
    }
}