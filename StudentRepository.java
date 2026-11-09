package uni;

import java.util.ArrayList;
import java.util.List;

/**
 * النمط: Singleton
 * الدور: ضمان وجود نسخة واحدة فقط من المستودع في الذاكرة لتجنب ضياع الحسابات.
 */
public class StudentRepository {
    private static StudentRepository instance;
    private List<StudentAccount> accounts;

    // Constructor خاص لمنع إنشاء كائنات جديدة بـ new خارج الصنف
    private StudentRepository() {
        accounts = new ArrayList<>();
    }

    // نقطة الوصول الوحيدة للنسخة
    public static synchronized StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public void addStudent(StudentAccount student) {
        accounts.add(student);
        AuditLog.getInstance().log("Added student with ID: " + student.getId());
    }

    public List<StudentAccount> getAllStudents() {
        return accounts;
    }
}