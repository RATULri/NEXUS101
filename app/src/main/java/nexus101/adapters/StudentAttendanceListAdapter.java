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
import nexus101.admin.course.AdminCourseActivity;
import nexus101.listeners.StudentAttendanceItemClickListener;
import nexus101.network.models.StudentAttendancesInfo;

public class StudentAttendanceListAdapter extends RecyclerView.Adapter<StudentAttendanceListAdapter.CardViewHolder> {

    Context context;
    List<StudentAttendancesInfo> studentAttendancesInfos;
    LayoutInflater inflater;
    StudentAttendanceItemClickListener studentAttendanceItemClickListener;
    public StudentAttendanceListAdapter(AdminCourseActivity adminCourseActivity, List<StudentAttendancesInfo> studentAttendancesInfos, StudentAttendanceItemClickListener studentAttendanceItemClickListener) {
        this.context = adminCourseActivity;
        this.studentAttendanceItemClickListener = studentAttendanceItemClickListener;
        this.studentAttendancesInfos = studentAttendancesInfos;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_course_and_group, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAttendanceListAdapter.CardViewHolder holder, int position) {
        holder.bind(studentAttendancesInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return studentAttendancesInfos.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;

        public CardViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.course_or_group_name);
        }

        public void bind(final StudentAttendancesInfo studentAttendancesInfos) {

            tv_name.setText(studentAttendancesInfos.getCourseName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studentAttendanceItemClickListener.onItemClick(studentAttendancesInfos);
                }
            });

        }
    }
}
