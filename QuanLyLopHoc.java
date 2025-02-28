import java.util.*;

class Student {
    String firstName, lastName;
    double oop, project, machineLearning, database, mobileDev;
    
    Student(String firstName, String lastName, double oop, double project, double machineLearning, double database, double mobileDev) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.oop = oop;
        this.project = project;
        this.machineLearning = machineLearning;
        this.database = database;
        this.mobileDev = mobileDev;
    }
    
    double getAverage() {
        return (oop + project + machineLearning + database + mobileDev) / 5;
    }
    
    String getRank() {
        double avg = getAverage();
        return avg >= 9 ? "A" : avg >= 8 ? "B" : avg >= 6.5 ? "C" : avg >= 5 ? "D" : "<D";
    }
    
    String getFullName() {
        return lastName + " " + firstName;
    }
}

class ClassRoom {
    String classCode;
    List<Student> students = new ArrayList<>();
    
    ClassRoom(String classCode) {
        this.classCode = classCode;
    }
    
    void addStudent(Student s) {
        students.add(s);
    }
    
    void display() {
        System.out.println("Lớp: " + classCode);
        Map<String, Integer> rankCount = new HashMap<>();
        for (String r : Arrays.asList("A", "B", "C", "D", "<D")) rankCount.put(r, 0);
        
        for (Student s : students) {
            System.out.println(s.getFullName() + " - AVG: " + s.getAverage() + " - Rank: " + s.getRank());
            rankCount.put(s.getRank(), rankCount.get(s.getRank()) + 1);
        }
        System.out.println("\nThong ke xep hang:");
        rankCount.forEach((r, c) -> System.out.println(r + ": " + c + " sinh viên"));
    }
}

public class QuanLyLopHoc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, ClassRoom> classes = new HashMap<>();
        
        ClassRoom c1 = new ClassRoom("CNTT1");
        c1.addStudent(new Student("Hoai", "Nguyen", 8.5, 7.0, 9.0, 8.0, 7.5));
        c1.addStudent(new Student("An", "Tran", 9.5, 9.0, 8.5, 9.0, 9.5));
        classes.put("CNTT1", c1);
        
        ClassRoom c2 = new ClassRoom("CNTT2");
        c2.addStudent(new Student("Minh", "Pham", 6.0, 6.5, 5.5, 7.0, 6.0));
        classes.put("CNTT2", c2);
        
        System.out.println("Nhap ma lop (CNTT1, CNTT2) hoac 'exit': ");
        while (true) {
            String code = sc.nextLine();
            if (code.equalsIgnoreCase("exit")) break;
            if (classes.containsKey(code)) classes.get(code).display();
            else System.out.println("Lop khong ton tai.");
        }
        sc.close();
    }
}
