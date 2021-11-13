package model;

public class Category {
	
	private long cateId;
	private String cateName;

	public Category() { }		// 기본 생성자
	
	public Category(long cateId, String cateName) {
		this.cateId = cateId;
		this.cateName = cateName;
	}

	public long getCateId() {
		return cateId;
	}

	public void setCateId(long cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
}
