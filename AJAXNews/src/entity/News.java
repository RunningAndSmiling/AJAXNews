package entity;

public class News {
	private int id;
	private String title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public News(String title) {
		super();
		this.title = title;
	}
	

}
