/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
/**
 * Provide views to RecyclerView with data from mDataSet.
 */
package co.sethspace.myapplication;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anirudh on 5/20/2015.
 */
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        //private static final String TAG = "MyAdapter";

        private ArrayList<RedditPosts> mDataSet;

        /**
         * Provide a reference to the type of views that you are using (custom ViewHolder)
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public  TextView textView1;
            public  TextView textView2;
            public  TextView textView3;
            public ViewHolder(View v) {
                super(v);
                textView1 = (TextView)v.findViewById(R.id.textView1);
                textView2 = (TextView)v.findViewById(R.id.textView2);
                textView3 = (TextView)v.findViewById(R.id.textView3);
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
         */
        public MyAdapter(List<RedditPosts> dataSet) {
            mDataSet = (ArrayList<RedditPosts>) dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view.
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.text_row_item, viewGroup, false);

            return new ViewHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            //Log.d(TAG, "Element " + position + " set.");

            // Get element from your dataset at this position and replace the contents of the view
            // with that element
            viewHolder.textView1.setText(mDataSet.get(position).getTitle());
            viewHolder.textView2.setText(mDataSet.get(position).getAuthor());
            viewHolder.textView3.setText((mDataSet.get(position).getUpvotes()));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
    }
