package by.training.ih.manupulation;

import java.util.Comparator;

import by.training.ih.composite.TextComponent;

public class CompositeTextSizeComparator implements Comparator<TextComponent> {
	public int compare(TextComponent element1, TextComponent element2) {
		return element1.getComponents().size() - element2.getComponents().size();

	}

}
