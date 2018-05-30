package nexus101.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nexus101.R;
import nexus101.network.models.Student;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.CardViewHolder> {
    Context context;
    List<Student> students;
    LayoutInflater inflater;
    public List<Student> checkedStudents;
    public List<Student> uncheckedStudents;

    public StudentAttendanceAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
        this.inflater = LayoutInflater.from(context);
        checkedStudents = new ArrayList<>();
        uncheckedStudents = new ArrayList<>();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_attendance_taking, parent, false);
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
        CheckBox checkBox;

        public CardViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.name);
            tv_roll = (TextView) itemView.findViewById(R.id.roll);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);

        }

        public void bind(final Student student) {

            tv_name.setText(student.getUserInfo().getName());
            tv_roll.setText(student.getStudentInfo().getRollNumber());

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()){
                        if (uncheckedStudents.contains(student)){
                            uncheckedStudents.remove(student);
                        }
                        checkedStudents.add(student);
                    }
                    if (!checkBox.isChecked()){
                        if (checkedStudents.contains(student)){
                            checkedStudents.remove(student);
                        }
                        uncheckedStudents.add(student);
                    }
                }
            });

        }
    }
}
