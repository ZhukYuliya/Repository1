package by.newnet.command;

import java.util.HashMap;
import java.util.Map;

import by.newnet.command.impl.SaveTariff;
import by.newnet.command.impl.SaveUser;
import by.newnet.command.impl.SaveNewContract;
import by.newnet.command.impl.Authentication;
import by.newnet.command.impl.ChangeLocale;
import by.newnet.command.impl.ChangePersonalDetails;
import by.newnet.command.impl.CheckAccount;
import by.newnet.command.impl.Registration;
import by.newnet.command.impl.LogOut;
import by.newnet.command.impl.Pay;
import by.newnet.command.impl.PostRequest;
import by.newnet.command.impl.RegisterNewContract;
import by.newnet.command.impl.SetContacts;
import by.newnet.command.impl.SetPassword;
import by.newnet.command.impl.SetRequestStatus;
import by.newnet.command.impl.ShowAccount;
import by.newnet.command.impl.ShowRequests;
import by.newnet.command.impl.ShowTariff;
import by.newnet.command.impl.ShowTariffs;
import by.newnet.command.impl.ShowUser;
import by.newnet.command.impl.ShowUsers;
import by.newnet.command.impl.Subscribe;
import by.newnet.command.impl.ToPayment;

public class CommandHelper {
		private Map<CommandName, Command> commands = new HashMap<>();
		
		public CommandHelper(){
			commands.put(CommandName.AUTHENTICATION, new Authentication());
			commands.put(CommandName.REGISTRATION, new Registration());
			commands.put(CommandName.CHECK_ACCOUNT, new CheckAccount());		
			commands.put(CommandName.SAVE_NEW_CONTRACT, new SaveNewContract());		
			commands.put(CommandName.REGISTER_NEW_CONTRACT, new RegisterNewContract());		
			commands.put(CommandName.SHOW_TARIFFS, new ShowTariffs());
			commands.put(CommandName.SHOW_TARIFF, new ShowTariff());
			commands.put(CommandName.SHOW_USERS, new ShowUsers());	
			commands.put(CommandName.SHOW_USER, new ShowUser());	
			commands.put(CommandName.SAVE_USER, new SaveUser());	
			commands.put(CommandName.SET_REQUEST_STATUS, new SetRequestStatus());	
			commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
			commands.put(CommandName.LOG_OUT, new LogOut());
			commands.put(CommandName.SHOW_REQUESTS, new ShowRequests());
			commands.put(CommandName.SHOW_ACCOUNT, new ShowAccount());
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


