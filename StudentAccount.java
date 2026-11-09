package uni;

import java.util.ArrayList;
import java.util.List;

/**
 * النمط: Builder
 * الدور: بناء كائن StudentAccount خطوة بخطوة وتجاوز مشكلة كثرة الوسائط في الـ Constructor.
 */
public class StudentAccount {
    // الحقول الإجبارية والاختيارية للطالب
    private String id;          // إجباري
    private String fullName;    // إجباري
    private String email;       // إجباري
    private String phone;       // اختياري
    private String level;       // اختياري (الافتراضي L1)
    private String major;       // اختياري
    private boolean scholarship;// اختياري (الافتراضي false)
    private List<String> courses;// اختياري

    // Constructor خاص يستقبل الـ Builder
    private StudentAccount(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;
        this.courses = builder.courses;
    }

    // Prototype Pattern: دالة النسخ لإنشاء حساب جديد بناءً على قالب جاهز
    public StudentAccount cloneAccount(String newId, String newFullName, String newEmail) {
        return new Builder(newId, newFullName, newEmail)
                .setPhone(this.phone)
                .setLevel(this.level)
                .setMajor(this.major)
                .setScholarship(this.scholarship)
                .setCourses(new ArrayList<>(this.courses))
                .build();
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + fullName + ", Email=" + email + 
               ", Level=" + level + ", Major=" + major + ", Scholarship=" + scholarship + "]";
    }

    // صنف الـ Builder الداخلي
    public static class Builder {
        private String id;
        private String fullName;
        private String email;
        
        // القيم الافتيارية للحقول الاختيارية
        private String phone = null;
        private String level = "L1";
        private String major = "Computer Science";
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        // الحقول الإجبارية تيم تمريرها في البداية
        public Builder(String id, String fullName, String email) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setMajor(String major) {
            this.major = major;
            return this;
        }

        public Builder setScholarship(boolean scholarship) {
            this.scholarship = scholarship;
            return this;
        }

        public Builder setCourses(List<String> courses) {
            this.courses = courses;
            return this;
        }

        public StudentAccount build() {
            // التحقق من الشروط الإجبارية البسيطة
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("ID cannot be empty");
            }
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Email must contain '@'");
            }
            return new StudentAccount(this);
        }
    }
}