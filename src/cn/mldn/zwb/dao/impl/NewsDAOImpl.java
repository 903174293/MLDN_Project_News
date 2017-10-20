package cn.mldn.zwb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.zwb.dao.INewsDAO;
import cn.mldn.zwb.dao.abs.AbstractDAO;
import cn.mldn.zwb.vo.News;

public class NewsDAOImpl extends AbstractDAO implements INewsDAO {

	@Override
	public boolean doCreate(News vo) throws SQLException {
		String sql = "INSERT INTO news (title,note,photo) VALUES (?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getNote());
		super.pstmt.setString(3, vo.getPhoto());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doEdit(News vo) throws SQLException {
		String sql = "UPDATE news SET title=?,note=?,photo=? WHERE nid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getNote());
		super.pstmt.setString(3, vo.getPhoto());
		super.pstmt.setLong(4, vo.getNid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		String sql = "DELETE FROM news WHERE nid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		StringBuffer sql = new StringBuffer("DELETE FROM news WHERE nid IN (");
		Iterator<Long> iter = ids.iterator();
		while (iter.hasNext()) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length() - 1, sql.length()).append(")");
		super.pstmt = super.conn.prepareStatement(sql.toString());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public News findById(Long id) throws SQLException {
		String sql = "SELECT nid,title,note,photo FROM news WHERE nid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			News vo = new News();
			vo.setNid(rs.getLong(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
			vo.setPhoto(rs.getString(4));
			return vo;
		}
		return null;
	}

	@Override
	public List<News> findAll() throws SQLException {
		List<News> all = new ArrayList<News>();
		String sql = "SELECT nid,title,note,photo FROM news";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			News vo = new News();
			vo.setNid(rs.getLong(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
			vo.setPhoto(rs.getString(4));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<News> findAll(Long currentPage, Integer lineSize) throws Exception {
		List<News> all = new ArrayList<News>();
		String sql = "SELECT nid,title,note,photo FROM news LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			News vo = new News();
			vo.setNid(rs.getLong(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
			vo.setPhoto(rs.getString(4));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<News> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		List<News> all = new ArrayList<News>();
		String sql = "SELECT nid,title,note,photo FROM news WHERE " + column + " LIKE ? LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			News vo = new News();
			vo.setNid(rs.getLong(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
			vo.setPhoto(rs.getString(4));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM news";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM news WHERE " + column + " LIKE ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

}
