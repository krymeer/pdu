package czernik.osada.placezabaw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import czernik.osada.placezabaw.CommentsListAdapter.*;

public class PlaygroundCommentsScreen extends AppCompatActivity {
    private String street;
    private String town;
    private int id;

    private List<CommentsListItem> getComments() {
        List<CommentsListItem> comments = new ArrayList<>();
        comments.add(new CommentsListItem(0, "Grażyna W.", "2018-12-19", 0.5f, "Lorem ipsum dolor sit amet"));
        comments.add(new CommentsListItem(1, "Adam M.", "2018-11-19", 2.5f, "Consecteur adipiscing elit"));
        comments.add(new CommentsListItem(2, "Krzysztof O.", "2018-10-19", 4.5f, "Lorem ipsum dolor sit amet"));
        comments.add(new CommentsListItem(3, "Michał Cz.", "2018-09-19", 3.0f, "Consecteur adipiscing elit"));
        comments.add(new CommentsListItem(4, "Bronisław K.", "2018-08-19", 5.0f, "Lorem ipsum dolor sit amet"));
        comments.add(new CommentsListItem(5, "Romuald W.", "2018-08-19", 3.0f, "Consecteur adipiscing elit"));
        comments.add(new CommentsListItem(6, "Jakub O.", "2018-08-19", 5.0f, "Lorem ipsum dolor sit amet"));
        return comments;
    }

    private void initCommentList() {
        List<CommentsListItem> itemsList        = getComments();
        CommentsListAdapter commentsListAdapter = new CommentsListAdapter(this, itemsList);
        ListView commentsListView               = findViewById(R.id.playgroundComments_list);
        View buttonView                         = View.inflate(this, R.layout.button_add_comment, null);

        commentsListView.setAdapter(commentsListAdapter);
        commentsListView.addFooterView(buttonView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_comments);
        initCommentList();

        TextView streetTextView     = findViewById(R.id.playgroundComments_street);
        TextView townTextView       = findViewById(R.id.playgroundComments_town);
        Intent intent               = getIntent();
        Bundle bundle               = intent.getExtras();

        if (bundle != null)
        {
            if (intent.hasExtra("playgroundId"))
            {
                this.id = bundle.getInt("playgroundId");
            }

            if (intent.hasExtra("street"))
            {
                this.street = bundle.getString("street");
                streetTextView.setText(this.street);
            }

            if (intent.hasExtra("town"))
            {
                this.town = bundle.getString("town");
                townTextView.setText(this.town);
            }
        }
    }

    public void onAddCommentBtnClick(View view) {
        Intent intent   = new Intent(this, AddCommentScreen.class);
        String address  = this.town + ", " + this.street;
        intent.putExtra("playgroundId", this.id);
        intent.putExtra("address", address);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            Snackbar.make(findViewById(android.R.id.content), R.string.request_sent, Snackbar.LENGTH_LONG).show();
        }
    }
}
