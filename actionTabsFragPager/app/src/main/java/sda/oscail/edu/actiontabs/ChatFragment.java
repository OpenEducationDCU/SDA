package sda.oscail.edu.actiontabs;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ChatFragment extends Fragment
{
   Button m_pressButton;
    TextView m_textView;
    int m_count=0;
    View.OnClickListener mOnClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.chatfragment, container, false);



        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();


                Snackbar snackbar =Snackbar.make(view, "Count is "+  Integer.toString(m_count), Snackbar.LENGTH_LONG)
                        .setAction("Increment", mOnClickListener).setActionTextColor(Color.GREEN);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(Color.parseColor("#3f51b5"));
                TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snackbar.show();
            }
        });

        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_count++;
            }
        };
        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
           m_count =  savedInstanceState.getInt("COUNT");

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("COUNT",m_count);
    }



}
