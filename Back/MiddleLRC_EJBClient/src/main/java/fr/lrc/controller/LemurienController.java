package fr.lrc.controller;

import java.util.Map;
import java.util.regex.Pattern;

import fr.lrc.model.commentaire.StringReturn;
import fr.lrc.model.lemurien.LemurienModel;

public class LemurienController {

	private static final String REGEX_DATE_SHORT = "^(1[0-2]|0[1-9]|\\d)\\/([0-9]\\d)$";
	private static final String REGEX_DATE_LONG = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{2}$";

	private LemurienController() {
	}

	public static StringReturn lemurienController(LemurienModel lemurienM) {
		if (lemurienM == null) {
			return StringReturn.stringReturnMessage(false, "Object null");
		}

		Map<String, String> mapParam = lemurienM.getAllParameters();
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
		} else if (!date.isEmpty()
				&& (!Pattern.matches(REGEX_DATE_LONG, date) && !Pattern.matches(REGEX_DATE_SHORT, date))) {
			return StringReturn.stringReturnMessage(false, nomDate + " non conforme");
		} else {
			return StringReturn.stringReturnMessage(true, "");
		}
	}
}
