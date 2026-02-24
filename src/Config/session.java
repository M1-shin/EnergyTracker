package Config;

public class session {

    private static session instance;

    private int a_id;
    private String name;
    private String lname;
    private String uname;
    private String email;
    private String type;
    private String status;

    private session() {
    }

    public static synchronized session getInstance() {
        if (instance == null) {
            instance = new session();
        }
        return instance;
    }

    public static boolean isInstanceEmpty() {
        return instance == null;
    }

    public int getUserId() {
        return a_id;
    }

    public void setUserId(int a_id) {
        this.a_id = a_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void clearSession() {
        instance = null;
    }
}