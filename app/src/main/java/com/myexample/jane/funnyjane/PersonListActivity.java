package com.myexample.jane.funnyjane;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonListActivity extends ListActivity {

    private ProgressDialog dialog = null;

    @Override
    public void onCreate(Bundle c) {
        super.onCreate(c);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Wait");
        dialog.setMessage("Read files...");
        dialog.setCancelable(false);
        dialog.show();

        ArrayList<PersonEntity> array = (ArrayList<PersonEntity>) ObjectIOUtils.read(PersonListActivity.this, MainActivity.FILE_PATH);
        if (array != null) {
            SimpleAdapter adapter = new SimpleAdapter(PersonListActivity.this, getData(array), R.layout.listitem,
                    new String[]{"title", "score"},
                    new int[]{R.id.title, R.id.score});
            setListAdapter(adapter);
        }

        // make dialog dismiss after 1 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000);
    }

    private List<Map<String, String>> getData(ArrayList<PersonEntity> array) {
        List<Map<String, String>> list = new ArrayList<>();

        for (PersonEntity p : array) {
            Map<String, String> map = new HashMap<>();
            map.put("title", p.getName());
            map.put("score", p.getScore() + "");
            list.add(map);
        }

        return list;
    }
}
