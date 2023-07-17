# Practice_Project_Lambdas
Project with functional interfaces, and bernoulli nmbers calculation
 Interface Tasks

This repository contains the implementation of several interface tasks in Java, showcasing my skills as a Java backend developer. The tasks involve working with interfaces, lambda expressions, anonymous inner classes, and more.
In this task streams cannot be used except for array filling

Interface Tasks Description

    Filter Method
        The filter method takes an array of any type and an implementation of the Filter interface to filter the elements of the array based on a provided condition.
        The Filter interface has a method boolean apply(T o) that determines whether an element satisfies the filter condition.

    Filter Method with Function Interface
        The extended filter method takes an array of any type and an implementation of the standard Function<T, R> interface, where T is the input type, and R is explicitly set to Boolean. It applies the filter based on the condition specified in the function.

Tasks Implemented

    Integer Array Tasks
        Filter positive numbers.
        Filter numbers equal to a given value (input from the keyboard).
        Filter even numbers using lambda expressions.

    Real Number Array Tasks
        Filter elements that are not Bernoulli numbers within a specified range of indices using complex lambda expressions.

    String Array Tasks
        Filter strings based on their length (input from the keyboard) using an anonymous inner class.
        Filter strings with characters sorted lexicographically using complex lambda expressions.
        Filter strings that represent words using a reference to a non-static method.

    Arbitrary Object Array Tasks
        Filter null references using the method Objects.nonNull.
        Filter objects that are filled with default values using lambda expressions.

    Detector Interface Tasks
        Keep only objects that return true for the detect method of the Detector interface. (The detect method should check if the class has declared fields.)

How to Use

    Clone the repository and import the project into your Java IDE.
    Navigate to the main class where the tasks are implemented.
    Run the Java application to see the results of the interface tasks.

Feel free to explore the code and review the implementations for each task. This portfolio demonstrates my proficiency in Java backend development and handling various tasks using interfaces and lambda expressions.

For any questions or collaboration opportunities, please reach out to me via solovev.anton93@gmail.com

Thank you for visiting my portfolio! Happy coding!
