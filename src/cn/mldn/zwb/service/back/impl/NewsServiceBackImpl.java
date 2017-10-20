package cn.mldn.zwb.service.back.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.zwb.dao.INewsDAO;
import cn.mldn.zwb.factory.Factory;
import cn.mldn.zwb.service.abs.AbstractService;
import cn.mldn.zwb.service.back.INewsServiceBack;
import cn.mldn.zwb.vo.News;

public class NewsServiceBackImpl extends AbstractService implements INewsServiceBack {

	@Override
	public boolean add(News vo) throws Exception {
		INewsDAO newsDAO = Factory.getIDAOInstance("news.dao");
		return newsDAO.doCreate(vo);
	}

	@Override
	public boolean edit(News vo) throws Exception {
		INewsDAO newsDAO = Factory.getIDAOInstance("news.dao");
		return newsDAO.doEdit(vo);
	}

	@Override
	public Map<String, Object> preEdit(long nid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		INewsDAO newsDAO = Factory.getIDAOInstance("news.dao");
		map.put("news", newsDAO.findById(nid));//放到map中的一个关键性的原因：可以方便的放入request的请求范围之中，在页面取出进行回显
		return map;
	}

	@Override
	public boolean delete(Set<Long> ids) throws Exception {
		if (ids == null || ids.size() == 0) {
			return false;
		}
		INewsDAO newsDAO = Factory.getIDAOInstance("news.dao");
		return newsDAO.doRemove(ids);
	}

	@Override
	public Map<String, Object> list(String column, String keyWord, long currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		INewsDAO newsDAO = Factory.getIDAOInstance("news.dao") ;
		if (super.isLikeSearch(column) && super.isLikeSearch(keyWord)) {
			map.put("allNews", newsDAO.findSplit(column, keyWord, currentPage, lineSize)) ;
			map.put("allRecorders", newsDAO.getSplitCount(column, keyWord)) ;
		} else {
			map.put("allNews", newsDAO.findAll(currentPage, lineSize)) ;
			map.put("allRecorders", newsDAO.getAllCount()) ;
		}
		return map;
	}

}
