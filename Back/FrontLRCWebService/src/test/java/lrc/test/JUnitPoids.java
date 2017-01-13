package lrc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.lrc.controller.PoidsController;
import fr.lrc.model.commentaire.StringReturn;
import fr.lrc.model.poids.PoidsModel;

public class JUnitPoids {

	@Test
	public void PoidsVide() {
		PoidsModel PoidsM = new PoidsModel(1, "", "01/17", 0.0);
		StringReturn poidsTest = PoidsController.poidsController(PoidsM);
		StringReturn message = StringReturn.stringReturnMessage(true, "");

		assertEquals(message.isResponse(), poidsTest.isResponse());
		assertEquals(message.getCommentaire(), poidsTest.getCommentaire());

		poidsTest = PoidsController.poidsController(null);
		message = StringReturn.stringReturnMessage(false, "Object null");

		assertEquals(message.isResponse(), poidsTest.isResponse());
		assertEquals(message.getCommentaire(), poidsTest.getCommentaire());
	}

	@Test
	public void PoidsParam() {
		assertEquals(true, PoidsController.isStringValid("", "", 64).isResponse());
		assertEquals(true, PoidsController.isStringValid("", "azerty", 64).isResponse());
		assertEquals(false, PoidsController.isStringValid("", null, 64).isResponse());
		assertEquals(false, PoidsController.isStringValid("", "azerty", 5).isResponse());

	}

	@Test
	public void PoidsDate() {
		assertEquals(true, PoidsController.isDateValide("", "01/15").isResponse());
		assertEquals(false, PoidsController.isDateValide("", "011/15").isResponse());
		assertEquals(false, PoidsController.isDateValide("", "01/151").isResponse());
		assertEquals(true, PoidsController.isDateValide("", "").isResponse());
		assertEquals(false, PoidsController.isDateValide("", "1/1/15").isResponse());
		assertEquals(false, PoidsController.isDateValide("", "a").isResponse());
		assertEquals(false, PoidsController.isDateValide("", null).isResponse());
		assertEquals(false, PoidsController.isDateValide("", "1/1/1/1").isResponse());
		assertEquals(false, PoidsController.isDateValide("", "111/1/15").isResponse());
		assertEquals(false, PoidsController.isDateValide("", "1/111/15").isResponse());
		assertEquals(false, PoidsController.isDateValide("", "1/1/155").isResponse());
	}
}
