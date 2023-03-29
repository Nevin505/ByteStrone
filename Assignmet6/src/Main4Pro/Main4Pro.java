package Main4Pro;
import com.myshop.product.*;
import com.myshop.product.beauty.BeautyProduct;
import com.myshop.product.electronics.ElectronicProduct;
import com.myshop.product.food.FoodProduct;
//import com.myshop.product.Product;
class Main4Pro{
	public static void main(String[] args) {
		
		Product product=new Product();
		
//		BeautyProduct b1=new BeautyProduct();
		
		FoodProduct f1=new FoodProduct();
		System.out.println();
		product.getProductdetails();
		f1.getFoodData();
		product.displayProduct();
		f1.displayFoodData();
		ElectronicProduct e1=new ElectronicProduct();
		product.getProductdetails();
		e1.getProduct();
		product.displayProduct();
		e1.display();
		
		
	}
}
