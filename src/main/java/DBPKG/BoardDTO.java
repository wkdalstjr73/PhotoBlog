package DBPKG;

public class BoardDTO {
	private String p_no;
	private String img;
	private String p_name;
	private String p_content;
	private int category;

	public String getP_no() {
		return p_no;
	}

	public void setP_no(String p_no) {
		this.p_no = p_no;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "BoardDTO [p_no=" + p_no + ", img=" + img + ", p_name=" + p_name + ", p_content=" + p_content
				+ ", category=" + category + "]";
	}

}
