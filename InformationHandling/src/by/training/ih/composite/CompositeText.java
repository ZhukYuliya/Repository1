package by.training.ih.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeText implements TextComponent {
	private List<TextComponent> components;

	public CompositeText() {
        components = new ArrayList<TextComponent>();
    }
	
	 public CompositeText(CompositeText text){
	        components = new ArrayList<TextComponent>();
	        for (TextComponent pharagraph : text.getComponents()) {
	        	TextComponent newPharagraph = new ParagraphComposite();
	            for (TextComponent sentence : pharagraph.getComponents()) {
	            	TextComponent newSentence = new CompositeText();
	                for (TextComponent lexeme : sentence.getComponents()) {
	                    TextComponent newLexeme = new Lexeme(lexeme.toString().trim());
	                    newSentence.add(newLexeme);
	                }
	                newPharagraph.add(newSentence);
	            }
	            components.add(newPharagraph);
	        }
	    }
	
	@Override
	public void add(TextComponent component) {
		components.add(component);
	}

	@Override
	public void remove(TextComponent component) {
		components.remove(component);

	}
	public List<TextComponent> getComponents() {
        return components;
    }

	@Override
	public String toString() {
		StringBuilder allText = new StringBuilder();
		for (TextComponent component : components) {
			allText = allText.append(component.toString());
		}
		return allText.toString();
	}
}
