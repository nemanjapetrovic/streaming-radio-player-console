package com.nemanjapetrovic.streaming.radio.player;

import com.nemanjapetrovic.streaming.radio.player.Commands.Command;
import com.nemanjapetrovic.streaming.radio.player.Commands.Args;

/**
 * Created by nemanja on 10/3/16.
 */
public class Main {

    public static void main(String[] args) {
        //Main command object
        Command command = new Command();

        //Get commands and arguments for the command
        Args arguments = null;
        if (args.length >= 1) {
            arguments = new Args(
                    (args[0] != null) ? args[0] : "",
                    (args.length >= 2 && args[1] != null) ? args[1] : "",
                    (args.length >= 3 && args[2] != null) ? args[2] : "");
        }

        try {
            if (arguments == null) { //No command valid was selected
                throw new Exception("Wrong program arguments, try help command!");
            }

            //Get command enum
            Args.CommandEnums cmd = Args.CommandEnums.valueOf(arguments.command.toLowerCase());
            switch (cmd) {
                case help:
                    command.Help();
                    break;
                case add:
                    command.Add(arguments.name, arguments.url);
                    break;
                case remove:
                    command.Remove(arguments.name);
                    break;
                case list:
                    command.List();
                    break;
                case play:
                    command.Play(arguments.name);
                    break;
                //For adding a new command just add a new case
                default:
                    System.out.println("The command does not exist, please try help!");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage() + "\n" + "The command does not exist!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}

