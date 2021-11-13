package model;

public class Category {
	
	private int cateId;
	private String cateName;

	public Category() { }		// 기본 생성자
	
	public Category(int cateId, String cateName) {
		this.cateId = cateId;
		this.cateName = cateName;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
}
