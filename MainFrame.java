package com.hrs.ui;
import com.hrs.dao.RoomDAO; import com.hrs.model.Room; import javax.swing.*; import javax.swing.table.DefaultTableModel; import java.awt.*; import java.sql.SQLException; import java.util.List;
public class MainFrame extends JFrame {
    private RoomDAO roomDAO = new RoomDAO(); private JTable roomsTable;
    public MainFrame(){
        setTitle("Hotel Reservation System"); setSize(600,400); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLocationRelativeTo(null);
        roomsTable=new JTable(); add(new JScrollPane(roomsTable), BorderLayout.CENTER);
        JButton refreshBtn=new JButton("Refresh Rooms"); JButton bookBtn=new JButton("Book Selected Room");
        refreshBtn.addActionListener(e->refreshRooms()); bookBtn.addActionListener(e->bookRoom());
        JPanel panel=new JPanel(); panel.add(refreshBtn); panel.add(bookBtn); add(panel, BorderLayout.SOUTH);
        refreshRooms();
    }
    private void refreshRooms(){
        try{
            List<Room> rooms=roomDAO.getAvailableRooms();
            DefaultTableModel model=new DefaultTableModel(new Object[]{"ID","Room No","Type","Price","Status"},0);
            for(Room r:rooms) model.addRow(new Object[]{r.getId(),r.getRoomNumber(),r.getType(),r.getPrice(),r.getStatus()});
            roomsTable.setModel(model);
        }catch(SQLException e){ e.printStackTrace(); JOptionPane.showMessageDialog(this,"Failed to load rooms"); }
    }
    private void bookRoom(){
        int sel=roomsTable.getSelectedRow(); if(sel<0){ JOptionPane.showMessageDialog(this,"Select a room to book"); return; }
        int roomId=(Integer)roomsTable.getValueAt(sel,0);
        try{ roomDAO.updateRoomStatus(roomId,"BOOKED"); JOptionPane.showMessageDialog(this,"Room booked successfully!"); refreshRooms(); }
        catch(SQLException e){ e.printStackTrace(); }
    }
}