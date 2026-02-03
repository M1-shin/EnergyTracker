/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

/**
 *
 * @author USER24
 */
public class session {

    private static int a_id;
    private static String name;
    private static String email;
    private static String type;

    public static void setSession(int id, String n, String e, String t) {
        a_id = id;
        name = n;
        email = e;
        type = t;
    }

    public static int getUserId() {
        return a_id;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getType() {
        return type;
    }

    public static void clearSession() {
        a_id = 0;
        name = null;
        email = null;
        type = null;
    }
}

