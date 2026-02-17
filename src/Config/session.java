package Config;

public class session {

    private static int a_id;
    private static String name;
    private static String lname;
    private static String uname;
    private static String email;
    private static String type;
    private static String status;

    public static void setSession(int id, String n, String ln, String un, String e, String t, String s) {
        a_id = id;
        name = n;
        lname = ln;
        uname = un;
        email = e;
        type = t;
        status = s;
    }

    public static int getUserId() {
        return a_id;
    }

    public static String getName() {
        return name;
    }

    public static String getLname() {
        return lname;
    }

    public static String getUname() {
        return uname;
    }

    public static String getEmail() {
        return email;
    }

    public static String getType() {
        return type;
    }

    public static String getStatus() {
        return status;
    }

    public static void clearSession() {
        a_id = 0;
        name = null;
        lname = null;
        uname = null;
        email = null;
        type = null;
        status = null;
    }
}