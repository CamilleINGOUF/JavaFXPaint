package drawing;

import java.util.ArrayList;

public interface RearrangeStrategy {
	
	public void rearrange(Drawing drawing);

	public RearrangeStrategy clone();
	
	public ArrayList<Shape> getNews();
	
	public ArrayList<Shape> getOlds();
	
	public void clear();
}
