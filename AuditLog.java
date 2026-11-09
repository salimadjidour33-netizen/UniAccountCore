package uni;

import java.time.LocalDateTime;

/**
 * النمط: Singleton
 * الدور: ضمان وجود سجل تدقيق مركزي واحد لتسجيل كل العمليات والأسداث في النظام.
 */
public class AuditLog {
    private static AuditLog instance;

    private AuditLog() {}

    public static synchronized AuditLog getInstance() {
        if (instance == null) {
            instance = new AuditLog();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[AUDIT LOG " + LocalDateTime.now() + "]: " + message);
    }
}