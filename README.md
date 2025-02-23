# UDDD
1
  
    public class Student {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String address;
    private String className;
    private Map<String, Double> scores; // Map tên môn học và điểm số

    // Constructor
    public Student(String firstName, String lastName, String birthdate, String address, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
        this.className = className;
        this.scores = new HashMap<>();
    }

    // Getters and setters for scores
    public void setScore(String subject, Double score) {
        scores.put(subject, score);
    }

    public Double getScore(String subject) {
        return scores.getOrDefault(subject, 0.0); // Mặc định là 0 nếu không có điểm
    }

    // Method to calculate rank based on scores
    public String calculateRank() {
        // Implement your logic to calculate rank based on scores
        // Return "A", "B", "C", "D", "<D" or any ranking system you prefer
        return "A"; // Example logic
    }
}

// Class Classroom
public class Classroom {
    private String classCode;
    private List<Student> students;

    // Constructor
    public Classroom(String classCode) {
        this.classCode = classCode;
        this.students = new ArrayList<>();
    }

    // Method to add student to class
    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to get students by rank
    public Map<String, Integer> getStudentCountByRank() {
        Map<String, Integer> rankCount = new HashMap<>();
        // Initialize counts
        rankCount.put("A", 0);
        rankCount.put("B", 0);
        rankCount.put("C", 0);
        rankCount.put("D", 0);
        rankCount.put("<D", 0);

        // Count students by rank
        for (Student student : students) {
            String rank = student.calculateRank();
            rankCount.put(rank, rankCount.get(rank) + 1);
        }

        return rankCount;
    }

    // Method to get student list
    public List<Student> getStudents() {
        return students;
    }
    }
2
        
    public class Student {
    private String firstName;
    private String lastName;
    private String className;
    private double oop, pm, ml, db, mobile;

    public Student(String firstName, String lastName, String className, double oop, double pm, double ml, double db, double mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.oop = oop;
        this.pm = pm;
        this.ml = ml;
        this.db = db;
        this.mobile = mobile;
    }

    public double getAverageScore() {
        return (oop + pm + ml + db + mobile) / 5.0;
    }

    public String getRank() {
        double avg = getAverageScore();
        if (avg >= 8.5) return "A";
        else if (avg >= 7.0) return "B";
        else if (avg >= 5.5) return "C";
        else if (avg >= 4.0) return "D";
        else return "<D";
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
    }

    // Model class for Classroom
    import java.util.ArrayList;
    import java.util.List;

    public class Classroom {
    private String classCode;
    private List<Student> students;

    public Classroom(String classCode) {
        this.classCode = classCode;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getClassCode() {
        return classCode;
    }
    }
3

        public class MainActivity extends AppCompatActivity {
        private List<Classroom> classrooms;

       @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize classrooms and students (example data)
        classrooms = new ArrayList<>();
        Classroom class1 = new Classroom("Class A");
        Classroom class2 = new Classroom("Class B");

        // Add example students
        class1.addStudent(new Student("John", "Doe", "2000-01-01", "123 Main St", "Class A"));
        class1.addStudent(new Student("Jane", "Smith", "2000-02-02", "456 Elm St", "Class A"));
        class2.addStudent(new Student("Michael", "Brown", "1999-03-03", "789 Oak St", "Class B"));

        // Add classrooms to list
        classrooms.add(class1);
        classrooms.add(class2);

        // Display classrooms using RecyclerView or ListView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ClassAdapter adapter = new ClassAdapter(classrooms, this);
        recyclerView.setAdapter(adapter);
    }

    // Adapter for RecyclerView
    public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
        private List<Classroom> classrooms;
        private Context context;

        public ClassAdapter(List<Classroom> classrooms, Context context) {
            this.classrooms = classrooms;
            this.context = context;
        }

        @NonNull
        @Override
        public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.class_item, parent, false);
            return new ClassViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
            Classroom classroom = classrooms.get(position);
            holder.className.setText(classroom.getClassCode());
            holder.itemView.setOnClickListener(v -> {
                // Handle item click to show detail (students and rank summary)
                Intent intent = new Intent(context, ClassDetailActivity.class);
                intent.putExtra("classroom", classroom);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return classrooms.size();
        }

        public class ClassViewHolder extends RecyclerView.ViewHolder {
            TextView className;

            public ClassViewHolder(@NonNull View itemView) {
                super(itemView);
                className = itemView.findViewById(R.id.className);
            }
        }
    }
    }
4

    public class ClassDetailActivity extends AppCompatActivity {
    private Classroom classroom;
    private TextView classSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_detail);

        // Get classroom from intent
        classroom = getIntent().getParcelableExtra("classroom");
        if (classroom == null) {
            finish(); // Handle error if classroom is null
        }

        // Display class summary
        classSummary = findViewById(R.id.classSummary);
        Map<String, Integer> rankCount = classroom.getStudentCountByRank();
        String summaryText = "Class Summary:\n";
        summaryText += "A: " + rankCount.get("A") + "\n";
        summaryText += "B: " + rankCount.get("B") + "\n";
        summaryText += "C: " + rankCount.get("C") + "\n";
        summaryText += "D: " + rankCount.get("D") + "\n";
        summaryText += "<D: " + rankCount.get("<D") + "\n";
        classSummary.setText(summaryText);

        // Display students using RecyclerView or ListView
        RecyclerView recyclerView = findViewById(R.id.studentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter adapter = new StudentAdapter(classroom.getStudents(), this);
        recyclerView.setAdapter(adapter);
    }

    // Adapter for RecyclerView
    public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
        private List<Student> students;
        private Context context;

        public StudentAdapter(List<Student> students, Context context) {
            this.students = students;
            this.context = context;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = students.get(position);
            holder.studentName.setText(student.getFirstName() + " " + student.getLastName());
            holder.studentClass.setText(student.getClassName());
        }

        @Override
        public int getItemCount() {
            return students.size();
        }

        public class StudentViewHolder extends RecyclerView.ViewHolder {
            TextView studentName;
            TextView studentClass;

            public StudentViewHolder(@NonNull View itemView) {
                super(itemView);
                studentName = itemView.findViewById(R.id.studentName);
                studentClass = itemView.findViewById(R.id.studentClass);
            }
        }
    }
}


