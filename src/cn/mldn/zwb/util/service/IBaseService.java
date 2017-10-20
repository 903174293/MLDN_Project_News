package cn.mldn.zwb.util.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 定义用户操作的业务
 * 
 * @author mldn
 */
public interface IBaseService<K, V> {
	/**
	 * 实现用户数据的增加操作，该处理操作执行步骤如下：<br>
	 * <li>1、首先要调用IVDAO.findById()方法查看当前要追加的用户编号是否存在；</li>
	 * <li>2、如果要增加的用户编号不存在，则执行IVDAO.doCreate()方法进行追加。</li>
	 * @param vo 包含新增的用户数据
	 * @return 如果增加成功返回true，如果用户存在则返回false
	 */
	public boolean add(V vo) throws Exception; 
	/**
	 * 实现用户的修改处理操作，该操作需要执行的IVDAO.doEdit()方法；
	 * @param vo 要修改的新的用户数据
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean edit(V vo) throws Exception;
	/**
	 * 进行指定用户编号的删除处理操作，调用IVDAO.doRemove()方法
	 * @param id 要删除的用户编号
	 * @return 如果用户删除成功返回true，如果没有删除或者用户不存在返回false
	 */
	public boolean delete(K id) throws Exception;
	/**
	 * 进行用户信息的批量删除处理操作，调用IVDAO.doRemove()方法
	 * @param ids 要删除的用户的编号，不能有重复
	 * @return 如果全部的数据都删除了则返回true，如果没有删除数据（null、size()==0）以及删除失败返回false
	 */
	public boolean delete(Set<K> ids) throws Exception;
	/**
	 * 根据用户的编号获得用户的完整信息，调用IVDAO.findById()方法
	 * @param id 要查询的用户编号
	 * @return 如果用户存在则以VO对象形式返回，如果不存在返回null
	 */
	public V get(K id) throws Exception;
	/**
	 * 查询全部数据信息
	 * @return 返回全部用户信息
	 */
	public List<V> list() throws Exception; 
	/**
	 * 进行数据的分页显示查询，在进行该查询处理的时候需要考虑两种情况：<br>
	 * <ul><li>情况一：没有设置查询列或者是关键字</li><li><ul>
	 * 	<li>调用IVDAO.findAll()方法得到指定的分页数据 </li>
	 * 	<li>调用IVDAO.getAllCount()方法得到数据行数 </li>
	 * </ul></li></ul>
	 * <ul><li>情况二：设置有查询关键字或模糊列</li><li><ul>
	 * 	<li>调用IVDAO.findSplit()方法得到指定的分页数据 </li>
	 * 	<li>调用IVDAO.getSplitCount()方法得到数据行数 </li>
	 * </ul></li></ul>
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行
	 * @param column 模糊查询列
	 * @param keyWord 查询关键字
	 * @return 该查询处理操作会返回两类数据：<br>
	 * <li>key = allVs、value = 全部的用户数据，以List集合返回</li>
	 * <li>key = VCount、value = 用户表中的数据量。</li>
	 */
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord) throws Exception;
}
