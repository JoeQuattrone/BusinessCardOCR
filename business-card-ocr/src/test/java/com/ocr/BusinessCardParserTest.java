package com.ocr;

import static com.ocr.BusinessCardParser.UNKNOWN_EMAIL;
import static com.ocr.BusinessCardParser.UNKNOWN_NAME;
import static com.ocr.BusinessCardParser.UNKNOWN_PHONE_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ocr.dictionaries.FirstNameDictionary;
import com.ocr.dictionaries.LastNameDictionary;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BusinessCardParserTest {
  private BusinessCardParser businessCardParser;
  private FirstNameDictionary firstNameDictionary;
  private LastNameDictionary lastNameDictionary;

  @BeforeMethod
  public void setUp() {
    firstNameDictionary = mock(FirstNameDictionary.class);
    lastNameDictionary = mock(LastNameDictionary.class);
    businessCardParser = new BusinessCardParser(firstNameDictionary, lastNameDictionary);
  }

  @DataProvider
  Object[][] findByFirstNameDataSet() {
    return new Object[][] {
      {CARD1, "Mike", "Mike Smith"},
      {CARD2, "Lisa", "Lisa Haung"},
      {CARD3, "Arthur", "Arthur Wilson"},
      {CARD_WITH_NO_NAME, "Random Word", UNKNOWN_NAME},
    };
  }

  @Test(dataProvider = "findByFirstNameDataSet")
  public void findName_shouldReturnNameFromCardInfoUsingFirstNameSearch(
      final BusinessCard card, final String firstName, final String expectedName) {
    when(firstNameDictionary.get(firstName)).thenReturn(Boolean.TRUE);

    final String name = businessCardParser.findName(card.getInfo());

    assertThat(name).isEqualTo(expectedName);
  }

  @DataProvider
  Object[][] findByLastNameDataSet() {
    return new Object[][] {
      {CARD1, "Smith", "Mike Smith"},
      {CARD2, "Haung", "Lisa Haung"},
      {CARD3, "Wilson", "Arthur Wilson"},
      {CARD_WITH_NO_NAME, "Random Word", UNKNOWN_NAME},
    };
  }

  @Test(dataProvider = "findByLastNameDataSet")
  public void findName_shouldReturnNameFromCardInfoUsingLastNameSearch(
      final BusinessCard card, final String lastName, final String expectedName) {
    when(lastNameDictionary.get(lastName)).thenReturn(Boolean.TRUE);

    final String name = businessCardParser.findName(card.getInfo());

    assertThat(name).isEqualTo(expectedName);
  }

  @DataProvider
  Object[][] findPhoneNumberDataSet() {
    return new Object[][] {
      {CARD1, "4105551234"},
      {CARD2, "4105551234"},
      {CARD3, "7035551259"},
      {CARD_WITH_NO_NAME, UNKNOWN_PHONE_NUMBER},
    };
  }

  @Test(dataProvider = "findPhoneNumberDataSet")
  public void findPhoneNumber_ShouldReturnPhoneNumber(
      final BusinessCard card, final String expected) {
    final String phoneNumber = businessCardParser.findPhoneNumber(card.getInfo());

    assertThat(phoneNumber).isEqualTo(expected);
  }

  @DataProvider
  Object[][] findEmailAddressDataSet() {
    return new Object[][] {
      {CARD1, "msmith@asymmetrik.com"},
      {CARD2, "lisa.haung@foobartech.com"},
      {CARD3, "awilson@abctech.com"},
      {CARD_WITH_NO_NAME, UNKNOWN_EMAIL},
    };
  }

  @Test(dataProvider = "findEmailAddressDataSet")
  public void findEmailAddress_ShouldReturnEmailAddress(
      final BusinessCard card, final String expected) {
    final String email = businessCardParser.findEmailAddress(card.getInfo());

    assertThat(email).isEqualTo(expected);
  }

  private static final BusinessCard CARD1 =
      new BusinessCard(
          "ASYMMETRIK LTD Mike Smith Senior Software Engineer (410)555-1234 msmith@asymmetrik.com");
  private static final BusinessCard CARD2 =
      new BusinessCard(
          "Foobar Technologies Analytic Developer Lisa Haung 1234 Sentry Road Columbia, MD 12345 Phone: 410-555-1234 Fax: 410-555-4321 lisa.haung@foobartech.com");
  private static final BusinessCard CARD3 =
      new BusinessCard(
          "Arthur Wilson Software Engineer Decision & Security Technologies ABC Technologies 123 North 11th Street Suite 229 Arlington, VA 22209 Tel: +1 (703) 555-1259 Fax: +1 (703) 555-1200 awilson@abctech.com");
  private static final BusinessCard CARD_WITH_NO_NAME = new BusinessCard("Text with no name");
}
