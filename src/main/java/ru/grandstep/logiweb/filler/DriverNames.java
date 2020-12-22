package ru.grandstep.logiweb.filler;

import java.util.List;

public class DriverNames {
    static final int min = 0;
    static final int max = 99;

    static String getFirstName(){
        int random = (int) (Math.random() * (max - min + 1) + min);
        return fName.get(random);
    }

    static String getLastName(){
        int random = (int) (Math.random() * (max - min + 1) + min);
        return lName.get(random);
    }

    static private final List<String> fName = List.of("Oliver","George","Harry","Noah","Jack","Leo","Arthur",
            "Muhammad","Oscar","Charlie","Jacob","Thomas","Henry","William",
            "Alfie","Archie","Joshua","Freddie","Theo","Isaac","James",
            "Alexander","Logan","Edward","Ethan","Lucas","Joseph",
            "Sebastian","Theodore","Finley","Max","Mohammed","Samuel",
            "Harrison","Benjamin","Arlo","Daniel","Adam","Teddy","Mason",
            "Elijah","Reuben","Dylan","Hunter","Reggie","Jaxon","Rory",
            "Louie","David","Tommy","Luca","Albie","Hugo","Zachary",
            "Albert","Jude","Toby","Riley","Ezra","Carter","Gabriel",
            "Roman","Frankie","Harley","Frederick","Ronnie","Jake","Elliot",
            "Louis","Jenson","Mohammad","Stanley","Bobby","Michael","Jesse",
            "Finn","Jayden","Harvey","Caleb","Jackson","Charles","Matthew",
            "Grayson","Blake","Liam","Elliot","Ellis","Ralph","Jasper",
            "Rowan","Alex","Ryan","Felix","Luke","Dexter","Ollie","Leon",
            "Tobias","Sonny","Dominic","Kai");

    static private final List<String> lName = List.of("Brown","Hall","Palmer","Green","Clark","Quinn","Harper",
            "Johnson","Owen","Ross","Carr","Allen","Hunter","Wilson",
            "Davis","Rogers","Anderson","Jennings","Day","Jackson",
            "Hewitt","Morgan","Kelly","Rowe","Reynolds","Austin","Harding",
            "Hammond","Buckley","Burgess","Glover","Riley","Willis",
            "Richardson","Hawkins","Doyle","Wells","Perry","Heath","Bates",
            "Steele","Johnston","Holland","Barnett","Jones","Spencer",
            "Barry","Hayes","Grant","Gardner","Goodwin","Cox","Chambers",
            "Barrett","Warren","Little","Coates","Archer","Mann","Howell",
            "Dale","Oliver","Wheeler","Andrews","Waters","Moss","Briggs",
            "West","Blake","Patterson","Higgins","Francis","Barber","Frost",
            "Griffin","Middleton","Murphy","Lambert","Kent","George",
            "Marsh","Reed","Page","Stone","Dean","Booth","Roberts",
            "Elliott","Bailey","Harris","Bolton","Morton","Sanders","Joyce",
            "James","Smith","Hill","Douglas","Thomson","Khan");
}