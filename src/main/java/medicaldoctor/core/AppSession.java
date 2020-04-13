package medicaldoctor.core;

import java.time.LocalDateTime;
import medicaldoctor.controllers.ControllerManager;
import medicaldoctor.entities.LogRecord;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.PatientVisit;
import medicaldoctor.entities.User;
import medicaldoctor.util.EmailGenerator;
import medicaldoctor.util.EmailNameGenerator;
import medicaldoctor.util.Encryption;
import medicaldoctor.util.FirstLetterLastNameUserNameGenerator;
import medicaldoctor.util.PasswordGenerator;
import medicaldoctor.util.RandomPasswordGenerator;
import medicaldoctor.util.UserNameGenerator;

/**
 * Static global state of the program session.
 */
public final class AppSession {

    public static PasswordGenerator TEMPORARY_PASSWORD_GENERATOR
            = new RandomPasswordGenerator(18);
    public static UserNameGenerator USER_NAME_GENERATOR
            = new FirstLetterLastNameUserNameGenerator();
    public static Encryption ENCRYPTION = new Encryption();
    public static ControllerManager CONTROLLER_MANAGER = new ControllerManager();
    public static EmailGenerator EMAIL_GENERATOR
            = new EmailNameGenerator();
    
    private static User activeUser;
    private static Patient currentPatient;
    private static PatientVisit currentVisit;

    public static User getActiveUser() {
        if (activeUser == null) {
            throw new IllegalStateException("User is not logged on");
        }
        return activeUser;
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static boolean isLoggedIn() {
        return activeUser != null;
    }

    public static Patient getCurrentPatient() {
        return currentPatient;
    }

    public static void setCurrentPatient(Patient currentPatient) {
        AppSession.currentPatient = currentPatient;
    }

    public static PatientVisit getCurrentVisit() {
        return currentVisit;
    }

    public static void setCurrentVisit(PatientVisit currentVisit) {
        AppSession.currentVisit = currentVisit;
    }

    /**
     * Log a new event to the database, assumes we are in a DatabaseScope.
     *
     * @param description
     */
    public static void logEvent(String description) {
        logEvent0(description);
    }

    /**
     * Log a new event to the database, assumes we are NOT in a DatabaseScope,
     * and thus will briefly create a new session.
     *
     * @param description
     * @throws Exception
     */
    public static void logEventInNewScope(String description) throws Exception {
        try (DatabaseScope scope = new DatabaseScope()) {
            logEvent0(description);
        }
    }

    private static void logEvent0(String description) {
        LogRecord record = new LogRecord();
        record.setDateTime(LocalDateTime.now());
        record.setUser(getActiveUser());
        record.setDescription(description);
        record.save();
    }

}
