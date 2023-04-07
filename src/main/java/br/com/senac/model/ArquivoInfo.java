
package br.com.senac.model;




public class ArquivoInfo {
	
	private String filename;
	private String url;
	
	public ArquivoInfo(String fileName, String url) {
		super();
		this.filename = fileName;
		this.url = url;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String fileName) {
		this.filename = fileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
