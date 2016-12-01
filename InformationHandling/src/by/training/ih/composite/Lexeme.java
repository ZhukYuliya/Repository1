package by.training.ih.composite;

import java.util.List;

public class Lexeme implements TextComponent {
	
	private String value;
	
	public Lexeme(String lex){
		this.value = lex;
	}

	@Override
	public void add(TextComponent c) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void remove(TextComponent c) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String toString() {
		return value + " ";
	}

	@Override
	public List<TextComponent> getComponents() {
		throw new UnsupportedOperationException();
	}
	
}
