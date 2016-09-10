package com.congbbs.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.congbbs.dao.MessageDao;
import com.congbbs.javabean.Message;
import com.congbbs.javabean.PageBean;
import com.congbbs.util.DbConn;

public class MessageDaoImpl implements MessageDao {

	//插入一条消息
	@Override
	public void addMessage(Message message) {
		//通过工具类获得数据库的链接
		Connection conn = DbConn.getConnection();
		//编写sql语句
		String sql = "insert into tb_message(messageTitle, messageContent,userID, publishTime)"+
		"values(?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); 	//将传过来的数据对应装配到sql语句中
			ps.setString(1, message.getMessageTitle());
			ps.setString(2, message.getMessageContent());
			ps.setInt(3, message.getUserID());
			ps.setTimestamp(4, new Timestamp(message.getPublishTime().getTime()));  // 获取当前操作的时间
			ps.execute(); 		//将数据提交到数据库中
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {				//关闭相应的数据连接对象
			DbConn.close(ps);
			DbConn.close(conn);
		}
	}
	//分页查询数据库中的记录
	@Override
	public List<Message> findAllMessage(PageBean pageBean) {
		Connection conn = DbConn.getConnection();
		//将消息按发布时间时间进行排序并分页查询出来
		String findSQL = "select * from tb_message order by publishTime desc limit ?,?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pageBegin =  (pageBean.getCurrPage()-1)*pageBean.getPageSize(); //计算每页相应的查询起点
		List<Message> list = new ArrayList<Message>();
		try {
			ps = conn.prepareStatement(findSQL);
			ps.setInt(1, pageBegin);     //设置查询的的起点
			ps.setInt(2, pageBean.getPageSize());   //设置查询的每页记录数
			rs = ps.executeQuery();   //执行查询返回结果集
			while(rs.next()){         //将查询到的数据装配到对象中
				Message message = new Message();
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setUserID(rs.getInt(4));
				message.setPublishTime(rs.getDate(5));
				list.add(message);     //将数据对象装配进list列表中
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {      
			DbConn.close(rs);
			DbConn.close(ps);
			DbConn.close(conn);
		}
		return list;
	}
    //查询总记录数
	@Override
	public int findAllCount() {
		Connection conn = DbConn.getConnection(); //获得数据库连接
		String sql = "select count(*) from tb_message";
		int count = 0;   //定义一个计数变量
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);  //将查询出来的记录数赋给count
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

	@Override
	public Message fingMessageById(int messageId) {
		Connection conn = DbConn.getConnection();
		String sql="select * from tb_message where messageID =?";
		PreparedStatement ps= null;
		ResultSet rs = null;
		Message message = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messageId);
			rs = ps.executeQuery();
			if(rs.next()){
				message = new Message();
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setUserID(rs.getInt(4));
				message.setPublishTime(rs.getDate(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbConn.close(rs);
			DbConn.close(ps);
			DbConn.close(conn);
		}
		return message;
	}
	
	//模糊查询数据库中的记录
	@Override
	public List<Message> findMessageByLike(String keyWord) {
		Connection conn = DbConn.getConnection();
		String sql = "select * from tb_message where messageTitle like ?";
		PreparedStatement ps= null;
		ResultSet rs= null;
		List<Message> list = new ArrayList<Message>();
		try {
			ps = conn.prepareStatement(sql);
			String keyWords = "%"+keyWord+"%";
			ps.setString(1, keyWords);
			rs = ps.executeQuery();
			while(rs.next()){
				Message message = new Message();
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setUserID(rs.getInt(4));
				message.setPublishTime(rs.getDate(5));
				list.add(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbConn.close(rs);
			DbConn.close(ps);
			DbConn.close(conn);
		}
		return list;
	}
	
	
}
