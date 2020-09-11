# The task

**1,** Write a java command line program that gives me two options. One to ask a specific question and the other option is to add questions and their answers
Therefore the following restrictions apply:

* A Question is a String with max 255 chars
* An Answer is a String with max 255 chars
* A Question can have multiple answers (like bullet points)
* If the user asks a question it has to be exactly the same as entered – no *“best match”.*
* If the user asks a question which is not stored yet the program should print *“the answer to life, universe and everything is 42”* according to *“The hitchhikers guide to the Galaxy”*
* If the user asks a question whish is  stored the program should print all answers to that question. Every Answer in a separate line
* Adding a question looks like:
    * *<question>? “<answer1>” “<answer2>” “<answerX>”*
    * Char *“?”* is the separator between question and answers
    * Every Question needs to have at least one answer but can have unlimited answers all inside of char “
    * 
**2**, Provide tests for the functionality described in 1)
**3**, Provide the source code on any GIT repo worldwide (GITLAB, GITHUB, whatever you prefer and/or use
**4**, Provide a gradle or Maven build for Building and test execution
**5**, Provide a Jenkinsfile to run the build and test execution in Jenkin
**6**, No other restrictions apply

# Example

Adding a question:
* What is Peters favorite food? “Pizza” “Spaghetti” “Ice cream”

Asking a question which is in the system:
* What is Peters favorite food?
    * Answers will be
        * Pizza
        * Spaghetti
        * Ice cream

Asking a question which is not in the system:
* When is Peters birthday?
    * Answer will be
        * the answer to life, universe and everything is 42

# Build
To use provided Jenkinsfile you should change values of Tool tag, according your Jenkins setup.
If you are using Linux system change the starter string of *"Run Maven on a Unix agent"* segment from "bat" to "sh".