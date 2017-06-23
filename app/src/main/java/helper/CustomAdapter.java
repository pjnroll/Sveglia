package helper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pier.sveglia.R;

import java.util.*;

import static helper.Constants.*;

/**
 * Created by pier on 19/06/17.
 */

public class CustomAdapter extends BaseAdapter {
    final static String TAG = "CustomAdapter";

    private TextView txtDescription;
    private TextView txtDetails;
    private ImageView imgAlarm;

    private Context ctx;
    private TreeMap<Integer, ArrayList<String>> data;

    public CustomAdapter(Context ctx, Map<Integer, ArrayList<String>> data) {
        this.ctx = ctx;
        this.data = new TreeMap<>(data);
        if (data == null)
            Log.i(TAG, "DATA NULL");
        else Log.i(TAG, "DATA NON NULL");
        Log.i(TAG, "=================COSTRUTTORE 1=================");
    }

    private TreeMap<Integer, ArrayList<String>> getData() {
        Log.i(TAG, "=================GETDATA 3=================");
        if (data == null)
            Log.i(TAG, "=================ERRORE DATA 4=================");
        else
            Log.i(TAG, "=================NON ERRORE DATA 4=================");
        return data;
    }

    private Context getContext() {
        return ctx;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return getData().get(position);     // Remember to cast it into (ArrayList<String>) in the
                                            // calling method.
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "=================GETVIEW 2=================");
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.list_view_layout, null);

        txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
        txtDetails = (TextView) convertView.findViewById(R.id.txtDetails);
        imgAlarm = (ImageView) convertView.findViewById(R.id.imgAlarm);

        imgAlarm.setImageResource(R.drawable.alarm);

        position++;
        Log.i(TAG, "=================POSITION == " + position + "=================");
        ArrayList<String> value = getData().get(position);
        if (value == null) {
            Log.i(TAG, "=================ERRORE VALUE 5=================");
            capiamoci();

            return null;
        } else
            Log.i(TAG, "=================NON ERRORE VALUE 5=================");
        String description = value.get(POS_LABEL);
        txtDescription.setText(description);

        String details = (value.get(POS_HOURS) + ":" + value.get(POS_MINUTES));
        txtDetails.setText(details);

        return convertView;
    }

    public void capiamoci() {
        // Spero di non dover rientrare mai più qui dentro
        Map<Integer, ArrayList<String>> mMap = getData();
        String stringa = null;
        Log.i(TAG, "SONO ESAUSTO " + (mMap == null));
        ArrayList<String> capiamolo = mMap.get(Integer.valueOf(0));
        Log.i(TAG, "SONO STANCO " + (capiamolo == null));
        for (ArrayList<String> a : mMap.values())
            Log.i("FOREACH A", a.toString());

        stringa = mMap.get(Integer.valueOf(0)).get(Integer.valueOf(0));
        Log.i(TAG, "/////////// CAPIAMOCI //////////");
        Log.i(TAG, stringa);
        Log.i(TAG, "/////////// CAPIAMOCI //////////");
    }

}
