/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mentor;

import Config.config;
import Config.session;
import Main.LandingPage;
import Main.LoginPage;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.BorderLayout;

/**
 *
 * @author Sheena
 */
public class User extends javax.swing.JFrame {

    /**
     * Creates new form User
     */
    session sess = session.getInstance();
    public User() {
        initComponents();
        loadGreeting();
        loadEnergyChart(sess.getUserId());
    }

    private void loadGreeting() {

    session sess = session.getInstance();
    String mentorName = sess.getName() + " " + sess.getLname();

    String clientName = "";
    String sql = "SELECT a.name, a.lname " +
                 "FROM mentor_client mc " +
                 "JOIN tbl_accts a ON mc.client_id = a.a_id " +
                 "WHERE mc.mentor_id = ? AND mc.status = 'Approved'";

    try (Connection conn = config.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, sess.getUserId());
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            clientName = rs.getString("name") + " " + rs.getString("lname");

            lblGreeting.setText(
                "<html>Welcome, <b>" + mentorName + "</b>! <br>" +
                "You are now successfully connected with your client, <b>" +
                clientName + "</b> in Energy Tracker!</html>"
            );
        } else {
            lblGreeting.setText(
                "<html>Welcome, <b>" + mentorName + "</b>!<br>" +
                "You currently have no approved client connections.</html>"
            );
        }
        rs.close();

    } catch (SQLException e) {
        System.out.println("Error loading greeting: " + e.getMessage());
    }
}
    
    public void loadEnergyChart(int mentorId){

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String sql =
    "SELECT e.task, e.energy_level, e.date " +
    "FROM energy_log e " +
    "JOIN mentor_client mc ON e.u_id = mc.client_id " +
    "WHERE mc.mentor_id = ? " +
    "AND mc.status = 'Approved' " +
    "ORDER BY e.date";

    try{

        Connection con = config.connectDB();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, mentorId);

        ResultSet rs = pst.executeQuery();

        while(rs.next()){

            String task = rs.getString("task");
            int energy = rs.getInt("energy_level");
            String date = rs.getString("date");

            dataset.addValue(energy, date, task);
        }rs.close();

        JFreeChart chart = ChartFactory.createBarChart(
                "Client Energy Tracker",
                "Tasks",
                "Energy Level",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);

        chartContainer.removeAll();
        chartContainer.setLayout(new BorderLayout());
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.revalidate();
        chartContainer.repaint();

    }catch(Exception e){
        e.printStackTrace();
    }
}
    
    public void saveInsight(String insight){

    session sess = session.getInstance();
    int mentorId = sess.getUserId();

    int clientId = getClientId();

    String sql = "INSERT INTO mentor_insights (mentor_id, client_id, insight, date_created) VALUES (?, ?, ?, datetime('now'))";

    try{

        Connection con = config.connectDB();
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, mentorId);
        pst.setInt(2, clientId);
        pst.setString(3, insight);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(this,"Insight saved successfully!");

    }catch(Exception e){
        e.printStackTrace();
    }
}
    
    public int getClientId(){

    session sess = session.getInstance();
    int mentorId = sess.getUserId();

    int clientId = 0;

    String sql = "SELECT client_id FROM mentor_client WHERE mentor_id=?";

    try{

        Connection con = config.connectDB();
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, mentorId);

        ResultSet rs = pst.executeQuery();

        if(rs.next()){
            clientId = rs.getInt("client_id");
        }
        rs.close();
    }catch(Exception e){
        e.printStackTrace();
    }

    return clientId;
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Home = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Acc = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Logout = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        chartContainer = new javax.swing.JPanel();
        Feedbackbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInsights = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        App = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Mentors = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Record = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1300, 737));
        setMinimumSize(new java.awt.Dimension(1300, 737));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(1300, 737));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        Logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoMouseClicked(evt);
            }
        });
        jPanel1.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, 30, 290, 110));

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("USER'S CLIENT");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        jLabel1.setFont(new java.awt.Font("Broadway", 2, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EnergiFy");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 180, 30));

        Home.setBackground(new java.awt.Color(0, 51, 51));
        Home.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HomeMouseExited(evt);
            }
        });
        Home.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("HOME");
        Home.add(jLabel2);
        jLabel2.setBounds(139, 16, 79, 29);

        jPanel1.add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 360, 60));

        Acc.setBackground(new java.awt.Color(0, 51, 51));
        Acc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        Acc.setForeground(new java.awt.Color(255, 255, 255));
        Acc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Acc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AccMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AccMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AccMouseExited(evt);
            }
        });
        Acc.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ACCOUNT");
        Acc.add(jLabel7);
        jLabel7.setBounds(120, 20, 125, 20);

        jPanel1.add(Acc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 360, 60));

        Logout.setBackground(new java.awt.Color(0, 51, 51));
        Logout.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogoutMouseExited(evt);
            }
        });
        Logout.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("LOGOUT");
        Logout.add(jLabel8);
        jLabel8.setBounds(126, 16, 107, 29);

        jPanel1.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 680, 360, 60));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGreeting.setFont(new java.awt.Font("Sylfaen", 3, 24)); // NOI18N
        lblGreeting.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lblGreeting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 880, 140));

        chartContainer.setLayout(new java.awt.BorderLayout());
        jPanel2.add(chartContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 830, 320));

        Feedbackbtn.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        Feedbackbtn.setText("Send Feedback");
        Feedbackbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeedbackbtnActionPerformed(evt);
            }
        });
        jPanel2.add(Feedbackbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 730, 180, 40));

        txtInsights.setColumns(20);
        txtInsights.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtInsights.setRows(5);
        txtInsights.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Give Insights:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 0, 18))); // NOI18N
        jScrollPane2.setViewportView(txtInsights);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 520, 170));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 780, 180, 20));

        jScrollPane1.setViewportView(jPanel2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 940, 570));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, -1, -1));

        App.setBackground(new java.awt.Color(0, 51, 51));
        App.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        App.setForeground(new java.awt.Color(255, 255, 255));
        App.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        App.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AppMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AppMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AppMouseExited(evt);
            }
        });
        App.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("APPLICATIONS");
        App.add(jLabel3);
        jLabel3.setBounds(90, 20, 200, 20);

        jPanel1.add(App, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 360, 60));

        Mentors.setBackground(new java.awt.Color(16, 79, 79));
        Mentors.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        Mentors.setForeground(new java.awt.Color(255, 255, 255));
        Mentors.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mentors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MentorsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MentorsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MentorsMouseExited(evt);
            }
        });
        Mentors.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CLIENT");
        Mentors.add(jLabel5);
        jLabel5.setBounds(130, 20, 100, 20);

        jPanel1.add(Mentors, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 360, 60));

        Record.setBackground(new java.awt.Color(0, 51, 51));
        Record.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        Record.setForeground(new java.awt.Color(255, 255, 255));
        Record.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Record.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RecordMouseExited(evt);
            }
        });
        Record.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("RECORDS");
        Record.add(jLabel11);
        jLabel11.setBounds(120, 20, 130, 20);

        jPanel1.add(Record, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 360, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoMouseClicked
        LandingPage Logo = new LandingPage();
        Logo.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogoMouseClicked

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        MentorDashboard Users = new MentorDashboard();
        Users.setVisible(true);
        dispose();
    }//GEN-LAST:event_HomeMouseClicked
     public void setColor(JPanel p){
        p.setBackground(new Color(0,51,51));
    }
    public void resetColor(JPanel p2){
        p2.setBackground(new Color(0,102,102));
    }
    private void HomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseEntered
        resetColor(Home);
    }//GEN-LAST:event_HomeMouseEntered

    private void HomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseExited
        setColor(Home);
    }//GEN-LAST:event_HomeMouseExited

    private void AccMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccMouseEntered
        resetColor(Acc);
    }//GEN-LAST:event_AccMouseEntered

    private void AccMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccMouseExited
        setColor(Acc);
    }//GEN-LAST:event_AccMouseExited

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        int confirm = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to logout?",
        "Logout Confirmation",
        JOptionPane.YES_NO_OPTION
        );

        if(confirm == JOptionPane.YES_OPTION){

            session.getInstance().clearSession();
            JOptionPane.showMessageDialog(null, "Logged out successfully!");
            new LoginPage().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_LogoutMouseClicked

    private void LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseEntered
        resetColor(Logout);
    }//GEN-LAST:event_LogoutMouseEntered

    private void LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseExited
        setColor(Logout);
    }//GEN-LAST:event_LogoutMouseExited

    private void AppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AppMouseClicked
        ApplicationsM App = new ApplicationsM();
        App.setVisible(true);
        dispose();
    }//GEN-LAST:event_AppMouseClicked

    private void AppMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AppMouseEntered
        resetColor(App);
    }//GEN-LAST:event_AppMouseEntered

    private void AppMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AppMouseExited
        setColor(App);
    }//GEN-LAST:event_AppMouseExited

    private void MentorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MentorsMouseClicked
        User Mentors = new User();
        Mentors.setVisible(true);
        dispose();
    }//GEN-LAST:event_MentorsMouseClicked

    private void MentorsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MentorsMouseEntered
        resetColor(Mentors);
    }//GEN-LAST:event_MentorsMouseEntered

    private void MentorsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MentorsMouseExited
        setColor(Mentors);
    }//GEN-LAST:event_MentorsMouseExited

    private void AccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccMouseClicked
        ProfileM Acc = new ProfileM();
        Acc.setVisible(true);
        dispose();
    }//GEN-LAST:event_AccMouseClicked

    private void FeedbackbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeedbackbtnActionPerformed
        String insight = txtInsights.getText().trim();

    int mentorId = sess.getUserId();

    if(insight.isEmpty()){
        JOptionPane.showMessageDialog(null,
            "Please write an insight before sending.",
            "Validation Error",
            JOptionPane.WARNING_MESSAGE);
        txtInsights.requestFocus();
        return;
    }

    try{

        Connection con = config.connectDB();

        String getClient = "SELECT client_id FROM mentor_client WHERE mentor_id=? AND status='Approved'";
        PreparedStatement pst1 = con.prepareStatement(getClient);
        pst1.setInt(1, mentorId);

        ResultSet rs = pst1.executeQuery();

        if(rs.next()){

            int clientId = rs.getInt("client_id");

            String sql = "INSERT INTO mentor_insights (mentor_id, client_id, insight, date_created) VALUES (?, ?, ?, datetime('now'))";

            PreparedStatement pst2 = con.prepareStatement(sql);
            pst2.setInt(1, mentorId);
            pst2.setInt(2, clientId);
            pst2.setString(3, insight);

            pst2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Insight sent successfully!");

            txtInsights.setText("");

        } else {

            JOptionPane.showMessageDialog(null,
                "No client connected to this mentor.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        rs.close();

    }catch(Exception e){
        e.printStackTrace();
    }
    }//GEN-LAST:event_FeedbackbtnActionPerformed

    private void RecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecordMouseClicked
        Records Record = new Records();
        Record.setVisible(true);
        dispose();
    }//GEN-LAST:event_RecordMouseClicked

    private void RecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecordMouseEntered
        resetColor(Record);
    }//GEN-LAST:event_RecordMouseEntered

    private void RecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecordMouseExited
        setColor(Record);
    }//GEN-LAST:event_RecordMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Acc;
    private javax.swing.JPanel App;
    private javax.swing.JButton Feedbackbtn;
    private javax.swing.JPanel Home;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel Mentors;
    private javax.swing.JPanel Record;
    private javax.swing.JPanel chartContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JTextArea txtInsights;
    // End of variables declaration//GEN-END:variables
}
