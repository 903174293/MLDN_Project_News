package cn.mldn.zwb.action;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.zwb.factory.Factory;
import cn.mldn.zwb.service.back.INewsServiceBack;
import cn.mldn.zwb.util.action.ActionResourceUtil;
import cn.mldn.zwb.util.action.abs.AbstractAction;
import cn.mldn.zwb.util.web.ModelAndView;
import cn.mldn.zwb.util.web.ServletObjectUtil;
import cn.mldn.zwb.util.web.SplitPageUtil;
import cn.mldn.zwb.vo.News;

public class NewsAction extends AbstractAction {
	public static final String ACTION_TITLE = "新闻" ;
	public String addPre() {
		return ActionResourceUtil.getPage("news.add.page") ;
	}
//	public ModelAndView addPre() {
//		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("news.add.page"));
//		return mav;
//	}
	public ModelAndView add(News vo) {//vo中有大部分值传过来，但是不包含photo
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;//关键注意：这里使用action不是page的原因：隐含者一个原则：跳转页面时，尽量不使用jsp格式；使用action;保证了页面信息的安全性（WEB-INF文件夹不能直接访问）
		if (ServletObjectUtil.getParam().isUpload("pic")) {														//增加完成之后，要做下一次增加所以使用action，考虑到pre方法中可能会增加其他业务操作，所以，优先使用action，而不使用jsp		
			String fileName = ServletObjectUtil.getParam().createUploadFileName("pic").get(0) ;
			vo.setPhoto(fileName); // 保存上传的图片名称
		} else {
			vo.setPhoto("nophoto.jpg"); 
		}
		INewsServiceBack newsService = Factory.getIServiceInstance("news.service.back") ;
		try {
			if (newsService.add(vo)) {	// 保存数据成功
				super.setUrlAndMsg(mav, "news.add.action", "vo.add.success",ACTION_TITLE );//专门进行数据传输的方法
				if (ServletObjectUtil.getParam().isUpload("pic")) {
					String filePath = ServletObjectUtil.getApplication().getRealPath("/upload/news/") + vo.getPhoto() ;
					ServletObjectUtil.getParam().saveUploadFile("pic", filePath);//关键性的一步：进行图片的保存处理
				}
			} else {
				super.setUrlAndMsg(mav, "news.add.action", "vo.add.failure",ACTION_TITLE );
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setUrlAndMsg(mav, "news.add.action", "vo.add.failure",ACTION_TITLE );
		}
		return mav ;
	}
	public ModelAndView editPre(long nid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("news.edit.page")) ;
		INewsServiceBack newsService = Factory.getIServiceInstance("news.service.back") ;
		try {
			mav.addObjectMap(newsService.preEdit(nid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView edit(News vo,String oldphoto) {//这里附加有原始的图片名称
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		vo.setPhoto(oldphoto); 
		if (ServletObjectUtil.getParam().isUpload("pic")) {
			if ("nophoto.jpg".equals(oldphoto)) {	// 原本没有图片名称
				String fileName = ServletObjectUtil.getParam().createUploadFileName("pic").get(0) ;
				vo.setPhoto(fileName); // 保存上传的图片名称
			}
		} 
		INewsServiceBack newsService = Factory.getIServiceInstance("news.service.back") ;
		try {
			if (newsService.edit(vo)) {	// 保存数据成功
				super.setUrlAndMsg(mav, "news.list.action", "vo.edit.success",ACTION_TITLE );
				if (ServletObjectUtil.getParam().isUpload("pic")) {
					String filePath = ServletObjectUtil.getApplication().getRealPath("/upload/news/") + vo.getPhoto() ;
					ServletObjectUtil.getParam().saveUploadFile("pic", filePath);
				}
			} else {
				super.setUrlAndMsg(mav, "news.list.action", "vo.edit.failure",ACTION_TITLE );
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setUrlAndMsg(mav, "news.list.action", "vo.edit.failure",ACTION_TITLE );
		}
		return mav ;
	}
	public ModelAndView delete() {	
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		Map<Long,String> map = super.handlePhotoLong(ServletObjectUtil.getParam().getParameter("ids")) ;//关键注意：这里的参数是ids----------------js中对其进行了处理
		INewsServiceBack newsService = Factory.getIServiceInstance("news.service.back") ;
		try {
			if (newsService.delete(map.keySet())) {	// 数据删除
				Iterator<Map.Entry<Long,String>> iter = map.entrySet().iterator() ;
				while (iter.hasNext()) {
					String fileName = iter.next().getValue() ;
					String filePath = ServletObjectUtil.getApplication().getRealPath("/upload/news/") + fileName ;
					File file = new File(filePath) ;
					if (file.exists()) {
						file.delete() ;
					}
				}
				super.setUrlAndMsg(mav, "news.list.action", "vo.delete.success",ACTION_TITLE );
			} else {
				super.setUrlAndMsg(mav, "news.list.action", "vo.delete.failure",ACTION_TITLE );
			}
		} catch (Exception e) {
			super.setUrlAndMsg(mav, "news.list.action", "vo.delete.failure",ACTION_TITLE );
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("news.list.page")) ;
		SplitPageUtil spu = new SplitPageUtil("新闻标题:title", "news.list.action") ;//----------------------dispatcher对分页的支持：关键
		INewsServiceBack newsService = Factory.getIServiceInstance("news.service.back") ;
		try {
			mav.addObjectMap(newsService.list(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
}
