package nexus101.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nexus101.R;
import nexus101.admin.AdminStudentAccountActivity;
import nexus101.network.models.Student;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.CardViewHolder> {
    Context context;
    List<Student> students;
    LayoutInflater inflater;

    public StudentListAdapter(AdminStudentAccountActivity adminStudentAccountActivity, List<Student> students) {
        this.context = adminStudentAccountActivity;
        this.students = students;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_student, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_roll;

        public CardViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.name);
            tv_roll = (TextView) itemView.findViewById(R.id.roll);
        }

        public void bind(final Student student) {

            tv_name.setText(student.getUserInfo().getName());
            tv_roll.setText(student.getStudentInfo().getRollNumber());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pore korbo
                }
            });

        }
    }
}
