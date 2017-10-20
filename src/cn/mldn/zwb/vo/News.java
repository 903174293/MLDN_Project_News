package cn.mldn.zwb.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class News implements Serializable{
	private Long nid ;
	private String title;
	private String note;
	private String photo;
	public Long getNid() {
		return nid;
	}
	public void setNid(Long nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "News [nid=" + nid + ", title=" + title + ", note=" + note + ", photo=" + photo + "]";
	}

}
