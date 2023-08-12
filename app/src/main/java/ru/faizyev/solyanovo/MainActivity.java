package ru.faizyev.solyanovo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student> students;
    private StudentsAdapter studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        students = generateStudentList();
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        setupButton();
    }


    private void setupRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.activity_main__rv_students);
        studentsAdapter = new StudentsAdapter(students);
        recyclerView.setAdapter(studentsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void setupButton(){
        Button addButton = findViewById(R.id.activity_main__button_add);
        addButton.setOnClickListener(v -> onAddClick());
    }


    private void onAddClick() {
        students.add(generateNewMovie());
        studentsAdapter.notifyDataSetChanged();
    }


    private Student generateNewMovie() {
        return new Student("Имя", "Фамилия", Gender.МУЖСКОЙ, R.drawable.avatar_1);
    }


    private List<Student> generateStudentList(){
        List<Student> students = new LinkedList<Student>();
        students.add(new Student("Тимофей", "Лоскутов", Gender.МУЖСКОЙ, R.drawable.avatar_1));
        students.add(new Student("Артем", "Рубанов", Gender.МУЖСКОЙ, R.drawable.avatar_1));
        students.add(new Student("Идеалей", "Менов", Gender.МУЖСКОЙ, R.drawable.avatar_1));
        students.add(new Student("Полина", "Дейнес", Gender.ЖЕНСКИЙ, R.drawable.avatar_1));
        students.add(new Student("Пивной", "Движ", Gender.ЖЕНСКИЙ, R.drawable.avatar_1));
        return students;
    }
}