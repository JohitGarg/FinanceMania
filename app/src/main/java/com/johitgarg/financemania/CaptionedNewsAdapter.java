package com.johitgarg.financemania;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.johitgarg.financemania.model.News;
import com.johitgarg.financemania.view.WebViewActivity;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


class CaptionedNewsAdapter extends RecyclerView.Adapter<CaptionedNewsAdapter.ViewHolder> {
    private Context mContext;
    private List<News> mNewsList;

    public CaptionedNewsAdapter(Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public CaptionedNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_view, parent, false);
        return new ViewHolder((CardView) v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final News news = mNewsList.get(position);
        final CardView cardView = holder.cardView;

        TextView titleText, descriptionText, sourceUrlText, dateAndtimeText;
        ImageView newsImage;

        titleText = cardView.findViewById(R.id.news_title);
        descriptionText = cardView.findViewById(R.id.news_description);
        sourceUrlText = cardView.findViewById(R.id.news_url_source);
        dateAndtimeText = cardView.findViewById(R.id.news_time);
        newsImage = cardView.findViewById(R.id.news_image);

        titleText.setText(news.getTitle());
        descriptionText.setText(news.getDescription());
        sourceUrlText.setText(news.getSourceUrl());

        String givenDateString = news.getDateAndTime();
        long timeInMilli = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilli = mDate.getTime();
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        dateAndtimeText.setText(DateUtils.getRelativeTimeSpanString(timeInMilli, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS));

        String imageURL = news.getImageUrl();

        Picasso.get().load(imageURL).fit().into(newsImage);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("NEWS","It works");
                Log.i("NEWS",news.getCompleteUrl());

                Intent intent = new Intent(cardView.getContext(), WebViewActivity.class);
                intent.putExtra("url",news.getCompleteUrl());
                cardView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;

        }
    }

}
