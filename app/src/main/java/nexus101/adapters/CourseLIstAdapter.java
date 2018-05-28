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
import nexus101.listeners.CourseItemClickListener;
import nexus101.network.models.CoursesInfo;
import nexus101.network.models.Student;

public class CourseLIstAdapter extends RecyclerView.Adapter<CourseLIstAdapter.CardViewHolder> {

    Context context;
    List<CoursesInfo> coursesInfos;
    LayoutInflater inflater;
    CourseItemClickListener courseItemClickListener;

    public CourseLIstAdapter(AdminCourseActivity adminCourseActivity, List<CoursesInfo> coursesInfos, CourseItemClickListener courseItemClickListener) {
        this.context = adminCourseActivity;
        this.courseItemClickListener = courseItemClickListener;
        this.coursesInfos = coursesInfos;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_course, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseLIstAdapter.CardViewHolder holder, int position) {
        holder.bind(coursesInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return coursesInfos.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;

        public CardViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.course_name);
        }

        public void bind(final CoursesInfo coursesInfo) {

            tv_name.setText(coursesInfo.getCourseName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    courseItemClickListener.onItemClick(coursesInfo);
                }
            });

        }
    }
}
