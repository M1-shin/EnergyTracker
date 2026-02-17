/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;


public class singleton {
    private static singleton instance;
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String username;
    private String status;

    private singleton() {
        // Private constructor to prevent instantiation
    }

    public static synchronized singleton getInstance() {
        if (instance == null) {
            instance = new singleton();
        }
        return instance;
    }
    
    public static boolean isInstanceEmpty() {
        return instance == null;
    }

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
      public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

