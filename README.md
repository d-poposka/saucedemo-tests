Test Environment Setup:
This project has an automated test suite for the SauceDemo website, written in Java, Selenium, and TestNG. Below are the steps to set up the environment and execute the tests.

Before executing tests, ensure that you have the following installed and set up:

Java 17 or higher – Ensure Java is properly installed and set up on your system.

Maven – This project uses Maven for dependency management and test execution. Install Maven if it's not already installed.

WebDriverManager – WebDriverManager is utilized in this project to automatically manage the necessary browser drivers (e.g., ChromeDriver), so there is no need for manual configuration.

Setting Up the Test Environment:
Clone the Repository: Start by cloning the repository on your local machine. Clone it with the following command:
git clone https://github.com/d-poposka/saucedemo-tests.git

Install Dependencies: Once you have the repository in your system, navigate to the project directory and install all the dependencies by running:
mvn clean install
This will download all necessary libraries and set up the environment for running the tests.

Run the Tests: To run the automated tests, run the following Maven command:
mvn test
This will trigger TestNG to run the SauceDemoTest.java file, which automates the entire flow of logging into the SauceDemo website, adding an item to the cart, proceeding through checkout, and logging out.
