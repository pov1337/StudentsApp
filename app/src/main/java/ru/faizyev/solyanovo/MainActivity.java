package ru.faizyev.solyanovo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student> students;
    private StudentsAdapter studentsAdapter;

    private int selectedPosition = -1;

    private int selectedAvatarResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        students = generateStudentList();
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        setupButton();
        setupSaveButton();
        setupDeleteButton();

    }


    private void setupRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.activity_main__rv_students);
        RadioGroup genderGroup = findViewById(R.id.activity_main_rg_gender);
        studentsAdapter = new StudentsAdapter(students, new StudentsAdapter.Listener() {
            @Override
            public void onStudentClick(Student student) {
                selectedPosition = students.indexOf(student);
                selectedAvatarResourceId = student.getAvatar();
                EditText editName = findViewById(R.id.activity_main__edit_name);
                EditText editSurname = findViewById(R.id.activity_main__edit_surname);
                ImageView avatar = findViewById(R.id.activity_main__image);
                editName.setText(student.getName());
                editSurname.setText(student.getSurname());
                avatar.setImageResource(student.getAvatar());

                if (student.getGender() == Gender.МУЖСКОЙ) {
                    genderGroup.check(R.id.activity_main__male);
                } else if (student.getGender() == Gender.ЖЕНСКИЙ) {
                    genderGroup.check(R.id.activity_main__female);
                }
            }
        });
        recyclerView.setAdapter(studentsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void setupButton(){
        Button addButton = findViewById(R.id.activity_main__button_add);
        addButton.setOnClickListener(v -> onAddClick());
    }
   private void setupSaveButton(){
        Button saveButton = findViewById(R.id.activity_main__button_save);
        saveButton.setOnClickListener(v -> onSaveClick());
    }
   private void setupDeleteButton(){
        Button deleteButton = findViewById(R.id.activity_main__button_delete);
        deleteButton.setOnClickListener(v -> onDeleteClick());
   }



        public void onSaveClick(){
            if(selectedPosition != -1){
                Student student = editStudent(students.get(selectedPosition).getAvatar());

                students.set(selectedPosition, student);
                studentsAdapter.notifyDataSetChanged();
            }
        }

    private void onDeleteClick(){
        if(selectedPosition != -1){
            students.remove(selectedPosition);
            studentsAdapter.notifyDataSetChanged();
        }
    }


    private void onAddClick() {
        students.add(generateNewStudent());
        studentsAdapter.notifyDataSetChanged();
    }


    private Student generateNewStudent() {
        return new Student("Имя", "Фамилия", Gender.МУЖСКОЙ, R.drawable.avatar_1);
    }


    private Student editStudent(int selectedAvatarResourceId){
        EditText editName = findViewById(R.id.activity_main__edit_name);
        EditText editSurname = findViewById(R.id.activity_main__edit_surname);
        ImageView imageAvatar = findViewById(R.id.activity_main__image);
        Drawable drawableAvatar = imageAvatar.getDrawable();



        String name = editName.getText().toString();
        String surname = editSurname.getText().toString();

        if(name.isEmpty() || surname.isEmpty()){
            if(name.isEmpty()){
                editName.setError("Введите имя");
                return null;
            }
            if (surname.isEmpty()){
                editSurname.setError("Введите фамилию");
                return null;
            }
        }

        RadioGroup genderGroup = findViewById(R.id.activity_main_rg_gender);
        Gender gender = null;
        int selectedRadioButtonId = genderGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);


        if (selectedRadioButton != null) {
            String genderText = selectedRadioButton.getText().toString();
            if (genderText.equals("МУЖ")) {
                gender = Gender.МУЖСКОЙ;
            } else if (genderText.equals("ЖЕН")) {
                gender = Gender.ЖЕНСКИЙ;
            }
        }



            return new Student(name, surname, gender, selectedAvatarResourceId);
    }



    private List<Student> generateStudentList(){
        List<Student> students = new LinkedList<Student>();
        students.add(new Student("Тимофей", "Лоскутов", Gender.МУЖСКОЙ, R.drawable.avatar_01));
        students.add(new Student("Артем", "Рубанов", Gender.МУЖСКОЙ, R.drawable.avatar_2));
        students.add(new Student("Идеалей", "Менов", Gender.МУЖСКОЙ, R.drawable.avatar_3));
        students.add(new Student("Полина", "Дейнес", Gender.ЖЕНСКИЙ, R.drawable.avatar_5));
        students.add(new Student("Пивной", "Движ", Gender.ЖЕНСКИЙ, R.drawable.avatar_4));
        return students;
    }
}