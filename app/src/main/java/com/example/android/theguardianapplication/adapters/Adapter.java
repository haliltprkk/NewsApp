package com.example.android.theguardianapplication.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.theguardianapplication.R;
import com.example.android.theguardianapplication.models.Guardian;

import java.util.ArrayList;

/**
 * Created by LENOVO on 22.11.2017.
 */

public class Adapter extends ArrayAdapter<Guardian> {

    private static final String LOG_TAG = Adapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of AndroidFlavor objects to display in a list
     */
    public Adapter(Activity context, ArrayList<Guardian> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view_items, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Guardian currentWord = getItem(position);
        TextView textViewArticle = (TextView) listItemView.findViewById(R.id.title_of_article_text_view);
        TextView textViewSection = (TextView) listItemView.findViewById(R.id.section_name_text_view);

        textViewArticle.setText(currentWord.getTitleOfArticle());
        textViewSection.setText(currentWord.getNameOfSection());

        return listItemView;
    }


}
