package uchat.uchat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.awt.font.TextAttribute;

/**
 * Created by Chris on 4/20/2016.
 */
public class ProfessorsAdapter extends ArrayAdapter<Professors> {
    Context context;
    int layoutResourceId;
    Professors data[] = null;

    public ProfessorsAdapter(Context context, int layoutResourceId, Professors[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProfessorsHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ProfessorsHolder();
            holder.name = (TextView) row.findViewById(R.id.prof_name);
            holder.rating = (TextView) row.findViewById(R.id.rating);

            row.setTag(holder);
        }
        else
        {
            holder = (ProfessorsHolder)row.getTag();
        }

        Professors professors = data[position];
        holder.name.setText(professors.name);
        holder.rating.setText(professors.rating);

        return row;
    }

    static class ProfessorsHolder
    {
        TextView name;
        TextView rating;
    }

}
