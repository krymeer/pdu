package czernik.osada.placezabaw.CommentsListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import czernik.osada.placezabaw.R;

public class CommentsListAdapter extends BaseAdapter {
    private Context context;
    private List<CommentsListItem> items;

    public CommentsListAdapter(Context context, List<CommentsListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.comments_list_item, viewGroup, false);
        }

        CommentsListItem commentItem    = (CommentsListItem) getItem(position);
        TextView authorTextView         = view.findViewById(R.id.comment_author);
        TextView contentsTextView       = view.findViewById(R.id.comment_contents);

        authorTextView.setText(commentItem.getAuthorName());
        contentsTextView.setText(commentItem.getContents());

        // TODO layout

        return view;
    }
}