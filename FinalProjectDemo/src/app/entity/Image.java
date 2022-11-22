package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="images_table")
public class Image {
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long imageId;
	
	@Column
	@NotNull
	private String fileName;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Images [imageId=" + imageId + ", fileName=" + fileName + "]";
	}
	
	
}
