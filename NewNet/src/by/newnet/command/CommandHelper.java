package by.newnet.command;

import java.util.HashMap;
import java.util.Map;

import by.newnet.command.impl.AddTariff;
import by.newnet.command.impl.Authentication;
import by.newnet.command.impl.ChangeLocale;
import by.newnet.command.impl.LogOut;
import by.newnet.command.impl.Registration;
import by.newnet.command.impl.ShowTariffs;

public class CommandHelper {
		private Map<CommandName, Command> commands = new HashMap<>();
		
		public CommandHelper(){
			commands.put(CommandName.AUTHENTICATION, new Authentication());
			commands.put(CommandName.REGISTRATION, new Registration());		
			commands.put(CommandName.SHOW_TARIFFS, new ShowTariffs());	
			commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
			commands.put(CommandName.LOG_OUT, new LogOut());
			commands.put(CommandName.ADD_TARIFF, new AddTariff());

		}
		
		
		public Command getCommand(String name){
			CommandName commandName = CommandName.valueOf(name.toUpperCase());
			Command command = commands.get(commandName);
			return command;		
		}
	}


