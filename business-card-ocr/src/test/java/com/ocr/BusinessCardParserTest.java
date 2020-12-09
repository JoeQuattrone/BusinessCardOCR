package com.ocr;

import static com.ocr.BusinessCardParser.UNKNOWN_NAME;
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
      {"John Smith", "John", "John Smith"},
      {CASE1, "Mike", "Mike Smith"},
      {CASE2, "Lisa", "Lisa Haung"},
      {CASE3, "Arthur", "Arthur Wilson"},
      {CASE_WITH_NO_NAME, "Random Word", UNKNOWN_NAME},
    };
  }

  @Test(dataProvider = "findByFirstNameDataSet")
  public void findName_shouldReturnNameFromCardInfoUsingFirstNameSearch(
      final String info, final String firstName, final String expectedName) {
    when(firstNameDictionary.get(firstName)).thenReturn(Boolean.TRUE);

    final String name = businessCardParser.findName(info);

    assertThat(name).isEqualTo(expectedName);
  }

  @DataProvider
  Object[][] findByLastNameDataSet() {
    return new Object[][] {
      {"John Smith", "Smith", "John Smith"},
      {CASE1, "Smith", "Mike Smith"},
      {CASE2, "Haung", "Lisa Haung"},
      {CASE3, "Wilson", "Arthur Wilson"},
      {CASE_WITH_NO_NAME, "Random Word", UNKNOWN_NAME},
    };
  }

  @Test(dataProvider = "findByLastNameDataSet")
  public void findName_shouldReturnNameFromCardInfoUsingLastNameSearch(
      final String info, final String lastName, final String expectedName) {
    when(lastNameDictionary.get(lastName)).thenReturn(Boolean.TRUE);

    final String name = businessCardParser.findName(info);

    assertThat(name).isEqualTo(expectedName);
  }

  private static final String CASE1 =
      "ASYMMETRIK LTD Mike Smith Senior Software Engineer (410)555-1234 msmith@asymmetrik.com";
  private static final String CASE2 =
      "Foobar Technologies Analytic Developer Lisa Haung 1234 Sentry Road Columbia, MD 12345 Phone: 410-555-1234 Fax: 410-555-4321 lisa.haung@foobartech.com";
  private static final String CASE3 =
      "Arthur Wilson Software Engineer Decision & Security Technologies ABC Technologies 123 North 11th Street Suite 229 Arlington, VA 22209 Tel: +1 (703) 555-1259 Fax: +1 (703) 555-1200 awilson@abctech.com";
  private static final String CASE_WITH_NO_NAME = "Text with no name";
}
