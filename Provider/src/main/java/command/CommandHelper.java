package command;

import java.util.HashMap;
import java.util.Map;

import command.exception.CommandNotFoundException;
import command.impl.AuthenticationCommand;
import command.impl.ChangeLocaleCommand;
import command.impl.CheckAccountCommand;
import command.impl.LogOutCommand;
import command.impl.PaymentCommand;
import command.impl.PostRequestCommand;
import command.impl.RegistrationCommand;
import command.impl.SaveNewContractCommand;
import command.impl.SaveTariffCommand;
import command.impl.SaveUserCommand;
import command.impl.SetContactsCommand;
import command.impl.SetPasswordCommand;
import command.impl.SetRequestStatusCommand;
import command.impl.ShowAccountCommand;
import command.impl.ShowAllTariffsCommand;
import command.impl.ShowAllUsersCommand;
import command.impl.ShowRequestsCommand;
import command.impl.ShowTariffCommand;
import command.impl.ShowUserCommand;
import command.impl.SubscriptionCommand;
import command.impl.ToIndexCommand;
import command.impl.ToNewContractCommand;
import command.impl.ToPaymentCommand;
import command.impl.ToPersonalDetailsCommand;
import command.impl.ToRegistrationCommand;

/**
 * The Class CommandHelper. Looks up for command instance by its name
 */
public class CommandHelper {
	private final Map<CommandName, Command> commands = new HashMap<>();

	/**
	 * Instantiates a new command helper.
	 */
	public CommandHelper() {
		commands.put(CommandName.AUTHENTICATION, new AuthenticationCommand());
		commands.put(CommandName.TO_REGISTRATION, new ToRegistrationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.CHECK_ACCOUNT, new CheckAccountCommand());
		commands.put(CommandName.SAVE_NEW_CONTRACT, new SaveNewContractCommand());
		commands.put(CommandName.TO_NEW_CONTRACT, new ToNewContractCommand());
		commands.put(CommandName.SHOW_TARIFFS, new ShowAllTariffsCommand());
		commands.put(CommandName.SHOW_TARIFF, new ShowTariffCommand());
		commands.put(CommandName.SHOW_USERS, new ShowAllUsersCommand());
		commands.put(CommandName.SHOW_USER, new ShowUserCommand());
		commands.put(CommandName.SAVE_USER, new SaveUserCommand());
		commands.put(CommandName.SET_REQUEST_STATUS, new SetRequestStatusCommand());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
		commands.put(CommandName.LOG_OUT, new LogOutCommand());
		commands.put(CommandName.SHOW_REQUESTS, new ShowRequestsCommand());
		commands.put(CommandName.SHOW_ACCOUNT, new ShowAccountCommand());
		commands.put(CommandName.TO_PERSONAL_DETAILS, new ToPersonalDetailsCommand());
		commands.put(CommandName.SET_PASSWORD, new SetPasswordCommand());
		commands.put(CommandName.SET_CONTACTS, new SetContactsCommand());
		commands.put(CommandName.PAYMENT, new PaymentCommand());
		commands.put(CommandName.TO_PAYMENT, new ToPaymentCommand());
		commands.put(CommandName.TO_INDEX, new ToIndexCommand());
		commands.put(CommandName.POST_REQUEST, new PostRequestCommand());
		commands.put(CommandName.SUBSCRIBE, new SubscriptionCommand());
		commands.put(CommandName.SAVE_TARIFF, new SaveTariffCommand());
	}

	/**
	 * Gets the command.
	 *
	 * @param name the name
	 * @return the command
	 * @throws CommandNotFoundException the illegal command exception
	 */
	public Command getCommand(String name) throws CommandNotFoundException {
		try {
			CommandName commandName = CommandName.valueOf(name.toUpperCase());
			return commands.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new CommandNotFoundException(e);
		}
	}
}
