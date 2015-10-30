/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import dataobjs.Owner;
import dao.OwnersFactory;
import dao.GuestFactory;
import dao.reservationsFactory;
import dataobjs.Guest;
import dataobjs.Reservation;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author erikbenscoter
 */

public class ReservationForm extends javax.swing.JPanel {
    
NumberFormat percentFormat = NumberFormat.getPercentInstance();
    /**
     * Creates new form ReservationForm
     */
    JFrame jf;
    Vector availablePts = new Vector();
    Vector ownerEmails = new Vector();
    Vector guestEmails = new Vector();
    Vector yearsAvailable = new Vector();
    int ownerIndexPicked, guestIndexPicked;
    
    
    Vector ownersVector = new Vector();
    Vector guests = new Vector();
        
    public ReservationForm(JFrame jf) {
        this.jf = jf;
        CommonConstructor();
    }
    public ReservationForm() {
        CommonConstructor();
    }
    public ReservationForm(String confirmationNumber){
        
        jf = new JFrame();
        jf.setSize(600, 700);
        CommonConstructor();
        
        //get Reservation from Database
        Reservation reservationToModify = reservationsFactory.get(confirmationNumber);
        
        TextBox_ConfirmationNumber.setText(reservationToModify.getConfimationNumber());
        TextBox_NumberOfNights.setText(Integer.toString(reservationToModify.getNumberOfNights()));     
        TextBox_UnitSize.setText(reservationToModify.getUnitSize());
        Textbox_Location.setText(reservationToModify.getLocation());
        Checkbox_upgraded.setSelected(reservationToModify.isWasUpgraded()==1);
        
        String dateOfReservation = reservationToModify.getDateOfReservation();
        Combobox_Year.setSelectedItem( dateOfReservation.split("-")[0] );
        Combobox_Month.setSelectedItem( dateOfReservation.split("-")[1] );
        Combobox_Day.setSelectedItem( dateOfReservation.split("-")[2].split(" ")[0] );
        ComboBox_Owner.setSelectedItem(reservationToModify.getOwnerUserName());
        
        
       
        
        
        
        //set editable to false
        Combobox_Day.setEnabled(false);
        Combobox_Year.setEnabled(false);
        Combobox_Month.setEnabled(false);
        TextBox_ConfirmationNumber.setEditable(false);
        TextBox_NumberOfNights.setEditable(false);
        TextBox_UnitSize.setEditable(false);
        Textbox_Location.setEditable(false);
        Button_Location.setEnabled(false);
        Button_UnitSize.setEnabled(false);
        Checkbox_upgraded.setEnabled(false);
        
        
        jf.add(this);
        jf.invalidate();
        jf.setVisible(true);
        jf.setAlwaysOnTop(true);
        jf.setLocation(700, 100);
    }
    public void CommonConstructor(){
        initComponents();
        myInitComponents();
        
        ownerIndexPicked = guestIndexPicked = 0;
        
        //set up owners info for combobox
        ownersVector = OwnersFactory.getAllOwners();
        Vector ownerNames = new Vector();
            ownerNames.add(0,"");
        for(int itterator = 0; itterator < ownersVector.size(); itterator++){
            ownerNames.add(((Owner) ownersVector.get(itterator)).getUserName());  
        }
        
        //set up guests info for combobox
        guests = GuestFactory.getAllGuests();
        Vector guestNames = new Vector();
            guestNames.add(0,"");
        for(int itterator = 0; itterator < guests.size(); itterator++){
            guestNames.add(((Guest) guests.get(itterator)).getFirstName() + " "+ ((Guest)guests.get(itterator)).getLastName());
        }
        
        
        Textbox_Location.setEditable(false);
        
        this.ComboBox_Owner.setModel(new DefaultComboBoxModel(ownerNames));
        this.ComboBox_GuestName.setModel(new DefaultComboBoxModel(guestNames));
        
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        
        //set up content that will fill year combobox
        for(int itterator = 0; itterator <= 10; itterator ++ ){
            yearsAvailable.add(year+itterator);
        }
        
        //set up the year combo box to include current year with 5 years going forward
        this.Combobox_Year.setModel(new DefaultComboBoxModel(yearsAvailable));
        
        //handle the autoupdating of available points
        this.TextBox_PointsRequired.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                recalculatePointsAfter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                recalculatePointsAfter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                recalculatePointsAfter();
            }
            
        });
        
        
        
        //ParseConfirmationForm pcf = new ParseConfirmationForm(this);
        //pcf.setAlwaysOnTop(true);
        
    }
    public void myInitComponents(){
        
    }
    public void grabReservationForm(){
        

        
    }
    public void setLocation(String p_location){
        Textbox_Location.setText(p_location);
    }
    
    public void recalculatePointsAfter(){

        if(!TextBox_PointsRequired.getText().equals("")){
          
            int pointsBeforeTransaction = Integer.parseInt(Label_availablePts.getText());
            int pointsNeeded = Integer.parseInt(TextBox_PointsRequired.getText());
            int pointsAfterTransaction = pointsBeforeTransaction - pointsNeeded;

            this.Label_CalculatedPointsAfter.setText(Integer.toString(pointsAfterTransaction));
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboBox_Owner = new javax.swing.JComboBox();
        Label_User = new javax.swing.JLabel();
        Label_PointsAvailableText = new javax.swing.JLabel();
        Label_availablePts = new javax.swing.JLabel();
        Label_ReservationDate = new javax.swing.JLabel();
        Label_NumberOfNights = new javax.swing.JLabel();
        TextBox_NumberOfNights = new javax.swing.JTextField();
        Label_UnitSize = new javax.swing.JLabel();
        Label_ConfirmationNumber = new javax.swing.JLabel();
        TextBox_ConfirmationNumber = new javax.swing.JTextField();
        Label_PointsRequired = new javax.swing.JLabel();
        TextBox_PointsRequired = new javax.swing.JTextField();
        Label_PointsAfter = new javax.swing.JLabel();
        Label_CalculatedPointsAfter = new javax.swing.JLabel();
        Label_Discounted = new javax.swing.JLabel();
        Checkbox_Discounted = new javax.swing.JCheckBox();
        Label_Upgraded = new javax.swing.JLabel();
        Checkbox_upgraded = new javax.swing.JCheckBox();
        Label_GuestOrHolding = new javax.swing.JLabel();
        Label_GuestName = new javax.swing.JLabel();
        Label_AmountPaid = new javax.swing.JLabel();
        Label_DatePaid = new javax.swing.JLabel();
        Label_PaymentMethod = new javax.swing.JLabel();
        TextBox_OtherPaymentMethod = new javax.swing.JTextField();
        Btn_Okay = new javax.swing.JButton();
        Btn_Cancel = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        ComboBox_GuestName = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        TextBox_UnitSize = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Label_Email = new javax.swing.JLabel();
        Combobox_Month = new javax.swing.JComboBox();
        Combobox_Day = new javax.swing.JComboBox();
        Combobox_Year = new javax.swing.JComboBox();
        Button_Location = new javax.swing.JButton();
        Textbox_Location = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Button_UnitSize = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Label_confirmGuestEmail = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        Button_ViewOwner = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabel3.setText("jLabel3");

        setBackground(new java.awt.Color(254, 254, 254));

        ComboBox_Owner.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBox_Owner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_OwnerActionPerformed(evt);
            }
        });

        Label_User.setText("Owner");

        Label_PointsAvailableText.setText("Available Points:");

        Label_availablePts.setText("0");

        Label_ReservationDate.setText("Date of Reservation:");

        Label_NumberOfNights.setText("Number of Nights:");

        TextBox_NumberOfNights.setText("0");
        TextBox_NumberOfNights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBox_NumberOfNightsActionPerformed(evt);
            }
        });

        Label_UnitSize.setText("Unit Size:");

        Label_ConfirmationNumber.setText("Confirmation #:");

        TextBox_ConfirmationNumber.setText(" ");

        Label_PointsRequired.setText("Points Required:");

        TextBox_PointsRequired.setText("0");
        TextBox_PointsRequired.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBox_PointsRequiredActionPerformed(evt);
            }
        });

        Label_PointsAfter.setText("Points After:");

        Label_CalculatedPointsAfter.setText("0");

        Label_Discounted.setText("Was It Discounted:");

        Checkbox_Discounted.setBackground(new java.awt.Color(255, 255, 255));
        Checkbox_Discounted.setText("check if discounted");

        Label_Upgraded.setText("Was it upgraded?:");

        Checkbox_upgraded.setBackground(new java.awt.Color(255, 255, 255));
        Checkbox_upgraded.setText("check if upgraded");

        Label_GuestOrHolding.setText("Guest Lined Up?");

        Label_GuestName.setText("Guest Name:");

        Label_AmountPaid.setText("Amount Paid:");

        Label_DatePaid.setText("Date Paid:");

        Label_PaymentMethod.setText("Payment Method:");

        Btn_Okay.setBackground(new java.awt.Color(83, 255, 0));
        Btn_Okay.setText("Okay");
        Btn_Okay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_OkayActionPerformed(evt);
            }
        });

        Btn_Cancel.setBackground(new java.awt.Color(255, 0, 28));
        Btn_Cancel.setText("Cancel");
        Btn_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CancelActionPerformed(evt);
            }
        });

        jCheckBox1.setText("check if guest is lined up");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Total Amount Rented For");

        ComboBox_GuestName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBox_GuestName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_GuestNameActionPerformed(evt);
            }
        });

        jTextField1.setText("0.00");

        jTextField2.setText("0.00");

        TextBox_UnitSize.setEnabled(false);
        TextBox_UnitSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBox_UnitSizeActionPerformed(evt);
            }
        });

        jLabel4.setText("Confirm Email:");

        Label_Email.setText("--");

        Combobox_Month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04","05","06","07","08","09","10","11","12" }));
        Combobox_Month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combobox_MonthActionPerformed(evt);
            }
        });

        Combobox_Day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01" ,"02" ,"03" ,"04" ,"05" ,"06" ,"07" ,"08" ,"09" ,"10" ,"11" ,"12" ,"13" ,"14" ,"15" ,"16" ,"17" ,"18" ,"19" ,"20" ,"21" ,"22" ,"23" ,"24" ,"25" ,"26" ,"27" ,"28" ,"29" ,"30" ,"31" }));

        Combobox_Year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Combobox_Year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combobox_YearActionPerformed(evt);
            }
        });

        Button_Location.setText("Location...");
        Button_Location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_LocationActionPerformed(evt);
            }
        });

        jLabel5.setText("Location");

        Button_UnitSize.setText("Unit Size...");
        Button_UnitSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_UnitSizeActionPerformed(evt);
            }
        });

        jLabel6.setText("Confirm Email:");

        Label_confirmGuestEmail.setText("--");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "01", "02", "03", "04","05","06","07","08","09","10","11","12" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01" ,"02" ,"03" ,"04" ,"05" ,"06" ,"07" ,"08" ,"09" ,"10" ,"11" ,"12" ,"13" ,"14" ,"15" ,"16" ,"17" ,"18" ,"19" ,"20" ,"21" ,"22" ,"23" ,"24" ,"25" ,"26" ,"27" ,"28" ,"29" ,"30" ,"31" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Button_ViewOwner.setText("view owner info");
        Button_ViewOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ViewOwnerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TextBox_OtherPaymentMethod, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_User)
                        .addGap(18, 18, 18)
                        .addComponent(ComboBox_Owner, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_NumberOfNights)
                            .addComponent(Label_ReservationDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextBox_NumberOfNights)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Combobox_Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Combobox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Combobox_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_Upgraded)
                            .addComponent(Label_GuestOrHolding)
                            .addComponent(Label_GuestName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboBox_GuestName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Checkbox_upgraded)
                                    .addComponent(jCheckBox1))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_Discounted)
                            .addComponent(Button_ViewOwner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel4)
                                        .addGap(81, 81, 81)
                                        .addComponent(Label_Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Label_PointsAfter)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Label_CalculatedPointsAfter))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(Label_PointsAvailableText)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Label_availablePts)))
                                        .addGap(128, 128, 128))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Checkbox_Discounted)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Btn_Okay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Cancel)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_ConfirmationNumber)
                            .addComponent(Label_UnitSize)
                            .addComponent(Label_PointsRequired)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Textbox_Location)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Button_Location))
                            .addComponent(TextBox_PointsRequired)
                            .addComponent(TextBox_ConfirmationNumber)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextBox_UnitSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Button_UnitSize))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_PaymentMethod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_DatePaid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_AmountPaid)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Label_confirmGuestEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBox_Owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_User))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Label_Email))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_PointsAvailableText)
                            .addComponent(Label_availablePts)))
                    .addComponent(Button_ViewOwner, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Label_ReservationDate)
                        .addComponent(Combobox_Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Combobox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Combobox_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_NumberOfNights)
                            .addComponent(TextBox_NumberOfNights, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_UnitSize)
                            .addComponent(TextBox_UnitSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_UnitSize))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ConfirmationNumber)
                    .addComponent(TextBox_ConfirmationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_PointsRequired)
                    .addComponent(TextBox_PointsRequired, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_Location)
                    .addComponent(Textbox_Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_CalculatedPointsAfter)
                    .addComponent(Label_PointsAfter, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Discounted)
                    .addComponent(Checkbox_Discounted))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Upgraded)
                    .addComponent(Checkbox_upgraded))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_GuestOrHolding)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_GuestName)
                    .addComponent(ComboBox_GuestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_confirmGuestEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_AmountPaid, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_DatePaid)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_PaymentMethod, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TextBox_OtherPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Okay)
                    .addComponent(Btn_Cancel)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_OkayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_OkayActionPerformed
        
        /*this.TextBox_AmountPaid;
        this.TextBox_ConfirmationNumber;
        this.TextBox_DateOfReservation;
        this.TextBox_DatePaid;
        this.TextBox_GuestName;
        this.TextBox_NumberOfNights;
        this.TextBox_OtherPaymentMethod;
        this.TextBox_PointsRequired;
        this.TextBox_UseDate;
        this.Checkbox_Discounted;
        this.Checkbox_upgraded;
        this.
        */
        
        jf.dispose();
        
        
        
    }//GEN-LAST:event_Btn_OkayActionPerformed

    private void Btn_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CancelActionPerformed
        jf.dispose();
    }//GEN-LAST:event_Btn_CancelActionPerformed

    private void ComboBox_OwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_OwnerActionPerformed
        
        this.ownerIndexPicked = this.ComboBox_Owner.getSelectedIndex();
        
        if(ownerIndexPicked == 0){
            Label_Email.setText( "--" );
//            Label_availablePts.setText( "--" );
            return;
        }
        
        ownerIndexPicked = ownerIndexPicked -1; //adjust for the empty default option
        this.Label_availablePts.setText(Integer.toString(((Owner) ownersVector.get(ownerIndexPicked)).pointsOwned));
        this.Label_Email.setText(((Owner) ownersVector.get(ownerIndexPicked)).emailAddress.toString() );
    }//GEN-LAST:event_ComboBox_OwnerActionPerformed

    private void TextBox_UnitSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBox_UnitSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextBox_UnitSizeActionPerformed

    private void Combobox_YearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combobox_YearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combobox_YearActionPerformed

    private void TextBox_PointsRequiredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBox_PointsRequiredActionPerformed
       recalculatePointsAfter();
    }//GEN-LAST:event_TextBox_PointsRequiredActionPerformed

    private void Button_LocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_LocationActionPerformed
        PickLocationForm plf = new PickLocationForm(this);
    }//GEN-LAST:event_Button_LocationActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
       
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void Combobox_MonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combobox_MonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combobox_MonthActionPerformed

    private void TextBox_NumberOfNightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBox_NumberOfNightsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextBox_NumberOfNightsActionPerformed

    private void Button_UnitSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_UnitSizeActionPerformed
        new PickUnitSize(this);
    }//GEN-LAST:event_Button_UnitSizeActionPerformed

    private void ComboBox_GuestNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_GuestNameActionPerformed
        
        //if we are not on the blank choice
        if(ComboBox_GuestName.getSelectedIndex() != 0){
            
            int indexSelected = ComboBox_GuestName.getSelectedIndex();
            
            //compensate for the blank choice
            indexSelected = indexSelected - 1;
            
            //grab the appropriate guest
            Guest selectedGuest = (Guest) guests.get(indexSelected);
            
            //set the confirmation email label
            Label_confirmGuestEmail.setText( selectedGuest.getEmailAddress().toString() );
            
            
        }else{
            Label_confirmGuestEmail.setText( "--" );
        }
    }//GEN-LAST:event_ComboBox_GuestNameActionPerformed

    private void Button_ViewOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ViewOwnerActionPerformed
        String userName = ComboBox_Owner.getSelectedItem().toString();
        JFrame jf = new JFrame();
        jf.setSize(740,272); //size of jpanel
        jf.add(new OwnerMaitenanceForm(userName, jf));
        jf.setVisible(true);
        
        
    }//GEN-LAST:event_Button_ViewOwnerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Cancel;
    private javax.swing.JButton Btn_Okay;
    private javax.swing.JButton Button_Location;
    private javax.swing.JButton Button_UnitSize;
    private javax.swing.JButton Button_ViewOwner;
    private javax.swing.JCheckBox Checkbox_Discounted;
    private javax.swing.JCheckBox Checkbox_upgraded;
    private javax.swing.JComboBox ComboBox_GuestName;
    public javax.swing.JComboBox ComboBox_Owner;
    public javax.swing.JComboBox Combobox_Day;
    public javax.swing.JComboBox Combobox_Month;
    public javax.swing.JComboBox Combobox_Year;
    private javax.swing.JLabel Label_AmountPaid;
    private javax.swing.JLabel Label_CalculatedPointsAfter;
    private javax.swing.JLabel Label_ConfirmationNumber;
    private javax.swing.JLabel Label_DatePaid;
    private javax.swing.JLabel Label_Discounted;
    private javax.swing.JLabel Label_Email;
    private javax.swing.JLabel Label_GuestName;
    private javax.swing.JLabel Label_GuestOrHolding;
    private javax.swing.JLabel Label_NumberOfNights;
    private javax.swing.JLabel Label_PaymentMethod;
    private javax.swing.JLabel Label_PointsAfter;
    private javax.swing.JLabel Label_PointsAvailableText;
    private javax.swing.JLabel Label_PointsRequired;
    private javax.swing.JLabel Label_ReservationDate;
    private javax.swing.JLabel Label_UnitSize;
    private javax.swing.JLabel Label_Upgraded;
    private javax.swing.JLabel Label_User;
    private javax.swing.JLabel Label_availablePts;
    private javax.swing.JLabel Label_confirmGuestEmail;
    public javax.swing.JTextField TextBox_ConfirmationNumber;
    public javax.swing.JTextField TextBox_NumberOfNights;
    private javax.swing.JTextField TextBox_OtherPaymentMethod;
    private javax.swing.JTextField TextBox_PointsRequired;
    public javax.swing.JTextField TextBox_UnitSize;
    private javax.swing.JTextField Textbox_Location;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
