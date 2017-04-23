package com.joelspicer.idleoutpost;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

        ImageButton imagebutton;
        TextView textview;


        public PageFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.page_fragment_layout,container,false);
            imagebutton = (ImageButton) view.findViewById(R.id.dollarBtn);
            textview = (TextView) view.findViewById(R.id.swipetext);

            Bundle bundle = getArguments();
            String message = Integer.toString(bundle.getInt("count"));


            if (message == "1")
                imagebutton.setImageResource(R.drawable.tap1);

            else if (message == "2")
                imagebutton.setImageResource(R.drawable.tap2);

            else if (message == "3")
                imagebutton.setImageResource(R.drawable.tap3);









            textview.setText("Page"+message);


            return view;
        }

}
