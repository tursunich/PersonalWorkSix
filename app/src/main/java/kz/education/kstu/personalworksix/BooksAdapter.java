package kz.education.kstu.personalworksix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kz.education.kstu.personalworksix.model.Book;

public class BooksAdapter extends ArrayAdapter<Book> implements View.OnClickListener{
    private int lastPosition = -1;
    private List<Book> dataSet;
    private Context mContext;

    private static class ViewHolder {
        TextView title;
        TextView publisher;
        TextView publishDate;
        TextView price;
    }

    public BooksAdapter(List<Book> data, Context context) {
        super(context, R.layout.list_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Book book = getItem(position);
    }

    private String getFormattedString(int resId, String string){
        return String.format(getContext().getString(resId), string);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);
        ViewHolder viewHolder;

        //final View result;

        //if (convertView == null){

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.title.setText(book.getVolumeInfo().getTitle());
            viewHolder.publisher = (TextView) convertView.findViewById(R.id.publisher);
            viewHolder.publisher.setText(getFormattedString(R.string.publisher, book.getVolumeInfo().getPublisher() != null ? book.getVolumeInfo().getPublisher() : ""));
            viewHolder.publishDate = (TextView) convertView.findViewById(R.id.publishDate);
            viewHolder.publishDate.setText(getFormattedString(R.string.publishDate, book.getVolumeInfo().getPublishDate() != null ? book.getVolumeInfo().getPublishDate() : ""));
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
        if (book.getSaleInfo().getListPrice() != null) {
            String amount = String.valueOf(book.getSaleInfo().getListPrice().getAmount());
            viewHolder.price.setText(getFormattedString(R.string.price, amount + " " + book.getSaleInfo().getListPrice().getCurrency()));
        }
            //result = convertView;

            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            result = convertView;
//        }
//
//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;
//
//        viewHolder.txtName.setText(dataModel.getName());
//        viewHolder.txtType.setText(dataModel.getType());
//        viewHolder.txtVersion.setText(dataModel.getVersion_number());
//        viewHolder.info.setOnClickListener(this);
//        viewHolder.info.setTag(position);
//        // Return the completed view to render on screen
        return convertView;
    }
}
