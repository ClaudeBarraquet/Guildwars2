package com.example.claude.guildwars2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Konynrith on 20/02/2018.
 */

public class CustomAdapterCharac extends ArrayAdapter<Character> {


    public CustomAdapterCharac(Context context, List<Character> charac) {
        super(context, 0,charac);

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.listcharactadapter,parent, false);
        }

        CharacView viewHolder = (CharacView) view.getTag();

        if(viewHolder == null){
            viewHolder = new CharacView();
            viewHolder.name = (TextView) view.findViewById(R.id.CharacName);
            viewHolder.profe = (TextView) view.findViewById(R.id.Profession);
            viewHolder.level = (TextView) view.findViewById(R.id.Level);
            viewHolder.logo = (ImageView) view.findViewById(R.id.Logo);
            view.setTag(viewHolder);
        }

        Character charact = getItem(position);
        viewHolder.name.setText(charact.getName());
        viewHolder.profe.setText(charact.getProfe());
        viewHolder.level.setText(charact.getLevel());
        viewHolder.logo.setImageBitmap(charact.getLogo());

        return view;

    }

    private class CharacView{
        public TextView name;
        public TextView profe;
        public TextView level;
        public ImageView logo;
    }
}
