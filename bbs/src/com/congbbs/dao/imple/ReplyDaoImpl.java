package com.congbbs.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.congbbs.dao.ReplyDao;
import com.congbbs.javabean.PageBean;
import com.congbbs.javabean.Reply;
import com.congbbs.util.DbConn;

public class ReplyDaoImpl implements ReplyDao {

	
	
	@Override
	public void addreply(Reply reply) {
		Connection conn = DbConn.getConnection();
		String sql = "insert into tb_reply (replyContent,replyID,replyTime,messageID,userID)"
		+"values(?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, reply.getReplyContent());
			ps.setInt(2, reply.getReplyID());
			ps.setTimestamp(3, new Timestamp(reply.getReplyTime().getTime()));
			ps.setInt(4, reply.getMessageID());
			ps.setInt(5, reply.getUserID());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbConn.close(ps);
			DbConn.close(conn);
		}
	}

	@Override
	public List<Reply> findReplyByMsgId(int messageId, PageBean pageBean) {
		Connection conn = DbConn.getConnection();
		String sql ="select * from tb_reply where messageID = ? limit ?, ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reply> replays = new ArrayList<Reply>();
		int pageBegin = 0; 
		if(pageBean.getCurrPage() == 0){
			pageBegin = 1;
		}else{
			pageBegin = (pageBean.getCurrPage()-1)*pageBean.getPageSize();
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messageId);
			ps.setInt(2, pageBegin);
			ps.setInt(3, pageBean.getPageSize());
			rs = ps.executeQuery();
			while(rs.next()){
				Reply reply = new Reply();
				reply.setReplyID(rs.getInt(1));
				reply.setReplyContent(rs.getString(2));
				reply.setUserID(rs.getInt(3));
				reply.setReplyTime(rs.getDate(4));
				reply.setMessageID(rs.getInt(5));
				replays.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbConn.close(rs);
			DbConn.close(ps);
			DbConn.close(conn);
		}
		return replays;
		
	}

	@Override
	public int findCountByMsgId(int messageID) {
		Connection conn = DbConn.getConnection();
		String sql ="select count(*) from tb_reply where messageID = ?";
		PreparedStatement ps = null;
		ResultSet rs =null;
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messageID);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbConn.close(rs);
			DbConn.close(ps);
			DbConn.close(conn);
		}
		return count;
	}
	
	
}
