package by.newnet.command;

import java.util.HashMap;
import java.util.Map;

import by.newnet.command.impl.SaveTariff;
import by.newnet.command.impl.AddNewContract;
import by.newnet.command.impl.Authentication;
import by.newnet.command.impl.ChangeLocale;
import by.newnet.command.impl.ChangePersonalDetails;
import by.newnet.command.impl.CheckAccount;
import by.newnet.command.impl.Registration;
import by.newnet.command.impl.LogOut;
import by.newnet.command.impl.Pay;
import by.newnet.command.impl.PostRequest;
import by.newnet.command.impl.Registration;
import by.newnet.command.impl.SetContacts;
import by.newnet.command.impl.SetPassword;
import by.newnet.command.impl.ShowAccountInfo;
import by.newnet.command.impl.ShowRequests;
import by.newnet.command.impl.ShowTariffs;
import by.newnet.command.impl.ShowUsers;
import by.newnet.command.impl.Subscribe;
import by.newnet.command.impl.ToPayment;

public class CommandHelper {
		private Map<CommandName, Command> commands = new HashMap<>();
		
		public CommandHelper(){
			commands.put(CommandName.AUTHENTICATION, new Authentication());
			commands.put(CommandName.REGISTRATION, new Registration());
			commands.put(CommandName.CHECK_ACCOUNT, new CheckAccount());		
			commands.put(CommandName.ADD_NEW_CONTRACT, new AddNewContract());		

			commands.put(CommandName.SHOW_TARIFFS, new ShowTariffs());
			commands.put(CommandName.SHOW_USERS, new ShowUsers());	
			//commands.put(CommandName.SHOW_OPERATIONS, new ShowOperations());	

			commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
			commands.put(CommandName.LOG_OUT, new LogOut());
			commands.put(CommandName.SHOW_REQUESTS, new ShowRequests());
			commands.put(CommandName.SHOW_ACCOUNT_INFO, new ShowAccountInfo());
			commands.put(CommandName.CHANGE_PERSONAL_DETAILS, new ChangePersonalDetails());
			commands.put(CommandName.SET_PASSWORD, new SetPassword());
			commands.put(CommandName.SET_CONTACTS, new SetContacts());
			commands.put(CommandName.PAY, new Pay());
			commands.put(CommandName.TO_PAYMENT, new ToPayment());

			commands.put(CommandName.POST_REQUEST, new PostRequest());
			commands.put(CommandName.SUBSCRIBE, new Subscribe());

			commands.put(CommandName.SAVE_TARIFF, new SaveTariff());


		}
		
		
		public Command getCommand(String name){
			CommandName commandName = CommandName.valueOf(name.toUpperCase());
			Command command = commands.get(commandName);
			return command;		
		}
	}


