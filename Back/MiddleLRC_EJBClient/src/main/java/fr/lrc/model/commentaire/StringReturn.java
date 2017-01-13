package fr.lrc.model.commentaire;

public class StringReturn {

	private final boolean response;
	private final String commentaire;

	private StringReturn(boolean response, String commentaire) {
		super();
		this.response = response;
		this.commentaire = commentaire;
	}

	public boolean isResponse() {
		return response;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public static StringReturn stringReturnMessage(boolean response, String commentaire) {
		return new StringReturn(response, commentaire);
	}

	public static String stringMessage(boolean response, String commentaire) {
		StringBuilder s = new StringBuilder();
		s.append("{").append("\"response\":").append(response).append(",").append("\"commentaire\":").append("\"")
				.append(commentaire).append("\"").append("}");
		return s.toString();
	}

	
}
