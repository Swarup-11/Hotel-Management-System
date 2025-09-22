package com.hrs;
import com.hrs.ui.MainFrame; import javax.swing.SwingUtilities;
public class HotelReservationSystem {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{ MainFrame mf=new MainFrame(); mf.setVisible(true); });
    }
}