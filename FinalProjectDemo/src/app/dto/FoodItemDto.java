package app.dto;

public class FoodItemDto {
	private String itemName;
	private Long stallId;
	private Double price;
	private String errorMessage;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Long getStallId() {
		return stallId;
	}
	public void setStallId(Long stallId) {
		this.stallId = stallId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "FoodItemDto [itemName=" + itemName + ", stallId=" + stallId + ", price=" + price + ", errorMessage="
				+ errorMessage + "]";
	}
	
	
}
