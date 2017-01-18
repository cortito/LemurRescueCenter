package fr.lrc.controller;

import java.util.Map;
import java.util.regex.Pattern;

import fr.lrc.model.commentaire.StringReturn;
import fr.lrc.model.poids.PoidsModel;

public class PoidsController {
	private static final String REGEX_DATE = "^(1[0-2]|0[1-9]|\\d)\\/([0-9]\\d)$";
	private static final String REGEX_POIDS = "^\\d+\\.\\d+|\\d+$";

	private PoidsController() {
	}

	public static StringReturn poidsController(PoidsModel poidsM) {

		if (poidsM == null) {
			return StringReturn.stringReturnMessage(false, "Object null");
		} else if (poidsM.getPoids() == null) {
			poidsM.setPoids("0.0");
		}
		if (!poidsM.getPoids().isEmpty() && !Pattern.matches(REGEX_POIDS, poidsM.getPoids())) {
			return StringReturn.stringReturnMessage(false, "Poids non conforme");
		}
		poidsM.setUpperCaseNom();

		Map<String, String> mapParam = poidsM.getAllParameters();

		StringReturn s = null;
		for (String nomParam : mapParam.keySet()) {
			if (nomParam.contains("Date")) {
				s = isDateValide(nomParam, mapParam.get(nomParam));
			} else {
				s = isStringValid(nomParam, mapParam.get(nomParam), 64);
			}
			if (!s.isResponse()) {
				return s;
			}
		}
		return StringReturn.stringReturnMessage(true, "");
	}

	public static StringReturn isStringValid(String nomParam, String param, int length) {
		if (param == null) {
			return StringReturn.stringReturnMessage(false, nomParam + " null");
		} else if (param.length() > length) {
			return StringReturn.stringReturnMessage(false, nomParam + " trop long - Max : " + length);
		} else {
			return StringReturn.stringReturnMessage(true, "");
		}
	}

	public static StringReturn isDateValide(String nomDate, String date) {

		if (date == null) {
			return StringReturn.stringReturnMessage(false, nomDate + " null");
		} else if (!date.isEmpty() && !Pattern.matches(REGEX_DATE, date)) {
			return StringReturn.stringReturnMessage(false, nomDate + " non conforme");
		} else {
			return StringReturn.stringReturnMessage(true, "");
		}
	}
}
