package cn.mldn.zwb.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.zwb.vo.News;



public interface INewsServiceBack {
	public boolean add(News vo) throws Exception;

	public boolean edit(News vo) throws Exception;

	public Map<String, Object> preEdit(long nid) throws Exception;

	public boolean delete(Set<Long> ids) throws Exception;

	public Map<String, Object> list(String column, String keyWord, long currentPage, int lineSize) throws Exception;
}
