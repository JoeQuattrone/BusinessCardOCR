# Business Card Optical Character Reader

### Introduction
This project is a Spring Boot web application that allows users to enter common business card data. It parses the text and returns the following data from the business card:
* Name
* Telephone number
* Email address

### Prerequisites
Before you continue ensure you have the following installed:
* Java 8
* Maven 3.2+
* Bash or zsh shell

### How To Install
<p>Clone this repository to your local machine</p>

### How to Run
<p>To start the application locally, from the root directory (business-card-ocr) run:</p>

    ./mvnw spring-boot:run
    
<p>In your browser navigate to: </p>

    localhost:8080
    
### Example Usage
<p>From the home page enter the following text into the business card input: </p>

    Foobar Technologies
    Analytic Developer
    Lisa Haung
    1234 Sentry Road
    Columbia, MD 12345
    Phone: 410-555-1234
    Fax: 410-555-4321
    lisa.haung@foobartech.com
        
<p>The following data should be extracted from the text and displayed on the home page: </p>

    ==>
    Name: Lisa Haung
    Phone: 4105551234
    Email: lisa.haung@foobartech.com
    
### How To Test
<p>Run tests with Maven. To execute the test suite run: </p>

    mvn test -DsuiteXmlFile=testng.xml
    
### How It Works
<p>The Business Card OCR uses different algorithms to extract the 3 pieces of the data listed above. The following is an explanation of each algorithm:</p>

    Name - Searches for first names using a dictionary of common first names. 
    If the search finds a first name, return the name with the word after it. 
    Otherwise search for last names using a dictionary of common last names. 
    If last name is found return the last name with the word before it.  
    
    Telephone Number - Searches for numeric words larger then 6 digits. Checks to make sure the previous is not "fax"
    then checks to see if digits are in the previous word, if so concat the previous word with the
    current word to form the complete phone number.
    Otherwise return the current word.
    
    Email Address - returns the first word with an '@' symbol. 
    
### Acknowledgments
Thank you to the team who put together the Spring Boot Getting Started Guide. You can find the original repository [here](https://github.com/spring-guides/gs-spring-boot)
    
     