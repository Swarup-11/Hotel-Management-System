package com.hrs.dao;
import com.hrs.DBConnection; import com.hrs.model.Room; import java.sql.*; import java.util.ArrayList; import java.util.List;
public class RoomDAO {
    public List<Room> getAvailableRooms() throws SQLException {
        String sql="SELECT * FROM rooms WHERE status='AVAILABLE'";
        try(Connection conn=DBConnection.getConnection(); PreparedStatement ps=conn.prepareStatement(sql)) {
            ResultSet rs=ps.executeQuery(); List<Room> list=new ArrayList<>();
            while(rs.next()) list.add(new Room(rs.getInt("id"), rs.getString("room_number"), rs.getString("type"), rs.getDouble("price"), rs.getString("status")));
            return list;
        }
    }
    public void updateRoomStatus(int roomId, String status) throws SQLException {
        String sql="UPDATE rooms SET status=? WHERE id=?";
        try(Connection conn=DBConnection.getConnection(); PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,status); ps.setInt(2,roomId); ps.executeUpdate();
        }
    }
}