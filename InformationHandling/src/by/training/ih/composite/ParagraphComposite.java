package by.training.ih.composite;

public class ParagraphComposite extends CompositeText{
	
	//a separate class for paragraph is created for inserting new line after each paragraph 
	
	@Override
	public String toString() {
		StringBuilder paragraph = new StringBuilder();
		for (TextComponent component : getComponents()) {
			paragraph = paragraph.append(component.toString());
		}
		paragraph.append("\n");
		return paragraph.toString();
	}
}
