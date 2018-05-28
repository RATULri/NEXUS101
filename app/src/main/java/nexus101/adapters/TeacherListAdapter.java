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
import nexus101.admin.AdminTeacherAccountActivity;
import nexus101.listeners.TeacherItemClickListener;
import nexus101.network.models.Student;
import nexus101.network.models.Teacher;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.CardViewHolder> {

    Context context;
    List <Teacher> teachers;
    LayoutInflater inflater;
    TeacherItemClickListener teacherItemClickLIstener;

    public TeacherListAdapter(AdminTeacherAccountActivity adminTeacherAccountActivity, List<Teacher> teachers, TeacherItemClickListener teacherItemClickLIstener) {
        this.context = adminTeacherAccountActivity;
        this.teachers = teachers;
        this.inflater = LayoutInflater.from(context);
        this.teacherItemClickLIstener = teacherItemClickLIstener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_teacher, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(teachers.get(position));
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_designation;

        public CardViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.name);
            tv_designation = (TextView) itemView.findViewById(R.id.designation);
        }

        public void bind(final Teacher teacher) {

            tv_name.setText(teacher.getUserInfo().getName());
            tv_designation.setText(teacher.getTeacherInfo().getDesignation());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pore korbo
                    teacherItemClickLIstener.onItemClick(teacher);
                }
            });

        }
    }
}
