package com.meli.games.notifier.entity;

public enum CategoriesEnum {
	
	CONSOLASYVIDEOJUEGOS("MLA1144"),
	SINCATEGORIA("");
	
	CategoriesEnum(String urlCategoria) {
		this.setUrlCategoria(urlCategoria);
	}

	private String urlCategoria;
	
	public String getUrlCategoria() {
		return urlCategoria;
	}

	public void setUrlCategoria(String urlCategoria) {
		this.urlCategoria = urlCategoria;
	}
}
