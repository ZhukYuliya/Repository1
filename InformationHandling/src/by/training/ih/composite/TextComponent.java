package by.training.ih.composite;

import java.util.List;

public interface TextComponent {
	
	public void add(TextComponent c);
	public void remove(TextComponent c);
	
	public List<TextComponent> getComponents();
}
