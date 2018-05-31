package nexus101.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nexus101.R;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.listeners.GroupItemClickListener;
import nexus101.network.models.Attendance;
import nexus101.network.models.GroupInfo;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.CardViewHolder>{
    Context context;
    List<Attendance> attendances;
    LayoutInflater inflater;

    public AttendanceAdapter(Context context, List<Attendance> attendances) {
        this.context = context;
        this.attendances = attendances;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_attendance_view, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(attendances.get(position));
    }

    @Override
    public int getItemCount() {
        return attendances.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView date, present;

        public CardViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            present = itemView.findViewById(R.id.present);
        }

        public void bind(final Attendance attendance) {
            date.setText(attendance.getAttendanceInfo().getDate());
            if (attendance.getAttendanceInfo().getIsPresent().equals(1)){
                present.setText("P");
            }
            else {
                present.setText("A");
            }
        }
    }
}
