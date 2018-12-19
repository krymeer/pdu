package czernik.osada.placezabaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import czernik.osada.placezabaw.CommentsListAdapter.*;

public class PlaygroundCommentsScreen extends AppCompatActivity {
    ListView commentsListView;
    TextView addressTextView;
    String address;

    private List<CommentsListItem> getComments(int count) {
        List<CommentsListItem> comments  = new ArrayList<>();
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

        List<CommentsListItem> itemsList = getComments(5);
        CommentsListAdapter commentsListAdapter = new CommentsListAdapter(this, itemsList);
        commentsListView = findViewById(R.id.playgroundComments_list);
        commentsListView.setAdapter(commentsListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_comments);
        initCommentList();

        addressTextView = findViewById(R.id.playgroundComments_address);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            if (intent.hasExtra("address")) {
                address = bundle.getString("address");
                addressTextView.setText(address);
            }
        }
    }

    public void onAddCommentBtnClick(View view) {
        Intent intent = new Intent(this, AddCommentScreen.class);
        intent.putExtra("address", address);
        startActivity(intent);
    }
}
