package projekdatafilm;

import java.awt.HeadlessException;
import java.awt.Image;
import static java.awt.SystemColor.text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class menu extends javax.swing.JFrame {
    String sql;
    Statement s;
    ResultSet r;
    PreparedStatement pst;
    Connection con, tes;
    String[] data;
    DefaultTableModel model;
    DefaultTableModel tabModel;
    ResultSet RsProduk = null;
    private ImageIcon format;
    String text;

    public menu() {
        initComponents();
        this.setLocationRelativeTo(null);
        combobox();
        data = new String[6];
        Connection con = (Connection) MyConnection.getConnection();
        model = (DefaultTableModel)jTable1.getModel();
        tabel();
        text = login.tx;
        loadImage(text);
        showkata(text);
        autonumber();
        txt_id.setEnabled(false);
    }
    
    private void kosongkan_form(){
        txt_nama.setText(null);
        txt_episode.setText(null);
        txt_studio.setText(null);
    }
    
    private void autonumber(){
        try{
            con = MyConnection.getConnection();
            s = con.createStatement();
            String sql = "SELECT * FROM menu ORDER BY id DESC";
            r = s.executeQuery(sql);
            if(r.next()){
                String NoID = r.getString("id").substring(2);
                String ID = "" + (Integer.parseInt(NoID) + 1);
                String Zero = "";
                
                if (ID.length() == 1){
                    Zero = "00";
                }
                else if (ID.length() == 2){
                    Zero = "0";
                }
                else if (ID.length() == 3){
                    Zero = "";
                }
                
                txt_id.setText("AF" + Zero + ID);
            }else{
                txt_id.setText("AF001");
            }
            r.close();
            s.close();
        } catch(Exception e){
            System.out.println("Autonumber error!");
    }
    }
    
    private void showkata(String txt){
        String tUser;
        try{
            sql = "SELECT * FROM register WHERE username=?";
            tes = DriverManager.getConnection("jdbc:mysql://localhost:3306/uas_kbpl_db", "root", "");
            pst = tes.prepareStatement(sql);
            
            pst.setString(1, txt);
            r = pst.executeQuery();
            if(r.next()){
                tUser = r.getString("nama");
                user.setText("Halo, " + tUser+ "");
            }
        }catch(SQLException ex){
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadImage(String text){
        String src;
        try{
            sql = "SELECT * FROM register WHERE username=?";
            tes = DriverManager.getConnection("jdbc:mysql://localhost:3306/uas_kbpl_db", "root", "");
            pst = tes.prepareStatement(sql);
            
            pst.setString(1, text);
            r = pst.executeQuery();
            if(r.next()){
                src = r.getString("foto");
                ImageIcon icon = new ImageIcon(src);
                Image img = icon.getImage().getScaledInstance(pilihlah.getWidth(), pilihlah.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon ic = new ImageIcon (img);
                pilihlah.setIcon(ic);
            }
        }catch(SQLException ex){
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void combobox(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uas_kbpl_db", "root", "");
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM season";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                jc_season.addItem(rs.getString("nama_season"));
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("error"+e.getMessage());
        }
    }
    
    public void tabel() {
        try {
            pst = con.prepareStatement(
                "SELECT menu.id, menu.nama, menu.type, menu.episode, menu.studio, season.nama_season " +
                "FROM menu INNER JOIN season ON menu.id_season = season.id_season"
            );
            r = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(r)); // Pastikan DbUtils sudah tersedia
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void display(){
        try{
            String sql = "SELECT * FROM menu WHERE id ='" + txt_id.getText() + "'";
            Connection conn = (Connection) MyConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        tabel();
        loadImage(text);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_episode = new javax.swing.JTextField();
        jc_type = new javax.swing.JComboBox();
        jc_season = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_studio = new javax.swing.JTextField();
        search = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        pilihlah = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(233, 220, 211));

        jPanel2.setBackground(new java.awt.Color(185, 157, 137));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(37, 34, 30));
        jLabel1.setText("ID Film");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(37, 34, 30));
        jLabel2.setText("Type");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(37, 34, 30));
        jLabel3.setText("Episode");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(37, 34, 30));
        jLabel4.setText("Season");

        jc_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "~Pilih Type~", "TV", "OVA", "OVA", "Movie", "Spesial" }));

        jc_season.setForeground(new java.awt.Color(37, 34, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setForeground(new java.awt.Color(37, 34, 30));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addd.png"))); // NOI18N
        jButton1.setText("add");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(37, 34, 30));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/deleteee_1.png"))); // NOI18N
        jButton2.setText("del");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setForeground(new java.awt.Color(37, 34, 30));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/clearr-removebg-preview (1).png"))); // NOI18N
        jButton3.setText("clr");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setForeground(new java.awt.Color(37, 34, 30));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update.png"))); // NOI18N
        jButton4.setText("upd");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setForeground(new java.awt.Color(37, 34, 30));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit.png"))); // NOI18N
        jButton5.setText("exi");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 34, 30));
        jLabel6.setText("Nama");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(37, 34, 30));
        jLabel7.setText("Studio");

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe Script", 0, 28)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(37, 34, 30));
        jLabel10.setText("Daftar Data-data Film Anime Jepang");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(49, 49, 49)
                        .addComponent(jButton4)
                        .addGap(54, 54, 54)
                        .addComponent(jButton3)
                        .addGap(44, 44, 44)
                        .addComponent(jButton5)
                        .addGap(24, 24, 24))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jc_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_episode)
                            .addComponent(txt_studio, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_season, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jc_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_episode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(txt_studio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jc_season, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel7)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(21, 21, 21))
        );

        jLabel5.setFont(new java.awt.Font("MV Boli", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 34, 30));
        jLabel5.setText("Welcome");

        user.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        user.setForeground(new java.awt.Color(37, 34, 30));
        user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        pilihlah.setBorder(new javax.swing.border.MatteBorder(null));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton6.setForeground(new java.awt.Color(37, 34, 30));
        jButton6.setText("Season");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("MV Boli", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(37, 34, 30));
        jLabel13.setText("Main Menu");

        jLabel9.setFont(new java.awt.Font("MV Boli", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(37, 34, 30));
        jLabel9.setText("To");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/anime-removebg-preview (1).png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Goudy Old Style", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(37, 34, 30));
        jLabel8.setText("Disini Merupakan Program Untuk");

        jLabel12.setFont(new java.awt.Font("Goudy Old Style", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(37, 34, 30));
        jLabel12.setText("Anime Jepang");

        jLabel14.setFont(new java.awt.Font("Goudy Old Style", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(37, 34, 30));
        jLabel14.setText("Menginputkan Data-data Film");

        jLabel15.setFont(new java.awt.Font("Goudy Old Style", 0, 30)); // NOI18N
        jLabel15.setText("Admin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(pilihlah, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jButton6))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 60, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel14))
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15))))
                        .addGap(59, 59, 59)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(36, 36, 36)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pilihlah, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        try{
            int row = jTable1.getSelectedRow();
            String tabel_klik = (jTable1.getModel().getValueAt(row, 0).toString());
            java.sql.Statement stat = con.createStatement();
            java.sql.ResultSet sql = stat.executeQuery("select * from menu where id ='" + tabel_klik+ "'");
            if(sql.next()){
                String id = sql.getString("id");
                txt_id.setText(id);
                String nama = sql.getString("nama");
                txt_nama.setText(nama);
                String type = sql.getString("type");
                jc_type.setSelectedItem(type);
                String episode = sql.getString("episode");
                txt_episode.setText(episode);
                String studio = sql.getString("studio");
                txt_studio.setText(studio);
                String season = sql.getString("id_season");
                jc_season.setSelectedItem(season);
            }
        }catch (SQLException ex){
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String id = txt_id.getText();
        String nama = txt_nama.getText();
        String type = jc_type.getSelectedItem().toString();
        String episode = txt_episode.getText();
        String studio = txt_studio.getText();
        String season = jc_season.getSelectedItem().toString();
        int idseason = 0;

        try{
            s = con.createStatement();
            String sqly = "SELECT * FROM season WHERE nama_season = '" + season +"' ";
            r = s.executeQuery(sqly);
            if(r.next())
            idseason = r.getInt(1);
            s = con.createStatement();
            s.executeUpdate("INSERT INTO menu (id, nama, type, episode, studio, id_season)"
                + "VALUE('"+id+"', '"+nama+"', '"+type+"', '"+episode+"', '"+studio+"', '"+idseason+"')");

            autonumber();
            tabel();
            JOptionPane.showMessageDialog(null, "Data telah Tersimpan");
            kosongkan_form();
            s.close();
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        String idHapus = jTable1.getValueAt(row, 0).toString();
        sql = "delete from menu where id='" +idHapus+"'";
        try{
            s = con.createStatement();
            s.execute(sql);
            tabel();
            JOptionPane.showMessageDialog(null, "Data telah terhapus");
            s.close();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "error"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        kosongkan_form();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        String ID, Nama, Episode, Type, Studio, season, Search;
        int idSeason = 0;

        ID = txt_id.getText();
        Nama = txt_nama.getText();
        Type = jc_type.getSelectedItem().toString();
        Episode = txt_episode.getText();
        Studio = txt_studio.getText();
        Search = search.getText();
        season = jc_season.getSelectedItem().toString();

        try {
            Statement stat = con.createStatement();
            String sqly = "SELECT * FROM season WHERE nama_season = '" + season + "'";
            r = stat.executeQuery(sqly);
            if (r.next()) {
                idSeason = r.getInt(1);
            }

            // Pastikan result set dan statement ditutup sebelum melanjutkan
            r.close();
            stat.close();

            // Perbaikan query di PreparedStatement
            pst = con.prepareStatement(
                "UPDATE menu SET id= ?, nama= ?, type= ?, episode= ?, studio= ?, id_season= ? WHERE id = ?"
            );
            pst.setString(1, ID);
            pst.setString(2, Nama);
            pst.setString(3, Type);
            pst.setString(4, Episode);
            pst.setString(5, Studio);
            pst.setInt(6, idSeason);
            pst.setString(7, Search);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Update Berhasil!");
            tabel(); // Refresh data di tabel
            kosongkan_form(); // Kosongkan form input

            txt_id.setText("");
            txt_nama.setText("");
            jc_type.setSelectedIndex(0);
            txt_episode.setText("");
            txt_studio.setText("");
            jc_season.setSelectedIndex(0);
            txt_studio.requestFocus();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        int answer = JOptionPane.showConfirmDialog(null, "Anda yakin keluar?", "Exit", JOptionPane.OK_OPTION);
        if (answer == JOptionPane.OK_OPTION){
            dispose();
        }
    }//GEN-LAST:event_jButton5MouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        String ID, Nama, type, episode, studio, season;
        int idSeason = 0;

        ID = txt_id.getText();
        Nama = txt_nama.getText();
        type = jc_type.getSelectedItem().toString();
        episode = txt_episode.getText();
        studio = txt_studio.getText();
        season = jc_season.getSelectedItem().toString();

        try{
            java.sql.Statement stat = con.createStatement();
            String sqly = "select * from season where nama_season ='"+season+"'";
            r = stat.executeQuery(sqly);
            if (r.next())
            idSeason = r.getInt(1);

            String id1 = search.getText();
            pst = con.prepareStatement("select id, nama, type, episode, studio, id_season, from menu where id = ?");
            pst.setString(1, id1);

            if(r.next()==true){
                String id = r.getString(1);
                String nama = r.getString(2);
                String Type = r.getString(3);
                String Episode = r.getString(4);
                String Studio = r.getString(5);
                idSeason = r.getInt(6);

                txt_id.setText(id);
                txt_nama.setText(nama);
                jc_type.setSelectedItem(Type);
                txt_episode.setText(Episode);
                txt_studio.setText(Studio);
                jc_season.setSelectedItem(idSeason);
            }
            else{
                txt_id.setText("");
                txt_nama.setText("");
                jc_type.setSelectedItem(0);
                txt_episode.setText("");
                txt_studio.setText("");
                jc_season.setSelectedItem(0);
            }
        }
        catch(Exception ex){

        }
    }//GEN-LAST:event_searchKeyReleased

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        this.dispose();
        season a = new season();
        a.setVisible(true);
    }//GEN-LAST:event_jButton6MouseClicked

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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox jc_season;
    private javax.swing.JComboBox jc_type;
    private javax.swing.JLabel pilihlah;
    private javax.swing.JTextField search;
    private javax.swing.JTextField txt_episode;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_studio;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
