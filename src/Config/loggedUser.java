
package Config;

public class loggedUser {
    
    private static int adminUser;

    public static int getAdminUser() {
        return adminUser;
    }
    
    public static void setAdminUser(int adminUser) {
        loggedUser.adminUser = adminUser;
    }
    
}
