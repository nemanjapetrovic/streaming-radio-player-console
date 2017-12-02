# Streaming radio player

This is a terminal software used to play streaming radio stations via url. Just add some stations, and enjoy the music. :)

## Requirements
- installed VLC player because this app is using VLC libraries
    
## Commands
- ```help``` (prints all the commands with explenation)
- ```add``` (used to add name and url of the station)
- ```remove``` (used to remove the station)
- ```list``` (used to list/print all saved stations)
- ```play``` (used to play radio station)
    
## Terminal commands explanation      
- ```add [name] [url]```  Add a station to 'database' with some name and url
- ```remove [name]```  Remove a station from 'database' with some name
- ```list```  Prints all radio stations data from 'database'
- ```play [name]```  Play a radio station with some name

## How to add more commands
To add new commands you wll need to change four files:
- ```ICommand.java``` - add a new function
- ```Command.java``` - implement that function
- ```Args.java``` - in enum ```CommandEnums``` add a name of the command
- ```Main.java``` - in switch case statment ```main function``` add new case (your command name)
`

[![N|Solid](http://www.blogworld.com/wp-content/uploads/2009/04/linkedin-logo.jpg)](https://rs.linkedin.com/in/nemanjapetrovic1994)
