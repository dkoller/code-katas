# Ohce kata

[Kata description](http://garajeando.blogspot.com.es/2016/05/the-ohce-kata-short-and-simple-exercise.html)

[CRC-Cards](http://www.extremeprogramming.org/rules/crccards.html):
* OhceService
    * Responsibilities:
        * Delegate interpretation of first console input according to time
        * Delegate interpretation of the following console inputs
        * Delegate printing interpretation result
    * Collaborators:
        * Interpreter
        * Console
        * Clock

* Interpreter
    * Responsibilities:
        * Calculate output message according to input message
    * Collaborators:
        * -
