package fr.lrc.model.commentaire;

public class StringReturn {
	private boolean response;
	private String commentaire;

	public StringReturn(boolean response, String commentaire) {
		super();
		this.response = response;
		this.commentaire = commentaire;
	}

	public StringReturn() {
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String message() {
		StringBuilder s = new StringBuilder();
		s.append("{").append("\"response\":").append(response).append(",").append("\"commentaire\":").append("\"")
				.append(commentaire).append("\"").append("}");
		return s.toString();
	}

}
