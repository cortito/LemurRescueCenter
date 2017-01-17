package lrc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.lrc.controller.LemurienController;
import fr.lrc.model.commentaire.StringReturn;
import fr.lrc.model.lemurien.LemurienModel;

public class JUnitLemurien {

	@Test
	public void lemurienVide() {
		LemurienModel lemurienM = new LemurienModel(1, "MINOU", "", "", "", "", "", "", "", "", "", "");
		StringReturn lemTest = LemurienController.lemurienController(lemurienM);
		StringReturn message = StringReturn.stringReturnMessage(true, "");

		assertEquals(message.isResponse(), lemTest.isResponse());
		assertEquals(message.getCommentaire(), lemTest.getCommentaire());

		lemTest = LemurienController.lemurienController(null);
		message = StringReturn.stringReturnMessage(false, "Object null");

		assertEquals(message.isResponse(), lemTest.isResponse());
		assertEquals(message.getCommentaire(), lemTest.getCommentaire());
	}

	@Test
	public void lemurienParam() {
		assertEquals(true, LemurienController.isStringValid("", "", 64).isResponse());
		assertEquals(true, LemurienController.isStringValid("", "azerty", 64).isResponse());
		assertEquals(false, LemurienController.isStringValid("", null, 64).isResponse());
		assertEquals(false, LemurienController.isStringValid("", "azerty", 5).isResponse());

	}

	@Test
	public void lemurienDate() {
		assertEquals(true, LemurienController.isDateValide("", "").isResponse());
		assertEquals(true, LemurienController.isDateValide("", "01/17").isResponse());
		assertEquals(true, LemurienController.isDateValide("", "31/01/15").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "01/017").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "01/2017").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "1/1/115").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "1/1/2015").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "a").isResponse());
		assertEquals(false, LemurienController.isDateValide("", null).isResponse());
		assertEquals(false, LemurienController.isDateValide("", "1/1/1/1").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "111/1/15").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "1/111/15").isResponse());
		assertEquals(false, LemurienController.isDateValide("", "1/1/155").isResponse());
	}

}
