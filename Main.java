package uni;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- بدء اختبار نواة نظام UniAccounts ---");

        // 1. اختبار نمط Builder لإنشاء طالب جديد
        StudentAccount student1 = new StudentAccount.Builder("20260001", "Salima Ben", "salima@univ.dz")
                .setLevel("L2")
                .setMajor("Computer Science")
                .setScholarship(true)
                .build();

        // 2. اختبار نمط Singleton للمستودع وسجل التدقيق وإضافة الطالب
        StudentRepository repo1 = StudentRepository.getInstance();
        repo1.addStudent(student1);

        // التأكد من أن الـ Singleton يعيد نفس النسخة
        StudentRepository repo2 = StudentRepository.getInstance();
        System.out.println("هل المستودع الأول نفس الثاني؟ " + (repo1 == repo2));

        // 3. اختبار نمط Prototype لنسخ قالب جاهز لطالب آخر في نفس الدفعة
        StudentAccount templateStudent = new StudentAccount.Builder("TEMPLATE", "Template", "template@univ.dz")
                .setLevel("L2")
                .setMajor("Computer Science")
                .build();

        StudentAccount student2 = templateStudent.cloneAccount("20260002", "Amine Ali", "amine@univ.dz");
        repo1.addStudent(student2);

        // طباعة الحسابات المسجلة في المستودع
        System.out.println("\nقائمة الحسابات في المستودع:");
        for (StudentAccount s : repo1.getAllStudents()) {
            System.out.println(s);
        }
    }
}