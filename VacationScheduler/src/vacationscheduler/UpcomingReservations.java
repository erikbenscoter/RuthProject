/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import Connections.RuthDBConnection;
import static com.google.gson.internal.bind.TypeAdapters.URI;
import dataobjs.Reservation;
import dataobjs.Owner;
import dao.OwnersFactory;
import dao.reservationsFactory;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author erikbenscoter
 */
public class UpcomingReservations extends javax.swing.JPanel {

    /**
     * Creates new form UpcomingReservations
     */
    
     Vector <Reservation> response;
     Vector <Owner> owners;
     Vector <Reservation> reservations = new Vector();
     
     int reservationIndex;
     
    public UpcomingReservations() {
        initComponents();
        UpdateDataSet();
        makeTable("Select * From Reservations");
        
        
        ////////////////////////////////////////////////////////////////////////
        //              FIll Up Owner combo box
        ////////////////////////////////////////////////////////////////////////
        Vector <Owner> allOwners = OwnersFactory.getAllOwners();
        Vector <String> userNames = new Vector();
        userNames.add("");
        for (int i = 0; i < allOwners.size(); i++) {
            userNames.add(allOwners.get(i).getUserName());
        }
        
        Combobox_UserNameFilter.setModel(new DefaultComboBoxModel(userNames));
 
        ////////////////////////////////////////////////////////////////////////
        //              FIll Up Upgrade Status combo box
        ////////////////////////////////////////////////////////////////////////
        Vector <String> allUpgradeStates;
        allUpgradeStates = new Vector();
        
        allUpgradeStates.add("");
        for (int i = 0; i<reservations.size(); i++){
            allUpgradeStates.add(reservations.get(i).getUpgradeState());
        }
        
        Set<String> uniqueUpgrades = new HashSet<String>();
            uniqueUpgrades.addAll(allUpgradeStates);
            
        ComboBox_UpgradeStateFilter.setModel(new DefaultComboBoxModel(uniqueUpgrades.toArray()));
        
        
        
        this.setVisible(true);
        jtable_upcomingReservations.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int rowClicked = jtable_upcomingReservations.rowAtPoint(e.getPoint());
                String confirmationNumberClicked = reservations.get(rowClicked).getConfimationNumber();
                System.out.println("confirmation number = " + confirmationNumberClicked);
                new ReservationForm(confirmationNumberClicked);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
   
    public void UpdateDataSet(){
        
        response = new Vector<Reservation>();
        
        reservationIndex = Reservation.scrapedIndicies.CONFIRMATION_NUMBER.getIndex();
        
        owners = OwnersFactory.getAllOwners();
        
        for(int ownerItterator = 0; ownerItterator < owners.size(); ownerItterator ++){
            Owner currentOwner = owners.get(ownerItterator);
            String currentUserName = currentOwner.userName;
            String currentUserPassword = currentOwner.password;
            Vector currentUserReservations = ScrapeWyndham.getUserReservations(currentUserName, currentUserPassword);
            syncDB(currentUserName, currentUserReservations);
        }
        
        ScrapeWyndham.closeWindow();   
    }
    public void updateUsingFilters(){
        String selectedUser = "";
        String withinNumberDays = "";
        String upgradeState = "";
            selectedUser= (String) Combobox_UserNameFilter.getSelectedItem();
            withinNumberDays= TextField_DayFilter.getText();
            upgradeState = (String) ComboBox_UpgradeStateFilter.getSelectedItem();
        String myQuery = "";
        boolean stillEmpty = true;
        
        if(!selectedUser.equals("")){
           myQuery = "Select * From Reservations where Owner_User_Name = '"+selectedUser+"'";
           stillEmpty = false;
        }
        if(!withinNumberDays.equals("")){
            if(stillEmpty == true){
                 myQuery = "select * from reservations where Date_of_reservation < date('now','+"+ withinNumberDays +" days') AND Date_of_reservation > date('now', '0 days')";
                 stillEmpty = false;
            }else{
                myQuery += "AND Date_of_reservation < date('now','+"+ withinNumberDays +" days') AND Date_of_reservation > date('now', '0 days')";
            }
        }
         if(!upgradeState.equals("")){
            if(stillEmpty == true){
                 myQuery = "select * from reservations where Upgrade_status = '" + upgradeState + "'";
                 stillEmpty = false;
            }else{
                myQuery += "AND Upgrade_status = '" + upgradeState + "'";
            }
        }
        
        
        
        if(stillEmpty){
            myQuery = "select * from reservations";
        }
        
        //System.err.println(myQuery);
        makeTable(myQuery);
        
        
    }
    
    public void syncDB(String p_currentUserName, Vector <Reservation> p_userReservations){
        
        for(int currentVectorItterator = 0; currentVectorItterator < p_userReservations.size(); currentVectorItterator ++){
            
            
            String confirmationNumberString = (String) p_userReservations.get(currentVectorItterator).getConfimationNumber();
            boolean alreadyInDB = reservationsFactory.doesReservationExistInDB(confirmationNumberString);
            Reservation currentReservation =p_userReservations.get(currentVectorItterator);

            currentReservation.setOwnerUserName(p_currentUserName);
            reservations.add(currentReservation);
            
            if(!alreadyInDB){
                reservationsFactory.insert(currentReservation);
                
            }else{
                //TODO: update given confimation number
                System.err.println("Already in DB");
                reservationsFactory.UpdateScrapedInfo(currentReservation);
            }
            
        }
    }
    public void makeTable(String p_query){
        reservations = reservationsFactory.getAll(p_query);
        Vector<Vector> tableBody = new Vector();
        Vector<String> tableHeadings = new Vector();
        
        for (int i = 0; i < reservations.size(); i++) {
            tableBody.add(reservations.get(i).toVector());
        }
        
        String stringHeadings[] ={"Confirmation Number","Check-In-Date","#Nights",
                "Resort", "Size", "Booked", "Traveler", "Upgrade"};
        
        tableHeadings = new Vector(Arrays.asList(stringHeadings));
                
        
        jtable_upcomingReservations.setModel(new DefaultTableModel(tableBody, tableHeadings));
        
        
        this.invalidate();
    }
    
    public void jTableToExcel(){
        
         
        try {
            String fileName = "./excelAttempt.xls";
            
            TableModel model = jtable_upcomingReservations.getModel();
            FileWriter file = new FileWriter(fileName);
         
        
            for(int column = 0; column < model.getColumnCount(); column ++){
                file.write( model.getColumnName(column) + "\t");

            }
        
            file.write("\n");

            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    file.write(model.getValueAt(i,j).toString()+"\t");
                }
                file.write("\n");
            }

        file.close();
        
        File file_file = new File(fileName);
        
        Desktop.getDesktop().open(file_file);
        
        
        } catch (IOException ex) {
             Logger.getLogger(UpcomingReservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_upcomingReservations = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Combobox_UserNameFilter = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TextField_DayFilter = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ComboBox_UpgradeStateFilter = new javax.swing.JComboBox();
        jButton_exportToExcel = new javax.swing.JButton();

        jtable_upcomingReservations.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtable_upcomingReservations);

        jScrollPane2.setViewportView(jScrollPane1);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Upcoming Reservations");

        Combobox_UserNameFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Combobox_UserNameFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combobox_UserNameFilterActionPerformed(evt);
            }
        });

        jLabel2.setText("Owner(user name):");

        jLabel3.setText("within the next (days):");

        TextField_DayFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_DayFilterActionPerformed(evt);
            }
        });

        jLabel4.setText("Upgrade State:");

        ComboBox_UpgradeStateFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBox_UpgradeStateFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_UpgradeStateFilterActionPerformed(evt);
            }
        });

        jButton_exportToExcel.setText("Export Table");
        jButton_exportToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_exportToExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(Combobox_UserNameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextField_DayFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBox_UpgradeStateFilter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_exportToExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Combobox_UserNameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_DayFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBox_UpgradeStateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_exportToExcel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Combobox_UserNameFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combobox_UserNameFilterActionPerformed
        updateUsingFilters();
        
    }//GEN-LAST:event_Combobox_UserNameFilterActionPerformed

    private void TextField_DayFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_DayFilterActionPerformed
       updateUsingFilters();
    }//GEN-LAST:event_TextField_DayFilterActionPerformed

    private void ComboBox_UpgradeStateFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_UpgradeStateFilterActionPerformed
        updateUsingFilters();
    }//GEN-LAST:event_ComboBox_UpgradeStateFilterActionPerformed

    private void jButton_exportToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_exportToExcelActionPerformed
        jTableToExcel();
    }//GEN-LAST:event_jButton_exportToExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBox_UpgradeStateFilter;
    private javax.swing.JComboBox Combobox_UserNameFilter;
    private javax.swing.JTextField TextField_DayFilter;
    private javax.swing.JButton jButton_exportToExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtable_upcomingReservations;
    // End of variables declaration//GEN-END:variables
}
