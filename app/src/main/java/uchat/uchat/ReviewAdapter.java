package uchat.uchat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Chris on 4/20/2016.
 */
public class ReviewAdapter extends ArrayAdapter<Reviews> {

    Context context;
    int layoutResourceId;
    Reviews data[] = null;

    public ReviewAdapter(Context context, int layoutResourceId, Reviews[] data) {
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
            holder.course = (TextView) row.findViewById(R.id.course);
            holder.rating = (TextView) row.findViewById(R.id.browse_rating);
            holder.descrip = (TextView) row.findViewById(R.id.descrip);

            row.setTag(holder);
        }
        else
        {
            holder = (ProfessorsHolder)row.getTag();
        }

        Reviews reviews = data[position];
        holder.course.setText(reviews.course);
        holder.rating.setText(reviews.rating);
        holder.descrip.setText(reviews.descrip);

        return row;
    }

    static class ProfessorsHolder
    {
        TextView course;
        TextView rating;
        TextView descrip;
    }
}
