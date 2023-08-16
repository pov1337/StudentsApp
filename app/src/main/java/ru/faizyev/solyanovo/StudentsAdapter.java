package ru.faizyev.solyanovo;


    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.List;

    public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

        private List<Student> students;

        private Listener onStudentClickListener;

        private int selectedAvatarResourceId = -1;

        public StudentsAdapter(List<Student> students, Listener onStudentClickListener) {
            this.students = students;
            this.onStudentClickListener = onStudentClickListener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
            view.setOnClickListener(v -> onStudentClickListener.onStudentClick( (Student) v.getTag()));
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = students.get(position);
            holder.bind(student);
            holder.itemView.setTag(student);
        }


        @Override
        public int getItemCount() {
            return students.size();
        }

        static final  class StudentViewHolder extends RecyclerView.ViewHolder {
            private ImageView avatarView;
            private TextView nameView;
            private TextView surnameView;


            public StudentViewHolder(@NonNull View itemView) {
                super(itemView);
                avatarView = itemView.findViewById(R.id.student_item__avatar_student);
                nameView = itemView.findViewById(R.id.student_item__name_student);
                surnameView = itemView.findViewById(R.id.student_item__surname_student);
            }
            public void bind(Student student){
                avatarView.setImageResource(student.getAvatar());
                nameView.setText(student.getName());
                surnameView.setText(student.getSurname());

            }
        }
        interface Listener {

            void onStudentClick(Student student);

        }
    }
