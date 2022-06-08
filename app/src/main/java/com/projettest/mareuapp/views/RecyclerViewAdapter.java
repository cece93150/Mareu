package com.projettest.mareuapp.views;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.projettest.mareuapp.di.DI;
import com.projettest.mareuapp.model.Meetings;
import com.projettest.mareuapp.service.MeetingApiService;
import com.projettest.mareuapp.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    MeetingApiService mApiService;
    private List<Meetings> mMeetings;


    public RecyclerViewAdapter(List<Meetings> meetings) {
        mMeetings = meetings;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void upDateMeetings(List<Meetings> meetings) {
        mMeetings = meetings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.displayMeetings(mMeetings.get(position));
        holder.mDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mApiService = DI.getMeetingApiService();
                    mApiService.deleteMeeting(mMeetings.get(position));
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatImageView mColor;
        public TextView mMembers;
        public ImageButton mDelete;
        public TextView mName;
        public TextView mRoom;

        public ViewHolder(View view) {
            super(view);
            mColor = view.findViewById(R.id.meeting_color);
            mName = view.findViewById(R.id.meeting_name);
            mRoom = view.findViewById(R.id.meeting_room);
            mMembers = view.findViewById(R.id.meeting_members);
            mDelete = view.findViewById(R.id.meeting_delete);
        }

        //item setText and color
        @SuppressLint("SetTextI18n")
        public void displayMeetings(Meetings meetings) {
            mColor.setImageTintList(ColorStateList.valueOf(meetings.getColor()));
            mName.setText(meetings.getNameOfMeeting() + " - " + meetings.getDate() + " - " + meetings.getHour());
            mRoom.setText(meetings.getRoom());
            mMembers.setText(meetings.getMembers());
        }
    }
}